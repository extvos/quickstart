package org.extvos.example.controller;

import org.extvos.example.entity.Student;
import org.extvos.example.service.StudentService;
import org.extvos.restlet.Assert;
import org.extvos.restlet.controller.BaseController;
import org.extvos.restlet.exception.RestletException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * @author shenmc
 */
@RestController
@RequestMapping("/students")
@Api(tags = {"学生数据管理"})
public class StudentController extends BaseController<Student, StudentService> {

    @Override
    public Student preInsert(Student entity) throws RestletException {
        Assert.notEmpty(entity.getName(), RestletException.badRequest("name can not be null or empty"));
        return entity;
    }

    @Override
    public void postUpdate(Serializable id, Student entity) throws RestletException {
        if (entity.getAge() < 10) {
            throw RestletException.badRequest("age can not less than 10");
        }
    }

    @Override
    public List<Student> postSelect(List<Student> entities) throws RestletException {
        return entities;
    }

    @Autowired
    private StudentService studentService;

    @Override
    public StudentService getService() {
        return studentService;
    }

    @Override
    public Student postSelect(Student s) throws RestletException {
        return s;
    }
}
