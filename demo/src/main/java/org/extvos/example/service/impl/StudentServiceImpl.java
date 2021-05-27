package org.extvos.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.extvos.example.entity.Student;
import org.extvos.example.mapper.StudentMapper;
import org.extvos.example.service.StudentService;
import org.extvos.restlet.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shenmc
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper myMapper;

    @Override
    public StudentMapper getMapper() {
        return myMapper;
    }

    @Override
    public boolean parseQuery(String k, Object v, QueryWrapper<?> wrapper) {

//        if("in_sport_team".equals(k)){ // 10
////            wrapper.inSql()
//            SQL += "id IN (SELECT stuedent_id FROM sport_team_students WHERE team_id = %d)" % (v);
//                return true;
//        }
        return false;
    }
}
