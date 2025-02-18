package pro.skyjava.course2.examinerservice.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.service.JavaQuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/add")
    public Question add(@RequestParam String question, @RequestParam String answer){
        return javaQuestionService.add(question, answer);
    }

    @GetMapping("/remove")
    public String remove(@RequestParam String question, @RequestParam String answer){
        return javaQuestionService.remove(question, answer);
    }

    @GetMapping
    public Collection<Question> getAllQuestions(){
        return javaQuestionService.getAllQuestionsAndAnswers();
    }



}
