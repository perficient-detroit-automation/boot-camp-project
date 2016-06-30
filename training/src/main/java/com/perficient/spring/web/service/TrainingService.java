package com.perficient.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.spring.web.model.TrainingMaster;
import com.perficient.spring.web.repository.TrainingRepository;

@Service 


public class TrainingService {
	
	@Autowired
	private TrainingRepository trainingRepository;

	public int addTraining(TrainingMaster tm){	
		return trainingRepository.addTraining(tm);
	}

	public TrainingMaster findTraining(int id) {
		// TODO Auto-generated method stub
		return trainingRepository.findTraining(id);
	}

	public TrainingMaster saveTraining(TrainingMaster tm) {
		// TODO Auto-generated method stub
		return trainingRepository.saveTraining(tm);
	}
	
}
 