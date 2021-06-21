package plus.extvos.example.service.impl;

import plus.extvos.example.entity.Teacher;
import plus.extvos.example.mapper.TeacherMapper;
import plus.extvos.example.service.TeacherService;
import plus.extvos.restlet.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 教师信息
 *
 * @author Mingcai SHEN
 */
@Service
public class TeacherServiceImpl extends BaseServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper myMapper;

    @Override
    public TeacherMapper getMapper() {
        return myMapper;
    }

}
