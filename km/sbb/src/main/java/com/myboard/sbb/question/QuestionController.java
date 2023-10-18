package com.myboard.sbb.question;

import com.myboard.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question",question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }
    @PostMapping("/create")
    // 메서드이름이 같지만 오버로딩(매개변수 달라야함)
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(),questionForm.getContent());  // 질문 데이터 저장
        return "redirect:/question/list";   // 질문 저장 후 질문 목록 페이지로 이동
    }
}










