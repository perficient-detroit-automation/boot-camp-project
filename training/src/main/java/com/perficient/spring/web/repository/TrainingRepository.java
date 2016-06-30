package com.perficient.spring.web.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.perficient.spring.web.model.TrainingMaster;

@Repository

public interface TrainingRepository {

	int addTraining (TrainingMaster tm);

	TrainingMaster findTraining(int id);

	TrainingMaster saveTraining(TrainingMaster tm);

	ArrayList<String> findAll(String searchBar);
	

		
	
}
