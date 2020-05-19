package com.junle.exam.Services;

import com.junle.exam.Entity.*;
import com.junle.exam.Repository.*;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManageService {
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
    @Autowired
    SystemInfoRepository systemInfoRepository;
    private String systemName;

    public List<Course> ListAllCourse() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return courseRepository.findAll(sort);
    }

    public MsgRespone saveOneCourse(String courseName) {
        MsgRespone msgRespone = new MsgRespone();
        Course temp = courseRepository.getByCourseName(courseName);
        if (temp != null) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("该科目名已存在");
            return msgRespone;
        }
        Course course = new Course();
        course.setCourseName(courseName);
        courseRepository.save(course);
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("添加科目：" + courseName + "成功");
        return msgRespone;
    }

    public MsgRespone questionFileUpload(MultipartFile file, Integer questionType, Integer courseId) {
        int i = 0;
        MsgRespone msgRespone = new MsgRespone();
        String path = "";
        path = "/C:/springboot/";
        File target = new File(path, file.getOriginalFilename());
        try {
            file.transferTo(target);
        } catch (Exception e) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("服务器获取文件保存路径失败");
            return msgRespone;
        }
        try {
            i = startReadQuestion(target, questionType, courseId);
        } catch (IOException e) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("服务器题库内容读取失败");
            return msgRespone;
        }
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("成功导入" + i + "道题");
        return msgRespone;
    }

    @Transactional
    public int startReadQuestion(File file, Integer questionType, Integer courseId) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader read = new InputStreamReader(fileInputStream, "gb2312");
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineText = null;
        int i = 0;//记录导入的题数
        Question question = new Question();
        Course course = courseRepository.getOne(courseId);
        while ((lineText = bufferedReader.readLine()) != null) {
            if (lineText.startsWith("题目：")) {
                //设置题目的基本属性
                question.setCourse(course);
                question.setQuestionType(questionType);
                question.setQuestionContent(lineText.substring(3));
            } else if (lineText.startsWith("答案：")) {
                question.setQuestionAnswer(lineText.substring(3));
                questionRepository.save(question);
                i++;
                question = new Question();
            } else {
                question.setQuestionContent(question.getQuestionContent() + "<br/>" + lineText);
            }
        }
        read.close();
        file.delete();
        if (i > 0) {
            if (questionType == 1)
                course.setCountQuestion_A(course.getCountQuestion_A() + i);
            else if (questionType == 2)
                course.setCountQuestion_B(course.getCountQuestion_B() + i);
            else if (questionType == 3)
                course.setCountQuestion_C(course.getCountQuestion_C() + i);
            else if (questionType == 4)
                course.setCountQuestion_D(course.getCountQuestion_D() + i);
            courseRepository.save(course);
        }
        return i;
    }

    public List<MemberGroup> getAllMemberGroup() {
        List<MemberGroup> memberGroups;
        memberGroups = memberGroupRepository.findAll();
        return memberGroups;
    }

    public Course getCourseById(Integer courseId) {
        Course course = courseRepository.findById(courseId).get();
        return course;
    }

    @Transactional
    public MsgRespone newPaper(String paperName, Integer courseId, Integer memberGroupId, Integer score, Integer timeLimit,
                               Integer countQuestionTypeA, Integer countQuestionTypeB, Integer countQuestionTypeC,
                               Integer countQuestionTypeD, Float scoreQuestionTypeA, Float scoreQuestionTypeB,
                               Float scoreQuestionTypeC, Float scoreQuestionTypeD, Boolean allowTimeOut,
                               Boolean atRandom) {
        MsgRespone msgRespone = new MsgRespone();
        MemberGroup memberGroup = memberGroupRepository.getOne(memberGroupId);
        Course course = courseRepository.getOne(courseId);
        Paper paper = new Paper();
        Paper temp = paperRepository.getByPaperName(paperName);
        if (temp != null) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("试卷名重复(名为【" + paperName + "】的试卷已存在）");
            return msgRespone;
        }
        Date generateTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String timeString = (simpleDateFormat.format(generateTime));
        paper.setGenerateTime(timeString);
        List<MemberPaper> memberPapers = new ArrayList<>();
        List<Member> members = (ArrayList<Member>) memberRepository.findMembersByMemberGroupId(memberGroupId);
        paper.setPaperName(paperName);
        paper.setMemberGroup(memberGroup);
        paper.setCourse(course);
        paper.setTotalScore(score);
        paper.setTimeLimit(timeLimit);
        paper.setAllowTimeOut(allowTimeOut);
        paper.setAtRandom(atRandom);
        paper.setPaperState(0);
        paper.setCountQuestionTypeA(countQuestionTypeA);
        paper.setCountQuestionTypeB(countQuestionTypeB);
        paper.setCountQuestionTypeC(countQuestionTypeC);
        paper.setCountQuestionTypeD(countQuestionTypeD);
        paper.setScoreQuestionTypeA(scoreQuestionTypeA);
        paper.setScoreQuestionTypeB(scoreQuestionTypeB);
        paper.setScoreQuestionTypeC(scoreQuestionTypeC);
        paper.setScoreQuestionTypeD(scoreQuestionTypeD);
        paperRepository.save(paper);
        if (members.size() > 0) {
            for (Member member : members) {
                MemberPaper memberPaper = new MemberPaper();
                memberPaper.setMember(member);
                memberPaper.setMemberPaperState(0);
                memberPaper.setLeftMinute(paper.getTimeLimit());
                memberPaper.setPaper(paper);
                memberPapers.add(memberPaper);
            }
        }
        memberPaperRepository.saveAll(memberPapers);
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("为" + members.size() + "人生成名为【" + paperName + " 】的试卷");
        return msgRespone;
    }


    public List<Member> getAllMemberByMemberGroupId(Integer memberGroupId) {
        List<Member> members = (ArrayList<Member>) memberRepository.findMembersByMemberGroupId(memberGroupId);
        return members;
    }

    public MsgRespone readMemberGroupFile(MultipartFile file, String memberGroupName) {
        int i = 0;
        MsgRespone msgRespone = new MsgRespone();
        String path = "";
        if (memberGroupName.equals("")) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("组名不能为空");
            return msgRespone;
        }
        if (memberGroupRepository.findByGroupName(memberGroupName) != null) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("组名已存在，请重新输入");
            return msgRespone;
        }
        path = "/C:/springboot/";
        File target = new File(path, file.getOriginalFilename());
        try {
            file.transferTo(target);
        } catch (Exception e) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("服务器获取文件保存路径失败");
            return msgRespone;
        }
        try {
            i = startReadMember(target, memberGroupName);
        } catch (IOException e) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("服务器人员文件读取失败");
            return msgRespone;
        }
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("成功导入分组名为" + memberGroupName + "的" + i + "人");
        return msgRespone;
    }

    public Integer startReadMember(File file, String memberGroupName) throws IOException {
        Integer i = 0;
        List<Member> members = new ArrayList<>();
        String fileType = file.getPath().substring(file.getPath().lastIndexOf(".") + 1);
        FileInputStream fileInputStream = new FileInputStream(file);
        MemberGroup memberGroup = new MemberGroup();
        memberGroup.setGroupName(memberGroupName);
        memberGroupRepository.save(memberGroup);
        memberGroup = memberGroupRepository.findByGroupName(memberGroupName);
        try {
            Workbook wb = null;
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(fileInputStream);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(fileInputStream);
            } else {
                return null;
            }
            //读取第一个sheet
            Sheet sheet = wb.getSheetAt(0);
            //第一行为标题
            for (Row row : sheet) {
                Member member = new Member();
                i++;
                if (i > 1) {
                    member.setRemarks(row.getCell(0).getStringCellValue());
                    member.setName(row.getCell(1).getStringCellValue());
                    member.setMemberGroup(memberGroup);
                    members.add(member);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        memberRepository.saveAll(members);
        for (Member m : members) {
            Integer temp = 21000 + m.getId();
            m.setExamNumber(temp.toString());
        }
        memberRepository.saveAll(members);
        memberGroup.setCountMember(members.size());
        memberGroupRepository.save(memberGroup);
        return i;
    }

    public MsgRespone deleteMemberGroup(Integer memberGroupId) {
        MsgRespone msgRespone = new MsgRespone();
        MemberGroup memberGroup = memberGroupRepository.findById(memberGroupId).get();
        List<Member> members = getAllMemberByMemberGroupId(memberGroupId);
        List<Paper> papers = paperRepository.getAllByMemberGroup(memberGroup);
        if (papers.size() > 0) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("删除失败，请先删除该人员组的所有考核");
            return msgRespone;
        }
        memberRepository.deleteAll(members);
        memberGroupRepository.delete(memberGroup);
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("删除该组及其所属人员，共计" + members.size() + "人成功");
        return msgRespone;
    }

    @Transactional
    public List<Member> deleteMemberInGroup(Integer memberId) {
        Member member = memberRepository.findById(memberId).get();
        List<MemberPaper> memberPapers = (List<MemberPaper>) memberPaperRepository.getAllByMember(member);
        if (memberPapers.size() > 0) {
            memberPaperRepository.deleteAll(memberPapers);
        }
        memberRepository.delete(member);
        MemberGroup memberGroup = memberGroupRepository.getOne(member.getMemberGroup().getId());
        memberGroup.setCountMember(memberGroup.getCountMember() - 1);//更新组内人员数量值
        memberGroupRepository.save(memberGroup);
        List<Member> members = (ArrayList<Member>) memberRepository.findMembersByMemberGroupId(member.getMemberGroup().getId());
        return members;
    }

    @Transactional
    public MsgRespone deleteCourse(Integer courseId) {
        MsgRespone msgRespone = new MsgRespone();
        Course course = courseRepository.findById(courseId).get();
        List<Paper> papers = paperRepository.getAllByCourse(course);
        if (papers.size() != 0) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("删除失败，请先删除关联该科目的所有考核");
            return msgRespone;
        }
        deleteQuestionByType(courseId, 1);
        deleteQuestionByType(courseId, 2);
        deleteQuestionByType(courseId, 3);
        deleteQuestionByType(courseId, 4);
        courseRepository.delete(course);
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("成功删除科目：" + course.getCourseName() + "，及其所属题库");
        return msgRespone;
    }

    @Transactional
    public MsgRespone deleteQuestionByType(Integer courseId, Integer questionType) {
        MsgRespone msgRespone = new MsgRespone();
        Course course = courseRepository.getOne(courseId);
        Iterable<Question> questions = questionRepository.findAllByCourse_IdAndAndQuestionType(courseId, questionType);
        int size = 0;
        for (Question q : questions) {
            size++;
        }
        if (size != 0) {
            questionRepository.deleteAll(questions);
            if (questionType == 1) {
                course.setCountQuestion_A(course.getCountQuestion_A() - size);
                msgRespone.setMsgContent("成功删除科目‘" + course.getCourseName() + "’ " + size + " 道单选题");
            } else if (questionType == 2) {
                course.setCountQuestion_B(course.getCountQuestion_B() - size);
                msgRespone.setMsgContent("成功删除科目‘" + course.getCourseName() + "’ " + size + " 道多选题");
            } else if (questionType == 3) {
                course.setCountQuestion_C(course.getCountQuestion_C() - size);
                msgRespone.setMsgContent("成功删除科目‘" + course.getCourseName() + "’ " + size + " 道判断题");
            } else if (questionType == 4) {
                course.setCountQuestion_D(course.getCountQuestion_D() - size);
                msgRespone.setMsgContent("成功删除科目‘" + course.getCourseName() + "’ " + size + " 道简答题");
            }
        } else {
            msgRespone.setMsgContent("无题可删");
        }
        courseRepository.save(course);
        msgRespone.setMsgType(0);
        return msgRespone;
    }

    public List<Paper> getAllPaperByPaperState(Integer paperState) {
        List<Paper> papers;
        if (paperState == 1) {
            papers = paperRepository.getByPaperState(1);
            papers.addAll(paperRepository.getByPaperState(2));
        } else {
            papers = paperRepository.getByPaperState(paperState);
        }
        return papers;
    }

    @Transactional
    public MsgRespone setPaperToExamState(Integer paperId) {
        MsgRespone msgRespone = new MsgRespone();
        Paper paper = paperRepository.getOne(paperId);
        paper.setPaperState(1);
        paperRepository.save(paper);
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("试卷：【" + paper.getPaperName() + "】已启用，考生可选择试卷开考");
        return msgRespone;
    }

    @Transactional
    public MsgRespone deletePaper(Integer paperId) {
        MsgRespone msgRespone = new MsgRespone();
        Paper paper = paperRepository.findById(paperId).get();
        List<MemberPaper> memberPapers = (List<MemberPaper>) memberPaperRepository.getAllByPaper(paper);
        for (MemberPaper memberPaper : memberPapers) {
            List<ExamQuestion> examQuestions = (List<ExamQuestion>) examQuestionRepository.getAllByMemberPaper(memberPaper);
            if (examQuestions.size() > 0) {
                examQuestionRepository.deleteAll(examQuestions);
            }
        }
        memberPaperRepository.deleteAll(memberPapers);
        paperRepository.delete(paper);
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("已成功删除试卷【" + paper.getPaperName() + "】及其相关的数据");
        return msgRespone;
    }

    @Transactional
    public List<MemberPaper> getAllMemberPaperByPaper(Integer paperId) {
        List<MemberPaper> memberPapers;
        Paper paper = paperRepository.findById(paperId).get();
        memberPapers = (List<MemberPaper>) memberPaperRepository.getAllByPaper(paper);
        return memberPapers;
    }

    @Transactional
    public MsgRespone removeMemberPaper(Integer memberPaperId) {
        MsgRespone msgRespone = new MsgRespone();
        MemberPaper memberPaper = memberPaperRepository.findById(memberPaperId).get();
        List<ExamQuestion> questions = (List<ExamQuestion>) examQuestionRepository.getAllByMemberPaper(memberPaper);
        examQuestionRepository.deleteAll(questions);
        memberPaperRepository.delete(memberPaper);
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("移除考生 " + memberPaper.getMember().getName() + " 本次考核记录成功");
        return msgRespone;
    }

    public MsgRespone redoExamForMemberPaper(Integer memberPaperId) {
        MsgRespone msgRespone = new MsgRespone();
        MemberPaper memberPaper = memberPaperRepository.findById(memberPaperId).get();
        List<ExamQuestion> questions = (List<ExamQuestion>) examQuestionRepository.getAllByMemberPaper(memberPaper);
        for (ExamQuestion examQuestion : questions) {
            examQuestion.setMemberAnswer("");
            examQuestion.setHasBeenAnswered(0);
            examQuestion.setScore(0f);
        }
        examQuestionRepository.saveAll(questions);
        memberPaper.setScore(0f);
        memberPaper.setMemberPaperState(1);
        memberPaper.setLeftMinute(memberPaper.getPaper().getTimeLimit());
        memberPaperRepository.save(memberPaper);
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("已对考生 " + memberPaper.getMember().getName() + " 设置重考，分值及时间已重置");
        return msgRespone;
    }

    public void downloadMemberPaperList(Integer paperId) {
        Paper paper = paperRepository.findById(paperId).get();
        List<MemberPaper> memberPapers = (List<MemberPaper>) memberPaperRepository.getAllByPaper(paper);
        String path = "";
        try {
            path = "/C:/springboot/";
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //创建HSSFWorkbook对象(excel的文档对象)
            HSSFWorkbook wb = new HSSFWorkbook();
            // 建立新的sheet对象（excel的表单）
            HSSFSheet sheet = wb.createSheet("sheet1");
            int[] width = {8000, 4000, 4000};
            for (int i = 0; i < 3; i++) {
                sheet.setColumnWidth(i, width[i]);
            }
            // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
            CellStyle readStyle = wb.createCellStyle();
            HSSFFont redFont = wb.createFont();
            //颜色
            redFont.setColor(Font.COLOR_NORMAL);
            //设置字体大小
            redFont.setFontHeightInPoints((short) 12);
            //字体
            // redFont.setFontName("宋体");
            readStyle.setFont(redFont);
            HSSFRow row0 = sheet.createRow(0);
            row0.setHeightInPoints(30);
            // 添加第一行表头
            row0.createCell(0).setCellValue(paper.getPaperName());
            row0.getCell(0).setCellStyle(readStyle);
            // 添加第二行表头
            row0 = sheet.createRow(1);
            row0.setHeightInPoints(20);
            row0.createCell(0).setCellValue("单位");
            row0.createCell(1).setCellValue("姓名");
            row0.createCell(2).setCellValue("考号");
            row0.getCell(0).setCellStyle(readStyle);
            row0.getCell(1).setCellStyle(readStyle);
            row0.getCell(2).setCellStyle(readStyle);

            //添加表中内容
            for (int row = 0; row < memberPapers.size(); row++) {//数据行
                //创建新行
                HSSFRow newrow = sheet.createRow(row + 2);//数据从第三行开始
                newrow.setHeightInPoints(30);
                //获取该行的数据
                newrow.createCell(0).setCellValue(memberPapers.get(row).getMember().getRemarks());
                newrow.createCell(1).setCellValue(memberPapers.get(row).getMember().getName());
                newrow.createCell(2).setCellValue(memberPapers.get(row).getMember().getExamNumber());
                newrow.getCell(0).setCellStyle(readStyle);
                newrow.getCell(1).setCellStyle(readStyle);
                newrow.getCell(2).setCellStyle(readStyle);
            }
            //输出Excel文件1
            FileOutputStream output = new FileOutputStream(path + "考核人员名单.xls");
            wb.write(output);//写入磁盘
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadScoreList(Integer paperId) {
        Paper paper = paperRepository.findById(paperId).get();
        List<MemberPaper> memberPapers = (List<MemberPaper>) memberPaperRepository.getAllByPaper(paper);
        String path = "";
        try {
            path = "/C:/springboot/";
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //创建HSSFWorkbook对象(excel的文档对象)
            HSSFWorkbook wb = new HSSFWorkbook();
            // 建立新的sheet对象（excel的表单）
            HSSFSheet sheet = wb.createSheet("sheet1");
            int[] width = {8000, 4000, 4000};
            for (int i = 0; i < 3; i++) {
                sheet.setColumnWidth(i, width[i]);
            }
            // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
            CellStyle readStyle = wb.createCellStyle();
            HSSFFont redFont = wb.createFont();
            //颜色
            redFont.setColor(Font.COLOR_NORMAL);
            //设置字体大小
            redFont.setFontHeightInPoints((short) 12);
            //字体
            // redFont.setFontName("宋体");
            readStyle.setFont(redFont);
            HSSFRow row0 = sheet.createRow(0);
            row0.setHeightInPoints(30);
            // 添加第一行表头
            row0.createCell(0).setCellValue(paper.getPaperName());
            row0.getCell(0).setCellStyle(readStyle);
            // 添加第二行表头
            row0 = sheet.createRow(1);
            row0.setHeightInPoints(20);
            row0.createCell(0).setCellValue("单位");
            row0.createCell(1).setCellValue("姓名");
            row0.createCell(2).setCellValue("成绩");
            row0.getCell(0).setCellStyle(readStyle);
            row0.getCell(1).setCellStyle(readStyle);
            row0.getCell(2).setCellStyle(readStyle);

            //添加表中内容
            for (int row = 0; row < memberPapers.size(); row++) {//数据行
                //创建新行
                HSSFRow newrow = sheet.createRow(row + 2);//数据从第三行开始
                newrow.setHeightInPoints(30);
                //获取该行的数据
                newrow.createCell(0).setCellValue(memberPapers.get(row).getMember().getRemarks());
                newrow.createCell(1).setCellValue(memberPapers.get(row).getMember().getName());
                newrow.createCell(2).setCellValue(memberPapers.get(row).getScore());
                newrow.getCell(0).setCellStyle(readStyle);
                newrow.getCell(1).setCellStyle(readStyle);
                newrow.getCell(2).setCellStyle(readStyle);
            }
            //输出Excel文件1
            FileOutputStream output = new FileOutputStream(path + "考核人员成绩.xls");
            wb.write(output);//写入磁盘
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public MsgRespone endExamForPaper(Integer paperId) {
        MsgRespone msgRespone = new MsgRespone();
        msgRespone.setMsgType(0);
        Paper paper = paperRepository.findById(paperId).get();
        List<MemberPaper> memberPapers = (List<MemberPaper>) memberPaperRepository.getAllByPaper(paper);
        for (MemberPaper memberPaper : memberPapers) {
            if (memberPaper.getMemberPaperState() == 2) {
                ;
            } else if (memberPaper.getMemberPaperState() == 1) {
                endExaming(memberPaper.getId());
            }
            memberPaper.setMemberPaperState(2);
        }
        paper.setPaperState(2);
        memberPaperRepository.saveAll(memberPapers);
        paperRepository.save(paper);
        msgRespone.setMsgContent("成功结束考核");
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

    public MsgRespone documentPaper(Integer paperId) {
        MsgRespone msgRespone = new MsgRespone();
        Paper paper = paperRepository.findById(paperId).get();
        if (paper.getPaperState() < 2) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("请先结束考核");
            return msgRespone;
        }
        List<MemberPaper> memberPapers = (List<MemberPaper>) memberPaperRepository.getAllByPaper(paper);
        for (MemberPaper memberPaper : memberPapers) {
            memberPaper.setMemberPaperState(3);
        }
        paper.setPaperState(3);
        paperRepository.save(paper);
        memberPaperRepository.saveAll(memberPapers);
        msgRespone.setMsgType(0);
        msgRespone.setMsgContent("成功归档试卷");
        return msgRespone;
    }

    public String getSystemName() {
        SystemInfo systemInfo = systemInfoRepository.getByInfoKey("systemName");
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
            systemInfo.setInfoKey("systemName");
            systemInfo.setInfoValue("某某单位机考系统");
            systemInfoRepository.save(systemInfo);
            systemInfo = new SystemInfo();
            systemInfo.setInfoKey("password");
            systemInfo.setInfoValue("123456");
            systemInfoRepository.save(systemInfo);
        }
        systemInfo = systemInfoRepository.getByInfoKey("systemName");
        return systemInfo.getInfoValue();
    }

    public void changeSystemName(String newSystemName) {
        SystemInfo systemInfo = systemInfoRepository.getByInfoKey("systemName");
        systemInfo.setInfoValue(newSystemName);
        systemInfoRepository.save(systemInfo);
    }

    public String changePassword(String newPassword) {
        SystemInfo systemInfo = systemInfoRepository.getByInfoKey("password");
        systemInfo.setInfoValue(newPassword);
        systemInfoRepository.save(systemInfo);
        return "修改密码成功，新的登录密码是" + newPassword;
    }

    public MsgRespone setNewScore(Integer questionId, Float newScore) {
        MsgRespone msgRespone = new MsgRespone();
        ExamQuestion examQuestion = examQuestionRepository.findById(questionId).get();
        MemberPaper memberPaper = examQuestion.getMemberPaper();
        Float oldScore = examQuestion.getScore();
        Float questionScoreValue = 0f;
        switch (examQuestion.getQuestion().getQuestionType()) {
            case 1: {
                questionScoreValue = examQuestion.getMemberPaper().getPaper().getScoreQuestionTypeA();
                break;
            }
            case 2: {
                questionScoreValue = examQuestion.getMemberPaper().getPaper().getScoreQuestionTypeB();
                break;
            }
            case 3: {
                questionScoreValue = examQuestion.getMemberPaper().getPaper().getScoreQuestionTypeC();
                break;
            }
            case 4: {
                questionScoreValue = examQuestion.getMemberPaper().getPaper().getScoreQuestionTypeD();
                break;
            }
        }
        if (newScore > questionScoreValue) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("更改失败，打分高于题型分值");
            return msgRespone;
        }
        if (newScore < 0) {
            msgRespone.setMsgType(1);
            msgRespone.setMsgContent("更改失败，打分不能低于0");
            return msgRespone;
        } else {
            if (newScore > oldScore) {
                memberPaper.setScore(memberPaper.getScore() + (newScore - oldScore));
                examQuestion.setScore(newScore);
            } else if (newScore < oldScore) {
                memberPaper.setScore(memberPaper.getScore() - (oldScore - newScore));
                examQuestion.setScore(newScore);
            }
            memberPaperRepository.save(memberPaper);
            examQuestionRepository.save(examQuestion);
            msgRespone.setMsgType(0);
            msgRespone.setMsgContent("修改得分成功");
            return msgRespone;
        }

    }
}
