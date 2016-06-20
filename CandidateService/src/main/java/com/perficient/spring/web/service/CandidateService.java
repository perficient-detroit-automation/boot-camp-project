package com.perficient.spring.web.service;


import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.spring.web.model.Candidate;
import com.perficient.spring.web.repository.CandidateRepository;


@Service
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;


	public Candidate getSampleCandidate() {
		LocalDate hireDate = LocalDate.parse("2015-03-31");
		LocalDate graduationDate = LocalDate.parse("2009-05-09");

		return new Candidate("Nick", "Umble", "248-469-2924",
			"nicholas.umble@perficient.com", hireDate, 2, "Software Engineering",
			"Java", graduationDate, 3, "Test candidate", null);
	}

	public Candidate getOneCandidate() {
		return candidateRepository.findOne(1);
	}
	
	public Candidate saveCandidate(Candidate c) {
		return candidateRepository.saveCandidate(c);
	}
	
	public Candidate addcandidate(Candidate c) {
		return candidateRepository.addCandidate(c);
	}
	
	public Candidate convertCandidate(Candidate c){
		return candidateRepository.convert(c);
	}

	public ArrayList<String> findAll(String params) {
		return candidateRepository.findAll(params);
	}

}
