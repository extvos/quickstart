package plus.extvos.example.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.extvos.example.entity.Teacher;
import plus.extvos.example.service.TeacherService;
import plus.extvos.restlet.controller.BaseController;
import plus.extvos.restlet.controller.BaseROController;


/**
 * 教师信息
 *
 * @author Mingcai SHEN
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
