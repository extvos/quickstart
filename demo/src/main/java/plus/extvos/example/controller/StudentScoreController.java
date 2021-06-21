package plus.extvos.example.controller;

import plus.extvos.example.entity.StudentScore;
import plus.extvos.example.service.StudentScoreService;
import plus.extvos.restlet.controller.BaseController;
import plus.extvos.restlet.controller.BaseROController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 学生成绩
 *
 * @author Mingcai SHEN
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
