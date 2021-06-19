package org.extvos.example.service.impl;

import org.extvos.example.entity.Student;
import org.extvos.example.mapper.StudentMapper;
import org.extvos.example.service.StudentService;
import org.extvos.restlet.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生信息
 *
 * @author Quick Lab
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
