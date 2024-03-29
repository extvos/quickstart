package plus.extvos.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import plus.extvos.builtin.async.dto.AsyncTask;
import plus.extvos.builtin.async.service.AsyncTaskRunner;
import plus.extvos.common.Assert;
import plus.extvos.common.Result;
import plus.extvos.common.annotation.Limit;
import plus.extvos.common.exception.ResultException;
import plus.extvos.logging.annotation.Log;
import plus.extvos.mqtt.annotation.Payload;
import plus.extvos.mqtt.annotation.TopicSubscribe;
import plus.extvos.mqtt.annotation.TopicVariable;
import plus.extvos.mqtt.publish.MqttPublisher;
import plus.extvos.redis.service.QuickRedisService;

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
    private AsyncTaskRunner asyncTaskRunner;

    @Autowired
    private QuickRedisService redisService;

    @Log
    @Limit(count = 10, period = 1)
    @GetMapping("/example/by/{num}")
    @ApiOperation("example request")
    public Result<?> exampleByNum(@PathVariable int num,
                                  @RequestParam(required = false) Map<String, String> queries) throws Exception {
        log.debug("example > {}", num);
        log.debug("queries > {}", queries);
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

    @PostMapping("/example/test1")
    public Result<?> exampleTest1Post(@RequestParam("access_token") String accessToken, @RequestBody List<Map<Object, Object>> data) {
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
    public Result<?> exampleTest1Get(@RequestParam("access_token") String accessToken) throws ResultException {
        Object v = redisService.get("T:" + accessToken);
        if (null != v) {
            return Result.data(v).success();
        } else {
            throw ResultException.notFound();
        }
    }

    @GetMapping("/example/async")
    public Result<AsyncTask> exampleAsync(@RequestParam("duration") int duration) throws ResultException {
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

    @TopicSubscribe("myth2ws01/{devId}")
    public void myth2ws01(String topic, @TopicVariable("devId") String devId, @Payload Map<String, Object> data) {
        log.debug("myth2ws01:> {}, {}, {}", topic, devId, data);
    }

    @TopicSubscribe("flexbox/report/{devId}")
    public void flexbox(String topic, @TopicVariable("devId") String devId, @Payload Map<String, Object> data) {
        log.debug("flexbox:> {}, {}, {}", topic, devId, data);
    }

}
