package plus.extvos.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@Restlet(deletable = false, updatable = true)
@ApiModel("学生信息")
public class Student {
    @TableId(type = IdType.AUTO)
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "id", example = "0")
    private Long id;

    @NotBlank(message = "name of student can not be empty", groups = {OnCreate.class})
    @TableField(value = "name", jdbcType = JdbcType.VARCHAR)
    @ApiModelProperty(value = "名")
    private String name;

    @Pattern(regexp = "F|M", message = "gender of student must be F or M", groups = {OnCreate.class})
    @ApiModelProperty(value = "性别")
    private String gender;

    @Pattern(regexp = "1[0-9]{10}", message = "invalid phone number", groups = {OnCreate.class})
    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @NotBlank(message = "family name of student can not be empty", groups = {OnCreate.class})
    @ApiModelProperty(value = "姓")
    private String familyName;

    @Max(value = 100, message = "age can not lager than 100", groups = {OnCreate.class})
    @Min(value = 1, message = "age can not less than 1", groups = {OnCreate.class})
    @ApiModelProperty(value = "年龄", example = "0")
    private Integer age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "birthday can not be null", groups = {OnCreate.class})
    @ApiModelProperty(value = "生日")
    private Date birthday;
}
