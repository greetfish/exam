package com.junle.exam.Entity;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    //考号
    private String examNumber;
    //备注\
    private String remarks;
    //
    @ManyToOne
    @JoinColumn(name = "memberGroupId")
    MemberGroup memberGroup;

    public MemberGroup getMemberGroup() {
        return memberGroup;
    }

    public void setMemberGroup(MemberGroup memberGroup) {
        this.memberGroup = memberGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExamNumber() {
        return examNumber;
    }

    public void setExamNumber(String examNumber) {
        this.examNumber = examNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
