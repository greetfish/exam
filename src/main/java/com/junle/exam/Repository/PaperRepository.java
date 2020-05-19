package com.junle.exam.Repository;

import com.junle.exam.Entity.Course;
import com.junle.exam.Entity.MemberGroup;
import com.junle.exam.Entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper,Integer> {
    Paper getByPaperName(String paperName);

    List<Paper> getAllByCourse(Course course);

    List<Paper> getByPaperState(int paperState);

    List<Paper> getAllByMemberGroup(MemberGroup memberGroup);
}
