package org.extvos.example.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.extvos.example.entity.Student;

import java.sql.Date;

/**
 * @author Mingcai SHEN
 */
public class StudentVO {
    private String name;

    private String gender;

    private String phoneNumber;

    private String familyName;

    private Integer age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;

    public StudentVO(Student s) {
        name = s.getName();
        gender = s.getGender();
        phoneNumber = s.getPhoneNumber();
        familyName = s.getFamilyName();
        age = s.getAge();
    }

    @Override
    public String toString() {
        return "Student: " + name + " " + familyName + "(" + gender + ")";
    }
}
