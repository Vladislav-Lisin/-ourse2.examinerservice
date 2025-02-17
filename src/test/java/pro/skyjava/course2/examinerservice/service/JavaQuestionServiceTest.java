package pro.skyjava.course2.examinerservice.service;

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
        // Arrange
        Question question1 = javaQuestionService.add("Q1", "A1");
        Question question2 = javaQuestionService.add("Q2", "A2");

        // Act
        Collection<Question> result = javaQuestionService.getAllQuestionsAndAnswers();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(question1));
        assertTrue(result.contains(question2));
    }

    @Test
    void getRandomQuestion_ShouldReturnRandomQuestion() {
        // Arrange
        Question question1 = javaQuestionService.add("Q1", "A1");
        Question question2 = javaQuestionService.add("Q2", "A2");

        // Act
        Question result = javaQuestionService.getRandomQuestion();

        // Assert
        assertNotNull(result);
        assertTrue(result.equals(question1) || result.equals(question2));
    }

    @Test
    void getRandomQuestion_ShouldReturnNullIfNoQuestionsExist() {
        Question result = javaQuestionService.getRandomQuestion();
        assertNull(result);
    }
}