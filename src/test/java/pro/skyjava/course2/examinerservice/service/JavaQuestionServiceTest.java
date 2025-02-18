package pro.skyjava.course2.examinerservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void add_ShouldAddQuestionAndReturnIt() {
        String questionText = "What is Java?";
        String answer = "A programming language";

        Question result = javaQuestionService.add(questionText, answer);

        assertEquals(questionText, result.getQuestion());
        assertEquals(answer, result.getAnswer());
        assertTrue(javaQuestionService.getAllQuestionsAndAnswers().contains(result));
    }

    @Test
    void remove_ShouldRemoveQuestionAndReturnSuccessMessage() {
        String questionText = "What is Java?";
        String answer = "A programming language";
        javaQuestionService.add(questionText, answer);
        String result = javaQuestionService.remove(questionText, answer);

        assertEquals("Пользователь успешно удалён", result);
        assertFalse(javaQuestionService.getAllQuestionsAndAnswers().contains(new Question(questionText, answer)));
    }

    @Test
    void remove_ShouldReturnNullIfQuestionDoesNotExist() {
        String questionText = "What is Java?";
        String answer = "A programming language";
        String result = javaQuestionService.remove(questionText, answer);
        assertNull(result);
    }

    @Test
    void getAllQuestionsAndAnswers_ShouldReturnAllQuestions() {
        Question question1 = javaQuestionService.add("Q1", "A1");
        Question question2 = javaQuestionService.add("Q2", "A2");

        Collection<Question> result = javaQuestionService.getAllQuestionsAndAnswers();

        assertEquals(2, result.size());
        assertTrue(result.contains(question1));
        assertTrue(result.contains(question2));
    }

    @Test
    void getRandomQuestion_ShouldReturnRandomQuestion() {
        Question question1 = javaQuestionService.add("Q1", "A1");
        Question question2 = javaQuestionService.add("Q2", "A2");

        Question result = javaQuestionService.getRandomQuestion();

        assertNotNull(result);
        assertTrue(result.equals(question1) || result.equals(question2));
    }

    @Test
    void getRandomQuestion() {
        javaQuestionService.add("question", "answer");
        Question result = javaQuestionService.getRandomQuestion();
        Assertions.assertEquals(new Question("question", "answer"), result);
    }
}