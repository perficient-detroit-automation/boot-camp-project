/**
 * @author justin.grothe
 * Used by the /search endpoint to get just first name, last name, id
 */
package com.perficient.spring.web.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.perficient.spring.web.model.Candidate;

public class CandidateMapper2 implements RowMapper<Object>{

	public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		Candidate c = new Candidate();
		
		c.setPersonID(rs.getInt("PERSON_ID"));
		c.setFirstName(rs.getString("FIRST_NAME"));
		c.setLastName(rs.getString("LAST_NAME"));
		
	return c;
		
	}
	

}
