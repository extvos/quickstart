package org.extvos.example.service.impl;

import org.extvos.example.entity.Teacher;
import org.extvos.example.mapper.TeacherMapper;
import org.extvos.example.service.TeacherService;
import org.extvos.restlet.service.impl.BaseServiceImpl;
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
