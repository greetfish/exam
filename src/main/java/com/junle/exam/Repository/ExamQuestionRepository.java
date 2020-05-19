package com.junle.exam.Repository;

import com.junle.exam.Entity.ExamQuestion;
import com.junle.exam.Entity.MemberPaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Integer> {
    Iterable<ExamQuestion> getAllByMemberPaper(MemberPaper memberPaper);

    void deleteAllByMemberPaper(MemberPaper memberPaper);
}
