/*
 * Author : Pratyusha Vankayala
 *  Pivotal Boot Camp - Perficient
 * 
 */

package com.perficient.spring.web.repository;



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.perficient.spring.web.controller.HomeController;
import com.perficient.spring.web.jdbc.CandidateMapper;
import com.perficient.spring.web.jdbc.EnumTableRowMapper;
import com.perficient.spring.web.model.Candidate;

@Component
public class CandidateRepositoryImpl implements CandidateRepository {
	
	static Logger log = Logger.getLogger(HomeController.class);

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
	
	public Candidate addCandidate(Candidate entity) {
		try{
			Class.forName("org.h2.Driver");
			Connection con = DriverManager.getConnection("jdbc:h2:~/candidateService","sa","");
			PreparedStatement insertPreparedStatement = null;
			String insertQuery = "INSERT INTO CANDIDATE (FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS, START_DATE, DEGREE_EN, MAJOR, SKILL_SET, GRADUATION_DATE, STATUS_EN, COMMENTS, RESUME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				insertPreparedStatement = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
				insertPreparedStatement.setString(1, entity.getFirstName());
				insertPreparedStatement.setString(2, entity.getLastName());
				insertPreparedStatement.setString(3, entity.getPhoneNumber());
				insertPreparedStatement.setString(4, entity.getEmailAddress());
				if (entity.getStartDate() == null) {
					insertPreparedStatement.setDate(5, null);
				} else {
					insertPreparedStatement.setDate(5, java.sql.Date.valueOf(entity.getStartDate()));
				}
				insertPreparedStatement.setInt(6, entity.getDegree());
				insertPreparedStatement.setString(7, entity.getMajor());
				insertPreparedStatement.setString(8, entity.getSkillset());
				insertPreparedStatement.setDate(9, java.sql.Date.valueOf(entity.getGraduationDate()));
				insertPreparedStatement.setInt(10, entity.getStatus());
				insertPreparedStatement.setString(11, entity.getComments());
				insertPreparedStatement.setBlob(12, entity.getResume());
				insertPreparedStatement.executeUpdate();
				ResultSet generatedKeys = insertPreparedStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					entity.setPersonID(generatedKeys.getInt(1));
				}
				insertPreparedStatement.close();
			} catch (SQLException e) {
				System.out.println("Insert to database for add failed");
				System.out.println(e.getMessage());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return entity;
	}


//	public Candidate getStatusDescription(int id) {
//		SqlParameterSource namedParameters = new MapSqlParameterSource("person_id", Integer.valueOf(id));
//		String sql = "SELECT DESCRIPTION FROM STATUS_ENUM, CANDIDATE WHERE PERSON_ID = ? "
//				+ "AND CANDIDATE.STATUS_EN = STATUS_ENUM.ENUM_ID";
//		Candidate statusDesc = jdbc.queryForList(sql, namedParameters);
//		
//		return statusDesc.get(0).toString();
//	}
	

	public Candidate saveCandidate(Candidate entity) {

		// TODO Auto-generated method stub
		try{
			System.out.println("In savecandidate function, about to update candidate");
			Class.forName("org.h2.Driver");
			Connection con = DriverManager.getConnection("jdbc:h2:~/candidateService","sa","");
			PreparedStatement updatePreparedStatement = null;
			String insertQuery = "UPDATE CANDIDATE SET (FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS, START_DATE, DEGREE_EN, MAJOR, SKILL_SET, GRADUATION_DATE, STATUS_EN, COMMENTS, RESUME) = (?,?,?,?,?,?,?,?,?,?,?,?)"
					+ " WHERE PERSON_ID=" + entity.getPersonID();
			try {
				updatePreparedStatement = con.prepareStatement(insertQuery);
				updatePreparedStatement.setString(1, entity.getFirstName());
				updatePreparedStatement.setString(2, entity.getLastName());
				updatePreparedStatement.setString(3, entity.getPhoneNumber());
				updatePreparedStatement.setString(4, entity.getEmailAddress());
				if (entity.getStartDate() == null) {
					updatePreparedStatement.setDate(5, null);
				} else {
					updatePreparedStatement.setDate(5, java.sql.Date.valueOf(entity.getStartDate()));
				}
				updatePreparedStatement.setInt(6, entity.getDegree() + 1);
				updatePreparedStatement.setString(7, entity.getMajor());
				updatePreparedStatement.setString(8, entity.getSkillset());
				updatePreparedStatement.setDate(9, java.sql.Date.valueOf(entity.getGraduationDate()));
				updatePreparedStatement.setInt(10, entity.getStatus() + 1);
				updatePreparedStatement.setString(11, entity.getComments());
				updatePreparedStatement.setBlob(12, entity.getResume());
				updatePreparedStatement.executeUpdate();
				updatePreparedStatement.close();
				
				System.out.println("Updated candidate information in database");
			} catch (SQLException e) {
				System.out.println("Insert to database for save failed");
				System.out.println(e.getMessage());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return entity;
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

	@Override
	public <S extends Candidate> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}


}
