package org.extvos.example.controller;

import org.extvos.auth.annotation.SessionUser;
import org.extvos.logging.annotation.Log;
import org.extvos.restlet.Result;
import org.extvos.restlet.annotation.Limit;
import org.extvos.restlet.exception.RestletException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenmc
 */
@RestController
@Api(tags = {"测试样例"})
public class ExampleController {

    private static final Logger log = LoggerFactory.getLogger(ExampleController.class);

    @Log
    @Limit(count = 10, period = 1)
    @GetMapping("/example/by/{num}")
    @ApiOperation("example request")
    public Result<?> exampleByNum(@PathVariable int num,
                                  @RequestParam(required = false) Map<String, String> queries,
                                  @SessionUser String username) throws Exception {
        log.debug("example > {}", num);
        log.debug("queries > {}", queries);
        log.debug("username > {}", username);
        HashMap<String, Object> m = new HashMap<>(4);
        m.put("a", 1);
        m.put("b", 2);
        m.put("c", 3);
        m.put("d", 4);
        if (num < 0) {
            throw new ApplicationContextException("hoola");
        }
        if (num < 10) {
            throw RestletException.badRequest("num can not less than 10");
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
    public Result<String> exampleByUser(@SessionUser String username) {
        return Result.data(username).success();
    }

    @PostMapping("/example/test1")
    public Result<?> exampleTest1(@RequestParam("access_token") String accessToken, @RequestBody List<Map<Object, Object>> data) {
        log.debug("exampleTest1:> accessToken: {}", accessToken);
        log.debug("exampleTest1:> data: {}", data);
        data.forEach(o -> {
            o.forEach((k, v) -> {
                log.debug(">>>> {} = {}", k, v);
            });
        });
        return Result.data(data).success();
    }

}
