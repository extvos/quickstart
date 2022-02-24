package plus.extvos.example.service.impl;

import plus.extvos.example.entity.StudentSkill;
import plus.extvos.example.mapper.StudentSkillMapper;
import plus.extvos.example.service.StudentSkillService;
import plus.extvos.restlet.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentSkill
 *
 * @author Quick Lab
 */
@Service
public class StudentSkillServiceImpl extends BaseServiceImpl<StudentSkillMapper, StudentSkill> implements StudentSkillService {

    @Autowired
    private StudentSkillMapper myMapper;

    @Override
    public StudentSkillMapper getMapper() {
        return myMapper;
    }

}
