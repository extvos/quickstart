package org.extvos.example.controller;

import org.extvos.example.entity.Teacher;
import org.extvos.example.service.TeacherService;
import org.extvos.restlet.controller.BaseController;
import org.extvos.restlet.controller.BaseROController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 教师信息
 *
 * @author Quick Lab
 */
@RestController
@RequestMapping("/example/teacher")
@Api(tags = {"教师信息"})
public class TeacherController extends BaseController<Teacher, TeacherService> {

    @Autowired
    private TeacherService myService;

    @Override
    public TeacherService getService() {
        return myService;
    }

}
