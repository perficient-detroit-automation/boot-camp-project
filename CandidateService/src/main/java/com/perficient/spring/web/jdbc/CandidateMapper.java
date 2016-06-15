package com.perficient.spring.web.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.perficient.spring.web.model.Candidate;

public class CandidateMapper implements RowMapper<Object>{

	public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		Candidate c = new Candidate();
		
		c.setPersonID(rs.getInt("PERSON_ID"));

		c.setFirstName(rs.getString("FIRST_NAME"));
		c.setLastName(rs.getString("LAST_NAME"));
		c.setPhoneNumber(rs.getString("PHONE_NUMBER"));
		c.setEmailAddress(rs.getString("EMAIL_ADDRESS"));

		c.setStartDate(rs.getDate("START_DATE").toLocalDate());
		c.setDegree(rs.getInt("DEGREE_EN"));
		c.setMajor(rs.getString("MAJOR"));
		c.setSkillset(rs.getString("SKILL_SET"));

		c.setGraduationDate(rs.getDate("GRADUATION_DATE").toLocalDate());
		c.setStatus(rs.getInt("STATUS_EN"));
		c.setComments(rs.getString("COMMENTS"));
		c.setResume(rs.getBlob("RESUME"));
		
	return c;
		
	}
	

}
