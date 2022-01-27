package plus.extvos.example.controller;

import plus.extvos.example.entity.StudentSkill;
import plus.extvos.example.service.StudentSkillService;
import plus.extvos.restlet.controller.BaseController;
import plus.extvos.restlet.controller.BaseROController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * StudentSkill
 *
 * @author Quick Lab
 */
@RestController
@RequestMapping("/example/student-skill")
@Api(tags = {"StudentSkill"})
public class StudentSkillController extends BaseController<StudentSkill, StudentSkillService> {

    @Autowired
    private StudentSkillService myService;

    @Override
    public StudentSkillService getService() {
        return myService;
    }

}
