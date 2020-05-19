package com.junle.exam.Entity;

import javax.persistence.*;

@Entity
public class Paper {
    @Id
    @GeneratedValue
    private Integer id;
    private String paperName;
    //考核的科目
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "memberGroupId")
    MemberGroup memberGroup;
    //试卷生成时间
    String generateTime;
    //考试时长（分钟）
    private Integer timeLimit;
    //总分
    private Integer totalScore;
    //允许超时
    private Boolean allowTimeOut = false;
    //试卷状态
    private Integer paperState = 0;       //0待考试卷 ； 1正在考试试卷 ； 2考试结束试卷  ； 3已归档试卷
    //随机抽题
    private Boolean atRandom = true;

    //抽题策略
    private Integer countQuestionTypeA; //单选题数量
    private Integer countQuestionTypeB; //多选题数量
    private Integer countQuestionTypeC; //判断题数量
    private Integer countQuestionTypeD; //简答题数量

    private Float scoreQuestionTypeA;   //单选题分值
    private Float scoreQuestionTypeB;   //多选题分值
    private Float scoreQuestionTypeC;   //判断题分值
    private Float scoreQuestionTypeD;   //简答题分值

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Boolean getAllowTimeOut() {
        return allowTimeOut;
    }

    public void setAllowTimeOut(Boolean allowTimeOut) {
        this.allowTimeOut = allowTimeOut;
    }

    public Integer getPaperState() {
        return paperState;
    }

    public void setPaperState(Integer paperState) {
        this.paperState = paperState;
    }

    public Boolean getAtRandom() {
        return atRandom;
    }

    public String getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(String generateTime) {
        this.generateTime = generateTime;
    }

    public void setAtRandom(Boolean atRandom) {
        this.atRandom = atRandom;
    }

    public Integer getCountQuestionTypeA() {
        return countQuestionTypeA;
    }

    public void setCountQuestionTypeA(Integer countQuestionTypeA) {
        this.countQuestionTypeA = countQuestionTypeA;
    }

    public Integer getCountQuestionTypeB() {
        return countQuestionTypeB;
    }

    public void setCountQuestionTypeB(Integer countQuestionTypeB) {
        this.countQuestionTypeB = countQuestionTypeB;
    }

    public Integer getCountQuestionTypeC() {
        return countQuestionTypeC;
    }

    public void setCountQuestionTypeC(Integer countQuestionTypeC) {
        this.countQuestionTypeC = countQuestionTypeC;
    }

    public Integer getCountQuestionTypeD() {
        return countQuestionTypeD;
    }

    public void setCountQuestionTypeD(Integer countQuestionTypeD) {
        this.countQuestionTypeD = countQuestionTypeD;
    }

    public Float getScoreQuestionTypeA() {
        return scoreQuestionTypeA;
    }

    public void setScoreQuestionTypeA(Float scoreQuestionTypeA) {
        this.scoreQuestionTypeA = scoreQuestionTypeA;
    }

    public Float getScoreQuestionTypeB() {
        return scoreQuestionTypeB;
    }

    public void setScoreQuestionTypeB(Float scoreQuestionTypeB) {
        this.scoreQuestionTypeB = scoreQuestionTypeB;
    }

    public Float getScoreQuestionTypeC() {
        return scoreQuestionTypeC;
    }

    public void setScoreQuestionTypeC(Float scoreQuestionTypeC) {
        this.scoreQuestionTypeC = scoreQuestionTypeC;
    }

    public Float getScoreQuestionTypeD() {
        return scoreQuestionTypeD;
    }

    public void setScoreQuestionTypeD(Float scoreQuestionTypeD) {
        this.scoreQuestionTypeD = scoreQuestionTypeD;
    }

    public MemberGroup getMemberGroup() {
        return memberGroup;
    }

    public void setMemberGroup(MemberGroup memberGroup) {
        this.memberGroup = memberGroup;
    }
}
