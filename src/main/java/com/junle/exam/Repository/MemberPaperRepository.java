package com.junle.exam.Repository;

import com.junle.exam.Entity.Member;
import com.junle.exam.Entity.MemberPaper;
import com.junle.exam.Entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberPaperRepository extends JpaRepository<MemberPaper, Integer> {
    void deleteAllByPaper(Paper paper);

    Iterable<MemberPaper> getAllByPaper(Paper paper);
    Iterable<MemberPaper> getAllByMember(Member member);

    @Query("select mp from MemberPaper mp where mp.member.id = ?1 and mp.paper.paperState =?2 and mp.memberPaperState <2")
    Iterable<MemberPaper> getAllByMemberAndPaperState(Integer memberId, Integer memberPaperState);

    @Query("select mp from MemberPaper mp where mp.member.examNumber = ?1 and mp.paper.paperState =?2 and mp.memberPaperState <2")
    Iterable<MemberPaper> getAllByExamNumberAndPaperState(String examNumber, Integer paperState);
}
