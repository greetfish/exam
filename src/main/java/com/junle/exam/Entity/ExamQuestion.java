package com.junle.exam.Entity;

import javax.persistence.*;

@Entity
public class ExamQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "memberPaperId")
    private MemberPaper memberPaper;    //所属人员试卷
    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;          //所引用的题
    private Integer questionIndex;      //题目在本卷中的序号
    private String memberAnswer = "";       //考生作答答案
    private Float score = 0f;                //本题得分
    private Integer hasBeenAnswered = 0;    //是否已作答，0未作答，1已作答

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MemberPaper getMemberPaper() {
        return memberPaper;
    }

    public void setMemberPaper(MemberPaper memberPaper) {
        this.memberPaper = memberPaper;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(Integer questionIndex) {
        this.questionIndex = questionIndex;
    }

    public String getMemberAnswer() {
        return memberAnswer;
    }

    public void setMemberAnswer(String memberAnswer) {
        this.memberAnswer = memberAnswer;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getHasBeenAnswered() {
        return hasBeenAnswered;
    }

    public void setHasBeenAnswered(Integer hasBeenAnswered) {
        this.hasBeenAnswered = hasBeenAnswered;
    }
}
