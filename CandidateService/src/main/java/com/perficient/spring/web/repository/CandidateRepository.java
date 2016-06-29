/*
 * Author : Pratyusha Vankayala
 *  Pivotal Boot Camp - Perficient
 * 
 */
package com.perficient.spring.web.repository;

import java.sql.Blob;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perficient.spring.web.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer>{

	
	Candidate findOne(int id);
	
	/* 
	 * @author Justin Grothe
	 * saves candidate to database, returns candidate
	 */
	Candidate saveCandidate(Candidate c);

	/* 
	 * @author Justin Grothe
	 * Adds candidate to the database, returns id 
	 */
	int addCandidate(Candidate c);


	Candidate convert(Candidate c);
	
	/* 
	 * @author Justin Grothe
	 * retrieves first name, last name, id from database with search terms from search application
	 */
	ArrayList<String> findAll(String params);

}
