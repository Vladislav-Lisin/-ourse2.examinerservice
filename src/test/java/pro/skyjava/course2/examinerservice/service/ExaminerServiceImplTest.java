package pro.skyjava.course2.examinerservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.skyjava.course2.examinerservice.Exception.QuestionAmountException;
import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestions_WhenAmountIsGreaterThanAvailable_ShouldThrowException() {
        when(questionService.getAllQuestionsAndAnswers()).thenReturn(Set.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2")
        ));

        int amount = 3;
        assertThrows(QuestionAmountException.class, () -> examinerService.getQuestions(amount));
    }

    @Test
    void getQuestions_WhenAmountEqualsAvailable_ShouldReturnAllQuestions() {
        Set<Question> questions = Set.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2")
        );
        when(questionService.getAllQuestionsAndAnswers()).thenReturn(questions);

        int amount = 2;
        Collection<Question> result = examinerService.getQuestions(amount);
        assertEquals(questions, result);
    }

    @Test
    void getQuestions_WhenAmountIsLessThanAvailable_ShouldReturnRandomQuestions() {
        Set<Question> questions = Set.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3")
        );
        when(questionService.getAllQuestionsAndAnswers()).thenReturn(questions);
        when(questionService.getRandomQuestion())
                .thenReturn(new Question("Q1", "A1"))
                .thenReturn(new Question("Q2", "A2"));

        int amount = 2;

        Collection<Question> result = examinerService.getQuestions(amount);
        assertEquals(amount, result.size());
        assertTrue(questions.containsAll(result));
    }
}