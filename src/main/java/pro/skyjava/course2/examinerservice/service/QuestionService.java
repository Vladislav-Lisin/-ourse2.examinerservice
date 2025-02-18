package pro.skyjava.course2.examinerservice.service;


import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(String question, String answer);
    String remove(String question, String answer);
    Collection<Question> getAllQuestionsAndAnswers();
    Question getRandomQuestion();


}
