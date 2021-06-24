package plus.extvos.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import plus.extvos.restlet.annotation.Restlet;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

/**
 * @author Mingcai SHEN
 */
@TableName("example_students")
@Data
@Restlet(deletable = false, updatable = false)
public class Student {
    @TableId(type = IdType.AUTO)
    @TableField(fill = FieldFill.INSERT)
    private Long id;

    @NotBlank
    @TableField(value = "name", jdbcType = JdbcType.VARCHAR)
    private String name;

    private String gender;

    private String phoneNumber;

    private String familyName;

    @Max(100)
    @Min(1)
    private Integer age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
}
