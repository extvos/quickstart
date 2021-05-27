package org.extvos.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;

/**
 * @author shenmc
 */
@TableName("example_students")
@Data
public class Student {
    @TableId(type = IdType.AUTO)
    private long id;

    private String name;

    private String gender;

    private String phoneNumber;

    private String familyName;

    private int age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
}
