package plus.extvos.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Mingcai SHEN
 */
@TableName("example_student_scores")
@Data
public class StudentScore {
    @TableId(type = IdType.AUTO)
    @TableField(fill = FieldFill.INSERT)
    private Long id;

    @TableField(value = "student_id")
    private Long studentId;

    @TableField(value = "course")
    private String course;

    @TableField(value = "score")
    private Integer score;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @TableField(value = "created")
    private Timestamp created;
}
