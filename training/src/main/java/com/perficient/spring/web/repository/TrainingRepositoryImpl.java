package com.perficient.spring.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.perficient.spring.web.jdbc.TrainingMapper;
import com.perficient.spring.web.jdbc.TrainingMapper2;
import com.perficient.spring.web.model.TrainingMaster;

@Component
public class TrainingRepositoryImpl implements TrainingRepository{

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
	public int addTraining (TrainingMaster tm){
		
		try {
			Class.forName("org.h2.Driver");
			Connection con=DriverManager.getConnection("jdbc:h2:~/trainingMaster","sa", "");
			PreparedStatement insertPS= null;
			String insertQuery= "INSERT INTO TRAININGMASTER (TITLE, DESCRIPTION, DURATION, DURATIONUNIT_EN, URL, TRAININGSTATUS_EN, BOOTCAMPTYPE_EN) VALUES (?,?,?,?,?,?,?)";
			try{
				insertPS= con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
				insertPS.setString(1, tm.getTitle());
				insertPS.setString(2, tm.getDescription());
				insertPS.setInt(3, tm.getDuration());
				insertPS.setInt(4, tm.getDurationUnit());
				insertPS.setString(5, tm.getURL());
				insertPS.setInt(6, tm.getTrainingStatus());
				insertPS.setInt(7, tm.getBootcampType());
				
				insertPS.executeUpdate();
				ResultSet generatedKeys= insertPS.getGeneratedKeys();
				if (generatedKeys.next()){
					tm.setTrainingID(generatedKeys.getInt(1));
				}
				insertPS.close();
			} catch (SQLException s) {
				System.out.println("Insert for add failed.");
				System.out.println(s.getMessage());
				System.out.println(s.getLocalizedMessage());
			}
			
		} catch (Exception e) {
			
		}
		return tm.getTrainingID();
		
	}
	
	public TrainingMaster findTraining(int id) {
		System.out.println("in repositoryimpl findone, id: " + id);

		SqlParameterSource namedParameters = new MapSqlParameterSource("ids", Integer.valueOf(id));
		TrainingMaster empl = (TrainingMaster) jdbc.queryForObject("SELECT * FROM TRAININGMASTER WHERE TRAINING_ID = :ids;",
				namedParameters, new TrainingMapper());
		return empl;
	}
	
	public TrainingMaster saveTraining(TrainingMaster tm) {
		
		try {
			System.out.println("Testing Testing");
			System.out.println("ID " + tm.getTrainingID());
			Class.forName("org.h2.Driver");
			Connection con=DriverManager.getConnection("jdbc:h2:~/trainingMaster","sa", "");
			PreparedStatement insertPS= null;
			String insertQuery= "UPDATE TRAININGMASTER SET (TITLE, DESCRIPTION, DURATION, DURATIONUNIT_EN, URL, TRAININGSTATUS_EN, BOOTCAMPTYPE_EN) = (?,?,?,?,?,?,?)" 
					+ "WHERE TRAINING_ID =" + tm.getTrainingID();
			try{
				insertPS= con.prepareStatement(insertQuery);
				insertPS.setString(1, tm.getTitle());
				insertPS.setString(2, tm.getDescription());
				insertPS.setInt(3, tm.getDuration());
				insertPS.setInt(4, tm.getDurationUnit());
				insertPS.setString(5, tm.getURL());
				insertPS.setInt(6, tm.getTrainingStatus());
				insertPS.setInt(7, tm.getBootcampType());
				
				insertPS.executeUpdate();
				insertPS.close();
			} catch (SQLException s) {
				System.out.println("Insert for save failed.");
				System.out.println(s.getMessage());
				System.out.println(s.getLocalizedMessage());
			}
			
		} catch (Exception e) {
			
		}
		
		return tm;
	}
	
	public ArrayList<String> findAll(String params){
		
		List<Object> a = null;
		ArrayList<String> toReturn = new ArrayList<String>();
		System.out.println("in findall method");
		System.out.println("There is one argument in search bar");
		// Not the best way to do this, as catch block is used in flow of code but wasn't sure how to do it otherwise
		try {
			int searchInt = Integer.parseInt(params);
			// Know it is an int, so they are searching by employee id
			String insertQuery = "SELECT TITLE, TRAINING_ID FROM TRAININGMASTER WHERE TRAINING_ID =" + searchInt;
			try {
				a =  jdbcTemplate.query(insertQuery, new TrainingMapper2());
			} catch (DataAccessException d) {
				System.out.println(d.getMessage());
				System.out.println(d.getLocalizedMessage());
			}
			if (a == null) {
				System.out.println("a is null");
			} else {
				for (int i = 0; i < a.size(); i++) {
					System.out.println(a.get(i));
					System.out.println(((TrainingMaster) a.get(i)).getTitle());
					toReturn.add(((TrainingMaster) a.get(i)).getTitle() + 
							" " + ((TrainingMaster) a.get(i)).getTrainingID());
				}
			}
			System.out.println("Executed query based on person id");
		} catch (NumberFormatException e) {
			// if the Integer.parseInt fails, know they are searching by last name
			String insertQuery = "SELECT TITLE, TRAINING_ID FROM TRAININGMASTER WHERE TITLE LIKE '" + params + "'";
			try {
				a =  jdbcTemplate.query(insertQuery, new TrainingMapper2());
			} catch (DataAccessException d) {
				System.out.println(d.getMessage());
				System.out.println(d.getLocalizedMessage());
			}
			if (a == null) {
				System.out.println("a is null");
			} else {
				for (int i = 0; i < a.size(); i++) {
					System.out.println(a.get(i));
					System.out.println(((TrainingMaster) a.get(i)).getTitle());
					toReturn.add(((TrainingMaster) a.get(i)).getTitle() + 
							" " + ((TrainingMaster) a.get(i)).getTrainingID());
				}
			}
			System.out.println("Executed query based on last name only");
		}


		return toReturn;
		
	}
	
}
