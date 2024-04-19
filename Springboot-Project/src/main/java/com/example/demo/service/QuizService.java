package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuizDao;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<Quiz> createQuiz(String title, String category) {
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questionDao.findByCategory(category));
		
		return new ResponseEntity<Quiz>(quizDao.save(quiz), HttpStatus.CREATED);
	}

	public ResponseEntity<String> deleteQuiz(int id) {
		if(quizDao.existsById(id)) {
			quizDao.deleteById(id);
			return new ResponseEntity<String>("deleted", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<Quiz> createYourQuiz(String title, String category, int numOfQue) {
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questionDao.findNRandomQuestions(category, numOfQue));
		
		return new ResponseEntity<Quiz>(quizDao.save(quiz), HttpStatus.CREATED);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestions(Integer id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Question> questionsFromDb =  quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		
		for(Question question: questionsFromDb) {
			QuestionWrapper q = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
			questionsForUser.add(q);
		}
		
		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

		Optional<Quiz> quiz = quizDao.findById(id);
		List<Question> questions = quiz.get().getQuestions();
		
		int point = 0, i=0;
		for(Response response: responses) {
			if(response.getUserResponse().equals(questions.get(i).getRightAnswer())) 
				point++;
			i++;
		}
		
		return new ResponseEntity<>(point, HttpStatus.OK);
	}


	
}
