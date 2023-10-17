package com.myboard.sbb.answer;

import com.myboard.sbb.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;


// Repository -> 인터페이스로 작성

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
