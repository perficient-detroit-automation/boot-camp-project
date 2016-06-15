/*
 * Author : Pratyusha Vankayala
 *  Pivotal Boot Camp - Perficient
 * 
 */
package com.perficient.spring.web.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perficient.spring.web.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer>{

	Candidate findOne(int id);
	Candidate saveCandidate(Candidate c);
	Candidate addCandidate(Candidate c);
}
