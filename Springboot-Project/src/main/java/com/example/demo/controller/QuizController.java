package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;
import com.example.demo.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<Quiz> createQuiz(@RequestParam(value = "title") String title, @RequestParam("category") String category) {
		return quizService.createQuiz(title, category);
	}
	
	@PostMapping("createownquiz")
	public ResponseEntity<Quiz> createYourQuiz(@RequestParam(value="title") String title, @RequestParam("category") String category, @RequestParam(value="numOfQue") int numOfQue) {
		return quizService.createYourQuiz(title, category, numOfQue);
	}
	
	@DeleteMapping("/delete/{id}") 
	public ResponseEntity<String> deleteQuiz(@PathVariable(value="id") int id) {
		return quizService.deleteQuiz(id);
	}
	
	@GetMapping("/get/{id}") 
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable(value="id") Integer id) {
		return quizService.getQuestions(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable(value="id") Integer id, @RequestBody List<Response> responses) {
		return quizService.calculateResult(id, responses);
	}
}
