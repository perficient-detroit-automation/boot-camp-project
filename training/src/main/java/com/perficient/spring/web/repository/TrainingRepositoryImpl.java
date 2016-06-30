package com.perficient.spring.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.perficient.spring.web.model.TrainingMaster;

@Component
public class TrainingRepositoryImpl implements TrainingRepository{

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
				insertPS.setInt(4, tm.getDurationUnit() + 1);
				insertPS.setString(5, tm.getURL());
				insertPS.setInt(6, tm.getTrainingStatus() + 1);
				insertPS.setInt(7, tm.getBootcampType() + 1);
				
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
	
}
