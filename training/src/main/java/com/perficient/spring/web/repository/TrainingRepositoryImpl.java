package com.perficient.spring.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.perficient.spring.web.jdbc.TrainingMapper;
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
	
}
