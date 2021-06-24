package plus.extvos.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.extvos.example.entity.StudentScore;
import plus.extvos.example.mapper.StudentScoreMapper;
import plus.extvos.example.service.StudentScoreService;
import plus.extvos.restlet.service.impl.BaseServiceImpl;

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
