package plus.extvos.example.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.extvos.common.exception.ResultException;
import plus.extvos.example.entity.Student;
import plus.extvos.example.service.StudentService;
import plus.extvos.excel.controller.BaseExcelController;
import plus.extvos.excel.dto.CellMapper;

import java.util.Arrays;
import java.util.List;


/**
 * 学生信息
 *
 * @author Mingcai SHEN
 */
@RestController
@RequestMapping("/example/student")
@Api(tags = {"学生信息"})
public class StudentController extends BaseExcelController<Student, StudentService> {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService myService;

    private CellMapper[] cellMappers = {
            new CellMapper("familyName", "姓"),
            new CellMapper("name", "名"),
            new CellMapper("gender", "性别"),
            new CellMapper("age", "年龄"),
            new CellMapper("birthday", "生日"),
            new CellMapper("phoneNumber", "电话"),
    };

    @Override
    public StudentService getService() {
        return myService;
    }

    @Override
    public String[] defaultExcludes() {
        return new String[]{"age"};
    }

    @Override
    public List<CellMapper> defaultCellMappers() {
        return Arrays.asList(cellMappers);
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
