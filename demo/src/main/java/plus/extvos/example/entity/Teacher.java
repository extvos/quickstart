package plus.extvos.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Date;

/**
 * @author Mingcai SHEN
 */
@TableName("example_teachers")
@Data
@ApiModel("教师信息")
public class Teacher {
    @TableId(type = IdType.AUTO)
    @TableField(fill = FieldFill.INSERT)
    private Integer id;

    private String name;

    private String gender;

    private String phoneNumber;

    private String familyName;

    private Integer age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
}
