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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.perficient.spring.web.controller.HomeController;
import com.perficient.spring.web.jdbc.CandidateMapper;
import com.perficient.spring.web.model.Candidate;


@Component
public class CandidateRepositoryImpl implements CandidateRepository {
	
	static Logger log = Logger.getLogger(HomeController.class);

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
			new MapSqlParameterSource("ids", Integer.valueOf(id));
		Candidate cand = (Candidate)jdbc.queryForObject(
			"SELECT * FROM CANDIDATE WHERE PERSON_ID = :ids;", namedParameters,
			new CandidateMapper());

		return cand;
	}


	public Candidate saveCandidate(Candidate entity) {
		// TODO Auto-generated method stub
		try{
			Class.forName("org.h2.Driver");
			Connection con = DriverManager.getConnection("jdbc:h2:~/candidateService","sa","");
//			try{
//				Statement st1 = con.createStatement();
//				ResultSet res = st1.executeQuery("SELECT COUNT(*) FROM CANDIDATE WHERE EMAIL_ADDRESS='" + entity.getEmailAddress() + "'");
//				res.next();
//				int count = res.getInt(1);
//				log.info("Count:" + count);
				log.info("Person id: " + entity.getPersonID());
//				if (count == 0) { // Does not exist in database, need to add candidate
				if (entity.getPersonID() == 0) {
					System.out.println("Does not exist in database, attempting to add candidate");
					System.out.println(entity.getStartDate());
					System.out.println(entity.getGraduationDate());
					PreparedStatement insertPreparedStatement = null;
					String insertQuery = "INSERT INTO CANDIDATE (FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS, START_DATE, DEGREE_EN, MAJOR, SKILL_SET, GRADUATION_DATE, STATUS_EN, COMMENTS, RESUME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
					try {
						insertPreparedStatement = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
						insertPreparedStatement.setString(1, entity.getFirstName());
						insertPreparedStatement.setString(2, entity.getLastName());
						insertPreparedStatement.setString(3, entity.getPhoneNumber());
						insertPreparedStatement.setString(4, entity.getEmailAddress());
						insertPreparedStatement.setDate(5, java.sql.Date.valueOf(entity.getStartDate()));
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
						System.out.println("Personid: " + entity.getPersonID());
						insertPreparedStatement.close();
						System.out.println("Updated database hopefully");
					} catch (SQLException e) {
						System.out.println("Insert to database for save failed");
						System.out.println(e.getMessage());
					}
				} else { // Exists in database, need to update candidate
					System.out.println("Candidate is already in database");
				}
//			}
//			catch (SQLException s){
//				System.out.println("SQL statement is not executed!");
//				System.out.println(s.getMessage());
//			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return entity;
	}

//	@Override
//	public <S extends Candidate> Iterable<S> save(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}

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

//	@Override
//	public Iterable<Candidate> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<Candidate> findAll(Iterable<Integer> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}

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
