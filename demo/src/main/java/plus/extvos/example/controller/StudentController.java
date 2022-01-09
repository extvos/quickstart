package plus.extvos.example.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.extvos.common.exception.ResultException;
import plus.extvos.example.entity.Student;
import plus.extvos.example.service.StudentService;
import plus.extvos.restlet.controller.BaseController;
import plus.extvos.restlet.controller.BaseROController;
import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 学生信息
 *
 * @author Mingcai SHEN
 */
@RestController
@RequestMapping("/example/student")
@Api(tags = {"学生信息"})
public class StudentController extends BaseController<Student, StudentService> {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

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

    @Override
    public Student preInsert(Student entity) throws ResultException {
        log.debug(">> preInsert: {}", entity);
//        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(entity);
//        if(!constraintViolations.isEmpty()){
//            String errorMsg = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
//            throw ResultException.badRequest(errorMsg);
//        }
        return super.preInsert(entity);
    }
}
