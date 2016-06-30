package com.perficient.spring.web.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.perficient.spring.web.model.TrainingMaster;

public class TrainingMapper2 implements RowMapper<Object>{
	
	public TrainingMaster mapRow(ResultSet rs, int RowNum) throws SQLException{
		
		TrainingMaster tm= new TrainingMaster();
		
		tm.setTrainingID(rs.getInt("TRAINING_ID"));
		tm.setTitle(rs.getString("TITLE"));
		
		return tm;
	}

}
