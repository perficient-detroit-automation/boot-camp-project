/*
 * Author : Pratyusha Vankayala
 *  Pivotal Boot Camp - Perficient
 * 
 */

package com.perficient.spring.web.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.perficient.spring.web.jdbc.CandidateMapper;
import com.perficient.spring.web.model.Candidate;


@Component
public class CandidateRepositoryImpl implements CandidateRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;


	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Candidate findOne(int id) {
		// TODO Auto-generated method stub
		//MapSqlParameterSource parameters = new MapSqlParameterSource();
		//parameters.addValue("PERSONID", id);

		SqlParameterSource namedParameters =
			new MapSqlParameterSource("id", Integer.valueOf(id));
		Candidate cand = (Candidate)jdbc.queryForObject(
			"SELECT * FROM CANDIDATE WHERE PERSON_ID = :id;", namedParameters,
			new CandidateMapper());

		return cand;
	}


	@Override
	public <S extends Candidate> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Candidate> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Candidate findOne(Integer id) {
		// TODO Auto-generated method stub


		return null;
	}

	@Override
	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Candidate> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Candidate> findAll(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Candidate entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Candidate> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
