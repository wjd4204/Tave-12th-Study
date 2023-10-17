package com.myboard.sbb.question;

import com.myboard.sbb.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository -> 인터페이스로 작성
public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
