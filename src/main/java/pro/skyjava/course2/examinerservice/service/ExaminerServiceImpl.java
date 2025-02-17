package pro.skyjava.course2.examinerservice.service;


import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.Exception.QuestionAmountException;
import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService service;
    Random random = new Random();

    public ExaminerServiceImpl(QuestionService service) {
        this.service = service;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > service.getAllQuestionsAndAnswers().size()) {
            throw new QuestionAmountException();
        }
        if (service.getAllQuestionsAndAnswers().size() == amount) {
            return service.getAllQuestionsAndAnswers();

        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(service.getRandomQuestion());
        }
        return result;
    }
}
