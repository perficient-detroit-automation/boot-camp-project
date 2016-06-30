package com.perficient.spring.web.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.perficient.spring.web.model.TrainingMaster;

public class TrainingMapper implements RowMapper<Object>{
	
	public TrainingMaster mapRow(ResultSet rs, int RowNum) throws SQLException{
		
		TrainingMaster tm= new TrainingMaster();
		
		tm.setTrainingID(rs.getInt("TRAINING_ID"));
		tm.setTitle(rs.getString("TITLE"));
		tm.setDescription(rs.getString("DESCRIPTION"));
		tm.setDuration(rs.getInt("DURATION"));
		tm.setDurationUnit(rs.getInt("DURATIONUNIT_EN"));
		tm.setURL(rs.getString("URL"));
		tm.setTrainingStatus(rs.getInt("TRAININGSTATUS_EN"));
		tm.setBootcampType(rs.getInt("BOOTCAMPTYPE_EN"));
		
		return tm;
	}

}
