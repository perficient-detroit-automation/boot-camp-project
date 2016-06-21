/*
 * Author : Pratyusha Vankayala
 *  Pivotal Boot Camp - Perficient
 * 
 */

package com.perficient.spring.web.repository;



import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.perficient.spring.web.controller.HomeController;
import com.perficient.spring.web.jdbc.CandidateMapper;
import com.perficient.spring.web.jdbc.CandidateMapper2;
import com.perficient.spring.web.model.Candidate;

@Component
public class CandidateRepositoryImpl implements CandidateRepository {
	
	static Logger log = Logger.getLogger(HomeController.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
//	public void uploadResume(Blob blob){
//			sessionFactory.getCurrentSession().save(blob);
//		}

	public ArrayList<String> findAll(String params) {
		List<Object> a = null;
		ArrayList<String> toReturn = new ArrayList<String>();
		System.out.println("in findall method");
		String[] searchParams = StringUtils.split(params, " ");
		String f_name = "", l_name = "";
		if (searchParams == null) {
			System.out.println("There is one argument in search bar");
			try {
				int searchInt = Integer.parseInt(params);
				// Know it is an int, so they are searching by employee id
				String insertQuery = "SELECT FIRST_NAME, LAST_NAME, PERSON_ID FROM CANDIDATE WHERE PERSON_ID =" + searchInt;
				try {
					a =  jdbcTemplate.query(insertQuery, new CandidateMapper2());
				} catch (DataAccessException d) {
					System.out.println(d.getMessage());
					System.out.println(d.getLocalizedMessage());
				}
				if (a == null) {
					System.out.println("a is null");
				} else {
					for (int i = 0; i < a.size(); i++) {
						System.out.println(a.get(i));
						System.out.println(((Candidate) a.get(i)).getFirstName());
						toReturn.add(((Candidate) a.get(i)).getFirstName() + 
								" " + ((Candidate) a.get(i)).getLastName() + " " + ((Candidate) a.get(i)).getPersonID());
					}
				}
				System.out.println("Executed query based on person id");
			} catch (NumberFormatException e) {
				// if the Integer.parseInt fails, know they are searching by last name
				l_name = StringUtils.capitalize(params);
				String insertQuery = "SELECT FIRST_NAME, LAST_NAME, PERSON_ID FROM CANDIDATE WHERE LAST_NAME LIKE '" + l_name + "'";
				try {
					a =  jdbcTemplate.query(insertQuery, new CandidateMapper2());
				} catch (DataAccessException d) {
					System.out.println(d.getMessage());
					System.out.println(d.getLocalizedMessage());
				}
				if (a == null) {
					System.out.println("a is null");
				} else {
					for (int i = 0; i < a.size(); i++) {
						System.out.println(a.get(i));
						System.out.println(((Candidate) a.get(i)).getFirstName());
						toReturn.add(((Candidate) a.get(i)).getFirstName() + 
								" " + ((Candidate) a.get(i)).getLastName() + " " + ((Candidate) a.get(i)).getPersonID());
					}
				}
				System.out.println("Executed query based on last name only");
			}
		} else if (searchParams.length == 2) {
			System.out.println("There are two arguments in search bar");
			f_name = StringUtils.capitalize(searchParams[0]);
			l_name = StringUtils.capitalize(searchParams[1]);
			String insertQuery = "SELECT FIRST_NAME, LAST_NAME, PERSON_ID FROM CANDIDATE WHERE FIRST_NAME LIKE '"
					+ f_name + "' AND LAST_NAME LIKE '" + l_name + "'";
			System.out.println(insertQuery);
			try {
				a =  jdbcTemplate.query(insertQuery, new CandidateMapper2());
			} catch (DataAccessException d) {
				System.out.println(d.getMessage());
				System.out.println(d.getLocalizedMessage());
			}
			System.out.println("Executed query for list");
			if (a == null) {
				System.out.println("a is null");
			} else {
				for (int i = 0; i < a.size(); i++) {
					System.out.println(a.get(i));
					System.out.println(((Candidate) a.get(i)).getFirstName());
					toReturn.add(((Candidate) a.get(i)).getFirstName() + 
							" " + ((Candidate) a.get(i)).getLastName() + " " + ((Candidate) a.get(i)).getPersonID());
				}
			}
			System.out.println(f_name + " " + l_name);
		}
		return toReturn;
	}
	 
	@Override
	public Candidate findOne(int id) {
		System.out.println("in repositoryimpl findone, id: " + id);
		// TODO Auto-generated method stub
		// MapSqlParameterSource parameters = new MapSqlParameterSource();
		// parameters.addValue("PERSONID", id);

		SqlParameterSource namedParameters = new MapSqlParameterSource("ids", Integer.valueOf(id));
		Candidate cand = (Candidate) jdbc.queryForObject("SELECT * FROM CANDIDATE WHERE PERSON_ID = :ids;",
				namedParameters, new CandidateMapper());
		return cand;
	}
	
	public int addCandidate(Candidate entity) {
		try{
			Class.forName("org.h2.Driver");
			Connection con = DriverManager.getConnection("jdbc:h2:~/candidateService","sa","");
			PreparedStatement insertPreparedStatement = null;
			String insertQuery = "INSERT INTO CANDIDATE (FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS, START_DATE, DEGREE_EN, MAJOR, SKILL_SET, GRADUATION_DATE, STATUS_EN, COMMENTS, RESUME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				insertPreparedStatement = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
//				insertPreparedStatement.setString(1, entity.getFirstName());
//				insertPreparedStatement.setString(2, entity.getLastName());
//				insertPreparedStatement.setString(3, entity.getPhoneNumber());
//				insertPreparedStatement.setString(4, entity.getEmailAddress());
//				if (entity.getStartDate() == null) {
//					insertPreparedStatement.setDate(5, null);
//				} else {
//					insertPreparedStatement.setDate(5, java.sql.Date.valueOf(entity.getStartDate()));
//				}
//				insertPreparedStatement.setInt(6, entity.getDegree() + 1);
//				insertPreparedStatement.setString(7, entity.getMajor());
//				insertPreparedStatement.setString(8, entity.getSkillset());
//				insertPreparedStatement.setDate(9, java.sql.Date.valueOf(entity.getGraduationDate()));
//				insertPreparedStatement.setInt(10, entity.getStatus() + 1);
//				insertPreparedStatement.setString(11, entity.getComments());
//				insertPreparedStatement.setBlob(12, entity.getResume());
//				insertPreparedStatement.executeUpdate();
//				ResultSet generatedKeys = insertPreparedStatement.getGeneratedKeys();
//				if (generatedKeys.next()) {
//					entity.setPersonID(generatedKeys.getInt(1));
//				}
				System.out.println(entity.getResume());
				insertPreparedStatement.close();
			} catch (SQLException e) {
				System.out.println("Insert to database for add failed");
				System.out.println(e.getMessage());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return entity.getPersonID();
	}
	
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
				updatePreparedStatement.setBlob(12, entity.getResume().getBinaryStream());
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

	@Override
	public Candidate convert(Candidate c) {
		// TODO Auto-generated method stub
		
			try{
			Class.forName("org.h2.Driver");
			System.out.println("converting here..!!!");
			Connection con = DriverManager.getConnection("jdbc:h2:~/candidateService","sa","");
			PreparedStatement updatePreparedStatement = null;
			String insertQuery = "UPDATE CANDIDATE SET (STATUS_EN) = (?)"
					+ " WHERE PERSON_ID=" + c.getPersonID();
		
			updatePreparedStatement = con.prepareStatement(insertQuery);
			updatePreparedStatement.setInt(1, 4);
			updatePreparedStatement.executeUpdate();
			updatePreparedStatement.close();
			
			System.out.println("converted");
			}
			catch(Exception e){
				System.out.println("convert to employee for employee failed");
				e.printStackTrace();
			}
		return c;
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
