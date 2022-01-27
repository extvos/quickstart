package plus.extvos.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@TableName(value = "example_student_skills", autoResultMap = true)
@Data
public class StudentSkill {
    @TableId(type = IdType.NONE)
    @TableField(value = "student_id")
    private Long studentId;

    @TableField(value = "skills", typeHandler = org.apache.ibatis.type.ObjectTypeHandler.class)
    private Map<String, Object> skills;

    @TableField(value = "scores", typeHandler = org.apache.ibatis.type.ArrayTypeHandler.class)
    private Integer[] scores;

    @TableField(value = "habits", typeHandler = org.apache.ibatis.type.ArrayTypeHandler.class)
    private String[] habits;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @TableField(value = "created")
    private Timestamp created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @TableField(value = "updated")
    private Timestamp updated;
}
