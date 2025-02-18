package pro.skyjava.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Service
public class JavaQuestionService implements QuestionService {
    Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question javaQuestion = new Question(question, answer);
        questions.add(javaQuestion);
        return javaQuestion;
    }

    @Override
    public String remove(String question, String answer) {
        if (questions.remove(new Question(question, answer))) {
            return "Пользователь успешно удалён";
        }
        return null;
    }

    public Collection<Question> getAllQuestionsAndAnswers() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("Список вопросов пуст");
        }
        int item = new Random().nextInt(questions.size());
        int i = 0;
        for (Question obj : questions) {
            if (i == item) {
                return obj;
            }
            i++;
        }
        throw new IllegalStateException("Вопрос не найден.");
    }
}







