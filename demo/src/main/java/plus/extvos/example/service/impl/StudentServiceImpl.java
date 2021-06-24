package plus.extvos.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.extvos.example.entity.Student;
import plus.extvos.example.mapper.StudentMapper;
import plus.extvos.example.service.StudentService;
import plus.extvos.restlet.service.impl.BaseServiceImpl;

/**
 * 学生信息
 *
 * @author Mingcai SHEN
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper myMapper;

    @Override
    public StudentMapper getMapper() {
        return myMapper;
    }

}
