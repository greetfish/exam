package com.junle.exam.Entity;

import javax.persistence.*;

@Entity
public class MemberPaper {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "examPaperId")
    private Paper paper;
    private Integer memberPaperState = 0;   // 0未开考未抽题 ，1已抽题开考 ，2已交卷 ，3已归档
    private Float score = 0f;               //得分
    private Integer questionIndex = 1;      //当前答题指向第几题
    private Boolean timeOut = false;        //是否超时
    private Integer leftMinute = 0;         //剩余考试时间(分钟)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Integer getMemberPaperState() {
        return memberPaperState;
    }

    public void setMemberPaperState(Integer memberPaperState) {
        this.memberPaperState = memberPaperState;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(Integer questionIndex) {
        this.questionIndex = questionIndex;
    }

    public Boolean getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Boolean timeOut) {
        this.timeOut = timeOut;
    }

    public Integer getLeftMinute() {
        return leftMinute;
    }

    public void setLeftMinute(Integer leftMinute) {
        this.leftMinute = leftMinute;
    }
}
