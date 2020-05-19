package com.junle.exam.Controller;

import com.junle.exam.Entity.ExamQuestion;
import com.junle.exam.Entity.MemberPaper;
import com.junle.exam.Entity.MsgRespone;
import com.junle.exam.Services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExamController {
    @Autowired
    ExamService examService;

    @PostMapping("/user_examLogin")
    public List<MemberPaper> getPaperlistByExamNumber(String examNumber) {
        List<MemberPaper> memberPapers = examService.getPaperlistByExamNumber(examNumber);
        return memberPapers;
    }

    @PostMapping("/user_startExaming")
    public List<ExamQuestion> startExaming(Integer memberPaperId) {
        List<ExamQuestion> examQuestions = examService.startExaming(memberPaperId);
        return examQuestions;
    }

    @PostMapping("/user_saveAnswer")
    public MsgRespone saveAnswer(Integer examQuestionId, String memberAnswer) {
        MsgRespone msgRespone = examService.saveAnswer(examQuestionId, memberAnswer);
        return msgRespone;
    }

    @PostMapping("/user_endExaming")
    public String endExaming(Integer memberPaperId) {
        String str = examService.endExaming(memberPaperId);
        return str;
    }

    @PostMapping("/user_rewriteLeftMinute")
    public String rewriteLeftMinute(Integer memberPaperId, Integer leftMinute) {
        String str = examService.rewriteLeftMinute(memberPaperId, leftMinute);
        return str;
    }
}
