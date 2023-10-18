package com.myboard.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor    //  롬복이 제공하는 애너테이션으로 final이 붙은 속성을 포함하는 "생성자를 자동으로 생성하는 역할"
@Controller
public class QuestionController {

    // questionRepository 객체가 자동으로 주입된다.
  //  private final QuestionRepository questionRepository;

    // 생성자 방식으로 의존성 주입
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {   // Model 객체는 따로 생성할 필요없이 컨트롤러 메서드의 매개변수로 지정하기만 하면 자동으로 Model 객체를 생성해줌
        List<Question> questionList = this.questionService.getList();

        // model 객체는 자바클래스와 템플릿간의 연결고리 역할을 한다.
        // model 객체에 값을(questionList)을 담아두면 템플릿에서 그 값을 사용할 수 있다.
        model.addAttribute("questionList",questionList);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question",question);
        return "question_detail";
    }
}
