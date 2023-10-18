package com.myboard.sbb.question;

import com.myboard.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service    // 스프링의 서비스로 만들기 위한 어노테이션
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    // Question 데이터를 조회하기 위한 메서드
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }


    // 제목과 내용을 입력으로 하여 질문 데이터를 저장하는 create 메서드
    // Question 컨트롤러에서 이 서비스를 사용할 수 있도록 해야함 -> this.questionService.create(subject, content);
    public void create(String subject,String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
}
