package com.perficient.spring.web.repository;

import org.springframework.stereotype.Repository;

import com.perficient.spring.web.model.TrainingMaster;

@Repository

public interface TrainingRepository {

	int addTraining (TrainingMaster tm);
	

		
	
}
