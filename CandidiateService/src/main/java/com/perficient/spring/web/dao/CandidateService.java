package com.perficient.spring.web.dao;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("candidate-service")
public class CandidateService {

	@Autowired
	private CandidateDAO dao;


	public Candidate getSampleCandidate() {
		LocalDate hireDate = LocalDate.parse("2015-03-31");
		LocalDate graduationDate = LocalDate.parse("2009-05-09");

		return new Candidate("Nick", "Umble", "248-469-2924",
			"nicholas.umble@perficient.com", hireDate, "Associate's",
			"Software Engineering", "Java", graduationDate, "Hired",
			"Test candidate", null);
	}
}
