package com.junle.exam.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Integer id;
    private String courseName;
    private Integer countQuestion_A=0;//单选题
    private Integer countQuestion_B=0;//多选题
    private Integer countQuestion_C=0;//判断题
    private Integer countQuestion_D=0;//简单题

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCountQuestion_A() {
        return countQuestion_A;
    }

    public void setCountQuestion_A(Integer countQuestion_A) {
        this.countQuestion_A = countQuestion_A;
    }

    public Integer getCountQuestion_B() {
        return countQuestion_B;
    }

    public void setCountQuestion_B(Integer countQuestion_B) {
        this.countQuestion_B = countQuestion_B;
    }

    public Integer getCountQuestion_C() {
        return countQuestion_C;
    }

    public void setCountQuestion_C(Integer countQuestion_C) {
        this.countQuestion_C = countQuestion_C;
    }

    public Integer getCountQuestion_D() {
        return countQuestion_D;
    }

    public void setCountQuestion_D(Integer countQuestion_D) {
        this.countQuestion_D = countQuestion_D;
    }
}
