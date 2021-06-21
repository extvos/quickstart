package plus.extvos.example.controller;

import plus.extvos.example.entity.Student;
import plus.extvos.example.service.StudentService;
import plus.extvos.restlet.controller.BaseController;
import plus.extvos.restlet.controller.BaseROController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 学生信息
 *
 * @author Mingcai SHEN
 */
@RestController
@RequestMapping("/example/student")
@Api(tags = {"学生信息"})
public class StudentController extends BaseController<Student, StudentService> {

    @Autowired
    private StudentService myService;

    @Override
    public StudentService getService() {
        return myService;
    }

    @Override
    public String[] defaultExcludes() {
        return new String[]{"age"};
    }
}
