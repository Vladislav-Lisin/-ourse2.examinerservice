package pro.skyjava.course2.examinerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.service.ExaminerService;

import java.util.Collection;

@RestController
public class ExamController {
    private final ExaminerService service;

    public ExamController(ExaminerService examinerService) {
        this.service = examinerService;
    }


    @GetMapping("/exam/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return service.getQuestions(amount);
    }

}