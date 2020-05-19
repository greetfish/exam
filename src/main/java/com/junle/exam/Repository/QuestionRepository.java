package com.junle.exam.Repository;

import com.junle.exam.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Iterable<Question> findAllByCourse_IdAndAndQuestionType(Integer courseId,Integer questionType);
}
