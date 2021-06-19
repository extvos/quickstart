package org.extvos.example.controller;

import org.extvos.example.entity.StudentScore;
import org.extvos.example.service.StudentScoreService;
import org.extvos.restlet.controller.BaseController;
import org.extvos.restlet.controller.BaseROController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 学生成绩
 *
 * @author Quick Lab
 */
@RestController
@RequestMapping("/example/student-score")
@Api(tags = {"学生成绩"})
public class StudentScoreController extends BaseController<StudentScore, StudentScoreService> {

    @Autowired
    private StudentScoreService myService;

    @Override
    public StudentScoreService getService() {
        return myService;
    }

}
