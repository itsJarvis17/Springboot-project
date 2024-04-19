package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Question;
import com.example.demo.service.QuestionService;

@RestController
@RequestMapping("questions")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("allquestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable(value = "category") String category) {
		return questionService.getAllQuestionsByCategory(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteQuestionById(@PathVariable(value="id") Integer id) {
		return questionService.deleteQuestionById(id);
	}
	
//	@PutMapping("/modify")
//	public Question saveOrUpdateQuestion(@RequestBody Question question) {
//		return questionService.saveOrUpdateQuestion(question);
//	}
}
