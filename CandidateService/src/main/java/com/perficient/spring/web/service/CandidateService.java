package com.perficient.spring.web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.spring.web.model.Candidate;
import com.perficient.spring.web.repository.CandidateRepository;


@Service
public class CandidateService {
	
	@Autowired
	private CandidateRepository candidateRepository;

	public Candidate getSampleCandidate() {
	/*	LocalDate hireDate = LocalDate.parse("2015-03-31");
		LocalDate graduationDate = LocalDate.parse("2009-05-09");

		return new Candidate("Nick", "Umble", "248-469-2924",
			"nicholas.umble@perficient.com", Date.valueOf(hireDate), "Associate's",
			"Software Engineering", "Java", Date.valueOf(graduationDate), "Hired",
			"Test candidate", null); */
		
		return candidateRepository.findOne(1);
	}
}
