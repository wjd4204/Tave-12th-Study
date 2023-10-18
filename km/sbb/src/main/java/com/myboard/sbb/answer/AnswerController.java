package com.myboard.sbb.answer;

import com.myboard.sbb.question.Question;
import com.myboard.sbb.question.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")    // Post 요청만 받아드릴 때 사용
    public String createAnswer(Model model, @PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult){

        Question question = this.questionService.getQuestion(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);

            // 검증에 실패할 경우 다시 답변을 등록할 수 있는 "question_detail" 템플릿으로 렌더링하게 함
            return "question_detail";
        }
        this.answerService.create(question,answerForm.getContent());
        return String.format("redirect:/question/detail/%s",id);

    }
}
