package org.extvos.example.service.impl;

import org.extvos.example.entity.StudentScore;
import org.extvos.example.mapper.StudentScoreMapper;
import org.extvos.example.service.StudentScoreService;
import org.extvos.restlet.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生成绩
 *
 * @author Mingcai SHEN
 */
@Service
public class StudentScoreServiceImpl extends BaseServiceImpl<StudentScoreMapper, StudentScore> implements StudentScoreService {

    @Autowired
    private StudentScoreMapper myMapper;

    @Override
    public StudentScoreMapper getMapper() {
        return myMapper;
    }

}
