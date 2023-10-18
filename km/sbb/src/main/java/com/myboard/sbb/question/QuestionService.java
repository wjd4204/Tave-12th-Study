package com.myboard.sbb.question;

import com.myboard.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Service    // 스프링의 서비스로 만들기 위한 어노테이션
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        // page는 조회할 페이지의 번호, 10은 한 패이지에 보여줄 게시물의 갯수를 의미한다
        // 이렇게 할 경우 데이터 전체를 조회하지 않고 해당 페이지의 데이터만 조회하도록 쿼리가 변경됨
        Pageable pageable = PageRequest.of(page,10,Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
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
