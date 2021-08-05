package plus.extvos.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import plus.extvos.restlet.annotation.Restlet;
import plus.extvos.restlet.intfs.OnCreate;

import javax.validation.constraints.*;
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

    @NotBlank(message = "name of student can not be empty", groups = {OnCreate.class})
    @TableField(value = "name", jdbcType = JdbcType.VARCHAR)
    private String name;

    @Pattern(regexp = "F|M", message = "gender of student must be F or M", groups = {OnCreate.class})
    private String gender;

    @Pattern(regexp = "1[0-9]{10}", message = "invalid phone number", groups = {OnCreate.class})
    private String phoneNumber;

    @NotBlank(message = "family name of student can not be empty", groups = {OnCreate.class})
    private String familyName;

    @Max(value = 100, message = "age can not lager than 100", groups = {OnCreate.class})
    @Min(value = 1, message = "age can not less than 1", groups = {OnCreate.class})
    private Integer age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "birthday can not be null", groups = {OnCreate.class})
    private Date birthday;
}
