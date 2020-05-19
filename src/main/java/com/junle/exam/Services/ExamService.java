package com.junle.exam.Services;

import com.junle.exam.Entity.ExamQuestion;
import com.junle.exam.Entity.MemberPaper;
import com.junle.exam.Entity.MsgRespone;
import com.junle.exam.Entity.Question;
import com.junle.exam.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ExamService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    MemberGroupRepository memberGroupRepository;
    @Autowired
    PaperRepository paperRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberPaperRepository memberPaperRepository;
    @Autowired
    ExamQuestionRepository examQuestionRepository;

    public List<MemberPaper> getPaperlistByExamNumber(String examNumber) {
        List<MemberPaper> memberPapers = (List<MemberPaper>) memberPaperRepository.getAllByExamNumberAndPaperState(examNumber, 1);
        return memberPapers;
    }

    @Transactional
    public List<ExamQuestion> startExaming(Integer memberPaperId) {
        MemberPaper memberPaper = memberPaperRepository.findById(memberPaperId).get();
        List<ExamQuestion> examQuestions = new ArrayList<>();
        if (memberPaper.getMemberPaperState() == 0) {
            makeExamQuestion(memberPaper);
            memberPaper.setMemberPaperState(1);
            memberPaperRepository.save(memberPaper);
        }
        examQuestions = (List<ExamQuestion>) examQuestionRepository.getAllByMemberPaper(memberPaper);
        return examQuestions;
    }

    @Transactional
    public void makeExamQuestion(MemberPaper memberPaper) {
        Integer questionIndex = 0;
        if (memberPaper.getPaper().getCountQuestionTypeA() != 0) {
            List<Question> questions = (List<Question>) questionRepository.findAllByCourse_IdAndAndQuestionType(memberPaper.getPaper().getCourse().getId(), 1);
            if (memberPaper.getPaper().getAtRandom()) {
                Collections.shuffle(questions);
            }
            if (questions.size() != 0) {
                List<ExamQuestion> examQuestions = new ArrayList<>();
                for (int i = 0; i < memberPaper.getPaper().getCountQuestionTypeA(); i++) {
                    ExamQuestion examQuestion = new ExamQuestion();
                    examQuestion.setMemberPaper(memberPaper);
                    examQuestion.setQuestion(questions.get(i));
                    examQuestion.setQuestionIndex(questionIndex++);
                    examQuestion.setScore(0f);
                    examQuestions.add(examQuestion);
                }
                examQuestionRepository.saveAll(examQuestions);
            }
        }
        if (memberPaper.getPaper().getCountQuestionTypeB() != 0) {
            List<Question> questions = (List<Question>) questionRepository.findAllByCourse_IdAndAndQuestionType(memberPaper.getPaper().getCourse().getId(), 2);
            if (memberPaper.getPaper().getAtRandom()) {
                Collections.shuffle(questions);
            }
            if (questions.size() != 0) {
                List<ExamQuestion> examQuestions = new ArrayList<>();
                for (int i = 0; i < memberPaper.getPaper().getCountQuestionTypeB(); i++) {
                    ExamQuestion examQuestion = new ExamQuestion();
                    examQuestion.setMemberPaper(memberPaper);
                    examQuestion.setQuestion(questions.get(i));
                    examQuestion.setQuestionIndex(questionIndex++);
                    examQuestion.setScore(0f);
                    examQuestions.add(examQuestion);
                }
                examQuestionRepository.saveAll(examQuestions);
            }
        }
        if (memberPaper.getPaper().getCountQuestionTypeC() != 0) {
            List<Question> questions = (List<Question>) questionRepository.findAllByCourse_IdAndAndQuestionType(memberPaper.getPaper().getCourse().getId(), 3);
            if (memberPaper.getPaper().getAtRandom()) {
                Collections.shuffle(questions);
            }
            if (questions.size() != 0) {
                List<ExamQuestion> examQuestions = new ArrayList<>();
                for (int i = 0; i < memberPaper.getPaper().getCountQuestionTypeC(); i++) {
                    ExamQuestion examQuestion = new ExamQuestion();
                    examQuestion.setMemberPaper(memberPaper);
                    examQuestion.setQuestion(questions.get(i));
                    examQuestion.setQuestionIndex(questionIndex++);
                    examQuestion.setScore(0f);
                    examQuestions.add(examQuestion);
                }
                examQuestionRepository.saveAll(examQuestions);
            }
        }
        if (memberPaper.getPaper().getCountQuestionTypeD() != 0) {
            List<Question> questions = (List<Question>) questionRepository.findAllByCourse_IdAndAndQuestionType(memberPaper.getPaper().getCourse().getId(), 4);
            if (memberPaper.getPaper().getAtRandom()) {
                Collections.shuffle(questions);
            }
            if (questions.size() != 0) {
                List<ExamQuestion> examQuestions = new ArrayList<>();
                for (int i = 0; i < memberPaper.getPaper().getCountQuestionTypeD(); i++) {
                    ExamQuestion examQuestion = new ExamQuestion();
                    examQuestion.setMemberPaper(memberPaper);
                    examQuestion.setQuestion(questions.get(i));
                    examQuestion.setQuestionIndex(questionIndex++);
                    examQuestion.setScore(0f);
                    examQuestions.add(examQuestion);
                }
                examQuestionRepository.saveAll(examQuestions);
            }
        }
    }

    @Transactional
    public MsgRespone saveAnswer(Integer examQuestionId, String memberAnswer) {
        MsgRespone msgRespone = new MsgRespone();
        ExamQuestion examQuestion = examQuestionRepository.findById(examQuestionId).get();
        if (examQuestion.getMemberPaper().getMemberPaperState() == 2) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("保存失败，本次考核已结束");
            return msgRespone;
        }
        //题目类型：1单选，2多选，3判断，4简答
        switch (examQuestion.getQuestion().getQuestionType()) {
            case 1: {
                examQuestion.setMemberAnswer(memberAnswer);
            }
            case 2: {
                if (memberAnswer.length() != examQuestion.getQuestion().getQuestionAnswer().length()) {
                    examQuestion.setMemberAnswer(memberAnswer);
                } else {
                    char[] ch1 = memberAnswer.toCharArray();
                    char[] ch2 = examQuestion.getQuestion().getQuestionAnswer().toCharArray();
                    Arrays.sort(ch1);
                    Arrays.sort(ch2);
                    if (String.valueOf(ch1).equals(String.valueOf(ch2))) {
                        examQuestion.setMemberAnswer(examQuestion.getQuestion().getQuestionAnswer());
                    }
                }
                break;
            }
            case 3: {
                examQuestion.setMemberAnswer(memberAnswer);
                break;
            }
            case 4: {
                examQuestion.setMemberAnswer(memberAnswer);
                break;
            }
        }
        examQuestion.setHasBeenAnswered(1);
        examQuestionRepository.save(examQuestion);
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("保存成功");
        return msgRespone;
    }

    @Transactional
    public String endExaming(Integer memberPaperId) {
        MemberPaper memberPaper = memberPaperRepository.findById(memberPaperId).get();
        Float score = 0f;
        try {
            score = computePaperScore(memberPaper);
        } catch (Exception e) {
            return "计算成绩出错";
        }
        memberPaper.setMemberPaperState(2);
        memberPaper.setScore(score);
        memberPaperRepository.save(memberPaper);
        return score.toString();
    }

    @Transactional
    public Float computePaperScore(MemberPaper memberPaper) {
        Float score = 0f;
        List<ExamQuestion> examQuestions = (List<ExamQuestion>) examQuestionRepository.getAllByMemberPaper(memberPaper);
        for (ExamQuestion examQuestion : examQuestions) {
            if (examQuestion.getMemberAnswer().equals(examQuestion.getQuestion().getQuestionAnswer())) {
                switch (examQuestion.getQuestion().getQuestionType()) {
                    case 1: {
                        examQuestion.setScore(examQuestion.getMemberPaper().getPaper().getScoreQuestionTypeA());
                        break;
                    }
                    case 2: {
                        examQuestion.setScore(examQuestion.getMemberPaper().getPaper().getScoreQuestionTypeB());
                        break;
                    }
                    case 3: {
                        examQuestion.setScore(examQuestion.getMemberPaper().getPaper().getScoreQuestionTypeC());
                        break;
                    }
                    case 4: {
                        examQuestion.setScore(examQuestion.getMemberPaper().getPaper().getScoreQuestionTypeD());
                        break;
                    }
                }
                score += examQuestion.getScore();
            }
        }
        examQuestionRepository.saveAll(examQuestions);
        return score;
    }

    public String rewriteLeftMinute(Integer memberPaperId, Integer leftMinute) {
        MemberPaper memberPaper = memberPaperRepository.findById(memberPaperId).get();
        memberPaper.setLeftMinute(leftMinute);
        memberPaperRepository.save(memberPaper);
        return "修改剩余分钟数成功";
    }
}
