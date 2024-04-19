package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String category);

	@Query("FROM Question WHERE category=?1 ORDER BY RAND() LIMIT ?2")
	List<Question> findNRandomQuestions(String category, int numOfQue);
	
}
