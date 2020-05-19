package com.junle.exam.Controller;

import com.junle.exam.Entity.*;
import com.junle.exam.Services.ExamService;
import com.junle.exam.Services.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
public class ManagerController {
    @Autowired
    ManageService manageService;

    @Autowired
    ExamService examService;

    @CrossOrigin
    @GetMapping("/test")
    public String test() {
        return "连接后台服务器正常";
    }

    @CrossOrigin
    @GetMapping("/user_getSystemName")
    public String getSystemName() {
        return manageService.getSystemName();
    }

    @CrossOrigin
    @GetMapping("/api_getAllCourse")
    public List<Course> getAllCourse() {
        return manageService.ListAllCourse();
    }

    @PostMapping("/api_newCourse")
    public MsgRespone newCourse(@RequestParam String courseName) {
        return manageService.saveOneCourse(courseName);
    }

    @PostMapping("/api_uploadQuestionFile")
    public MsgRespone uploadQuestionFile(MultipartFile file, Integer questionType, Integer courseId) {
        MsgRespone msgRespone = manageService.questionFileUpload(file, questionType, courseId);
        return msgRespone;
    }

    @PostMapping("/api_uploadMemberGroupFile")
    public MsgRespone uploadMemberGroupFile(MultipartFile file, String memberGroupName) {
        MsgRespone msgRespone;
        msgRespone = manageService.readMemberGroupFile(file, memberGroupName);
        return msgRespone;
    }

    @PostMapping("/api_changeSystemName")
    public String changeSystemName(String newSystemName) {
        manageService.changeSystemName(newSystemName);
        return "修改系统名称成功！";
    }

    @PostMapping("/api_changePassword")
    public String changePassword(String newPassword) {
        String str = manageService.changePassword(newPassword);
        return str;
    }

    @PostMapping("/api_getCourseById")
    public Course getCourseById(Integer courseId) {
        Course course = manageService.getCourseById(courseId);
        return course;
    }

    @PostMapping("/api_newPaper")
    public MsgRespone newPaper(String paperName, Integer courseId, Integer memberGroupId, Integer score, Integer timeLimit,
                               Integer countQuestionTypeA, Integer countQuestionTypeB, Integer countQuestionTypeC,
                               Integer countQuestionTypeD, Float scoreQuestionTypeA, Float scoreQuestionTypeB,
                               Float scoreQuestionTypeC, Float scoreQuestionTypeD, Boolean allowTimeOut,
                               Boolean atRandom) {
        MsgRespone msgRespone = manageService.newPaper(paperName, courseId, memberGroupId, score, timeLimit, countQuestionTypeA,
                countQuestionTypeB, countQuestionTypeC, countQuestionTypeD, scoreQuestionTypeA, scoreQuestionTypeB,
                scoreQuestionTypeC, scoreQuestionTypeD, allowTimeOut, atRandom);
        return msgRespone;
    }

    @GetMapping("/api_getAllMemberGroup")
    public List<MemberGroup> getAllMemberGroup() {
        return manageService.getAllMemberGroup();
    }

    @PostMapping("/api_getAllMemberByMemberGroupId")
    public List<Member> getAllMemberByMemberGroupId(Integer memberGroupId) {
        return manageService.getAllMemberByMemberGroupId(memberGroupId);
    }

    @PostMapping("/api_memberDelete")
    public List<Member> deleteMember(Integer memberId) {
        return manageService.deleteMemberInGroup(memberId);
    }

    @PostMapping("/api_deleteCourse")
    public MsgRespone deleteCourse(Integer courseId) {
        MsgRespone msgRespone = manageService.deleteCourse(courseId);
        return msgRespone;
    }
    @PostMapping("/api_deleteMemberGroup")
    public MsgRespone deleteMemberGroup(Integer memberGroupId) {
        MsgRespone msgRespone = manageService.deleteMemberGroup(memberGroupId);
        return msgRespone;
    }
    @PostMapping("/api_deleteQuestionByType")
    public MsgRespone deleteQuestionByType(Integer courseId, Integer questionType) {
        MsgRespone msgRespone = manageService.deleteQuestionByType(courseId, questionType);
        return msgRespone;
    }

    @PostMapping("/api_getAllPaper")
    public List<Paper> getAllPaper(Integer paperState) {
        List<Paper> papers;
        papers = manageService.getAllPaperByPaperState(paperState);
        return papers;
    }

    @PostMapping("/api_deletePaper")
    public MsgRespone deletePaper(Integer paperId) {
        MsgRespone msgRespone;
        msgRespone = manageService.deletePaper(paperId);
        return msgRespone;
    }

    @PostMapping("/api_setPaperToExam")
    public MsgRespone setPaperToExam(Integer paperId) {
        MsgRespone msgRespone;
        msgRespone = manageService.setPaperToExamState(paperId);
        return msgRespone;
    }

    @PostMapping("/api_getAllMemberPaper")
    public List<MemberPaper> getAllMemberPaper(Integer paperId) {
        List<MemberPaper> memberPapers;
        memberPapers = manageService.getAllMemberPaperByPaper(paperId);
        return memberPapers;
    }

    @PostMapping("/api_endExamForMemberPaper")
    public MsgRespone endExamForMemberPaper(Integer memberPaperId) {
        MsgRespone msgRespone = new MsgRespone();
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("结束考试成功，当前得分为" + examService.endExaming(memberPaperId) + "分");
        return msgRespone;
    }

    @PostMapping("/api_endExamForPaper")
    public MsgRespone endExamForPaper(Integer paperId) {
        MsgRespone msgRespone;
        msgRespone = manageService.endExamForPaper(paperId);
        return msgRespone;
    }

    @PostMapping("/api_documentPaper")
    public MsgRespone documentPaper(Integer paperId) {
        MsgRespone msgRespone;
        msgRespone = manageService.documentPaper(paperId);
        return msgRespone;
    }

    @PostMapping("/api_setNewScoreForQuestion")
    public MsgRespone setNewScoreForQuestion(Integer questionId,Float newScore) {
        MsgRespone msgRespone;
        msgRespone = manageService.setNewScore(questionId,newScore);
        return msgRespone;
    }

    @PostMapping("/api_removeMemberPaper")
    public MsgRespone removeMemberPaper(Integer memberPaperId) {
        MsgRespone msgRespone = new MsgRespone();
        msgRespone = manageService.removeMemberPaper(memberPaperId);
        return msgRespone;
    }

    @PostMapping("/api_redoExamForMemberPaper")
    public MsgRespone redoExamForMemberPaper(Integer memberPaperId) {
        MsgRespone msgRespone = new MsgRespone();
        msgRespone = manageService.redoExamForMemberPaper(memberPaperId);
        return msgRespone;
    }

    @PostMapping("/api_downloadMemberPaperList")
    public void downloadMemberPaperList(Integer paperId, HttpServletResponse response) {
        manageService.downloadMemberPaperList(paperId);
        String filename = "考核人员名单.xls";
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        String path = "";
        InputStream in;
        OutputStream out;
        try {
            path = "/C:/springboot/";
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fullFileName = path + filename;
        try {
            in = new FileInputStream(fullFileName);
            out = response.getOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(fullFileName);

    }

    @PostMapping("/api_downloadScore")
    public void downloadScore(Integer paperId, HttpServletResponse response) {
        manageService.downloadScoreList(paperId);
        String filename = "考核人员成绩.xls";
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        String path = "";
        InputStream in;
        OutputStream out;
        String fullFileName;
        try {
            path = path = "/C:/springboot/";
            fullFileName = path + filename;
            in = new FileInputStream(fullFileName);
            out = response.getOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
