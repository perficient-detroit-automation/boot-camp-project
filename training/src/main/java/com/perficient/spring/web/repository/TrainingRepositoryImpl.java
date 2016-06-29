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
			Connection con=DriverManager.getConnection("jdbc:h2:~/trainingConsole","sa", "");
			PreparedStatement insertPS= null;
			
			String insertQuery= "INSERT INTO TRAININGMASTER (TITLE, DESCRIPTION, DURATION, DURATIONUNIT_EN, URL, TRAININGSTATUS_EN, BOOTCAMPTYPE_EN) VALUES (?,?,?,?,?,?,?)";
			
			try{
				
				insertPS= con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
				insertPS.setString(1, tm.getTitle());
				insertPS.setString(1, tm.getDescription());
				insertPS.setInt(1, tm.getDuration());
				insertPS.setInt(1, tm.getDurationUnit());
				insertPS.setString(1, tm.getURL());
				insertPS.setInt(1, tm.getTrainingStatus());
				insertPS.setInt(1, tm.getBootcampType());
				
				insertPS.executeUpdate();
				ResultSet generatedKeys= insertPS.getGeneratedKeys();
				if (generatedKeys.next()){
					
					tm.setTrainingID(generatedKeys.getInt(1));
					
				}
				
				insertPS.close();
			} catch (SQLException s) {
				
				System.out.println("Insert for add failed.");
			}
			
		} catch (Exception e) {
			
			
			
		}
		
		return tm.getTrainingID();
		
	}
	
}
