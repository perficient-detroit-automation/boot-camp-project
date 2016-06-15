/*
 * Author : Pratyusha Vankayala
 *  Pivotal Boot Camp - Perficient
 * 
 */

package com.perficient.spring.web.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.perficient.spring.web.jdbc.CandidateMapper;
import com.perficient.spring.web.jdbc.EnumTableRowMapper;
import com.perficient.spring.web.model.Candidate;

@Component
public class CandidateRepositoryImpl implements CandidateRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}


	private JdbcTemplate jdbcTemplate;

	 public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }
	 
	@Override
	public Candidate findOne(int id) {
		// TODO Auto-generated method stub
		// MapSqlParameterSource parameters = new MapSqlParameterSource();
		// parameters.addValue("PERSONID", id);

		SqlParameterSource namedParameters = new MapSqlParameterSource("ids", Integer.valueOf(id));
		Candidate cand = (Candidate) jdbc.queryForObject("SELECT * FROM CANDIDATE WHERE PERSON_ID = :ids;",
				namedParameters, new CandidateMapper());

		return cand;
	}

//	public String getStatusDescription(int id) {
//		SqlParameterSource namedParameters = new MapSqlParameterSource("person_id", Integer.valueOf(id));
//		String sql = "SELECT DESCRIPTION FROM STATUS_ENUM, CANDIDATE WHERE PERSON_ID = ? "
//				+ "AND CANDIDATE.STATUS_EN = STATUS_ENUM.ENUM_ID";
//		
//		return null;
//	}
	
	
	@Override
	public <S extends Candidate> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public <S extends Candidate> Iterable<S> save(Iterable<S> entities) {
	// // TODO Auto-generated method stub
	// return null;
	// }

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

	// @Override
	// public Iterable<Candidate> findAll() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public Iterable<Candidate> findAll(Iterable<Integer> ids) {
	// // TODO Auto-generated method stub
	// return null;
	// }

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

	@Override
	public List<Candidate> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Candidate> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Candidate> findAll(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Candidate> List<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends Candidate> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Candidate> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public Candidate getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Candidate> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
