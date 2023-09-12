package plus.extvos.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import plus.extvos.auth.annotation.SessionUser;
import plus.extvos.builtin.async.dto.AsyncTask;
import plus.extvos.builtin.async.service.AsyncTaskRunner;
import plus.extvos.common.Assert;
import plus.extvos.common.Result;
import plus.extvos.common.annotation.Limit;
import plus.extvos.common.exception.ResultException;
import plus.extvos.common.i18n.LocaleMessage;
import plus.extvos.common.utils.RequestContext;
import plus.extvos.example.service.ExampleService;
import plus.extvos.logging.annotation.Log;
import plus.extvos.mqtt.annotation.Payload;
import plus.extvos.mqtt.annotation.TopicSubscribe;
import plus.extvos.mqtt.annotation.TopicVariable;
import plus.extvos.mqtt.publish.MqttPublisher;
import plus.extvos.redis.service.QuickRedisService;
import plus.extvos.restlet.annotation.RemoteAddress;
import plus.extvos.restlet.annotation.RequestHeader;
import plus.extvos.restlet.annotation.UserAgent;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mingcai SHEN
 */
@EnableAsync
@RestController
@Api(tags = {"测试样例"})
public class ExampleController {

    private static final Logger log = LoggerFactory.getLogger(ExampleController.class);

    @Autowired
    private ExampleService exampleService;

    @Autowired
    private AsyncTaskRunner asyncTaskRunner;

    @Autowired
    private QuickRedisService redisService;

    @Autowired
    private LocaleMessage localeMessage;

    @Log
    @Limit(count = 10, period = 1)
    @GetMapping("/example/by/{num}")
    @ApiOperation("example request")
    public Result<?> exampleByNum(@PathVariable int num,
                                  @RequestParam(required = false) Map<String, String> queries,
                                  @SessionUser String username, @RemoteAddress String remoteIp,
                                  @UserAgent String ua,
                                  @RequestHeader("Referer") String referer) throws Exception {
        log.debug("example > {}", num);
        log.debug("queries > {}", queries);
        log.debug("username > {}", username);
        log.debug("SessionUser: {}", username);
        log.debug("RemoteAddress: {}", remoteIp);
        log.debug("UserAgent: {}", ua);
        log.debug("Referer: {}", referer);
        HashMap<String, Object> m = new HashMap<>(4);
        m.put("a", 1);
        m.put("b", 2);
        m.put("c", 3);
        m.put("d", 4);
        if (num < 0) {
            throw new ApplicationContextException("hoola");
        }
        if (num < 10) {
            throw ResultException.badRequest("num can not less than 10");
        }
        if (null != queries) {
            queries.forEach((String k, String v) -> {
                log.debug(">> Query: {} = {}", k, v);
            });
        }
        Result<?> rs = Result.data(m);
        rs.header("TEST", "Example Test");
        rs.cookie("TEST", "Example Cookie");
        return rs.success();
    }

    @RequiresAuthentication
    @GetMapping("/example/by/user")
    public Result<?> exampleByUser(@SessionUser String username, @RemoteAddress String remoteIp, @UserAgent String ua) {
        Map<String, Object> m = new HashMap<>();
        RequestContext ctx = RequestContext.probe();
        m.put("SessionUser", username);
        m.put("RemoteAddress", ctx.getIpAddress());
        m.put("UserAgent", ctx.getBrowser());
        return Result.data(m).success();
    }

    @PostMapping("/example/test1")
    @RequiresPermissions(value = {"token","access"})
    public Result<?> exampleTest1(@RequestParam("access_token") String accessToken, @RequestBody List<Map<Object, Object>> data) {
        log.debug("exampleTest1:> accessToken: {}", accessToken);
        log.debug("exampleTest1:> data: {}", data);
        data.forEach(o -> {
            o.forEach((k, v) -> {
                log.debug(">>>> {} = {}", k, v);
            });
        });
        redisService.set("T:" + accessToken, data);
        return Result.data(data).success();
    }

    @GetMapping("/example/test1")
//    @Log(action = LogAction.CREATE, level = LogLevel.NORMAL)
    public Result<?> exampleTest1Get(@RequestParam("access_token") String accessToken) throws ResultException {
        Object v = redisService.get("T:" + accessToken);
        log.debug("Locale:> {}", localeMessage.getLocale());
        if (null != v) {
            return Result.data(v).success();
        } else {
            String msg = localeMessage.getMessage("exception.not_found");
            log.debug("Message:> {}", msg);
            throw ResultException.notFound(msg);
        }
    }

    @GetMapping("/example/async")
    public Result<AsyncTask> exampleAsync(@Valid @RequestParam("duration") int duration) throws ResultException {
        AsyncTask t = asyncTaskRunner.make((ai) -> {
            try {
                for (int i = 1; i <= duration; i++) {
                    log.info(Thread.currentThread().getName() + "----------异步：>" + i);
                    Thread.sleep(200);
                    double d = (double) i / (double) duration;
                    ai.percentage((int) (d * 100));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return Thread.currentThread().getName() + "执行异常:" + ex.getMessage();
            }
            return Thread.currentThread().getName() + "执行完毕";
        }, "example-async-" + duration);
        asyncTaskRunner.start(t);
        return Result.data(t).success();
    }

    private final MqttPublisher publisher = new MqttPublisher();

    @PostMapping("/example/mqtt-send")
    public Result<?> exampleMqttSend(@RequestParam(name = "topic") String topic, @RequestBody Map<String, Object> data) throws ResultException {
        Assert.notEmpty(topic, ResultException.badRequest());
        Assert.notEmpty(data, ResultException.badRequest());
        publisher.send(topic, data);
        return Result.data("OK").success();
    }

    @TopicSubscribe("test/#")
    public void mqttTestTopics(String topic, @Payload Map<String, Object> data) {
        log.debug("mqttTestTopics:> {}, {}", topic, data);
    }

    @TopicSubscribe("$SYS/brokers/{node}/clients/{device}/connected")
    public void onConnect(String topic, @TopicVariable("node") String node, @TopicVariable("device") String device, @Payload Map<String, Object> data) {
        log.info("onConnect1:> {} {} ", node, device);
        log.info("onConnect2:> {} {} ", topic, data);
    }

    @TopicSubscribe("$SYS/brokers/{node}/clients/{device}/disconnected")
    public void onDisconnect(String topic, @TopicVariable("node") String node, @TopicVariable("device") String device, @Payload Map<String, Object> data) {
        log.info("onDisconnect1:> {} {} ", node, device);
        log.info("onDisconnect2:> {} {} ", topic, data);
    }

}
