package plus.extvos.example.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.extvos.example.entity.StudentSkill;
import plus.extvos.example.service.StudentSkillService;
import plus.extvos.restlet.controller.BaseController;


/**
 * StudentSkill
 *
 * @author Quick Lab
 */
@RestController
@RequestMapping("/example/student-skill")
@Api(tags = {"StudentSkill"})
@RequiresPermissions(value = {"student","school"}, logical = Logical.OR)
public class StudentSkillController extends BaseController<StudentSkill, StudentSkillService> {

    @Autowired
    private StudentSkillService myService;

    @Override
    public StudentSkillService getService() {
        return myService;
    }

}
