package com.perficient.spring.web.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.perficient.spring.web.model.Employee;

public class EmployeeMapper2 implements RowMapper<Object>{

	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		Employee c = new Employee();
		
		c.setEmployeeID(rs.getInt("EMPLOYEE_ID"));
		c.setFirstName(rs.getString("FIRST_NAME"));
		c.setLastName(rs.getString("LAST_NAME"));
		
	return c;
		
	}
	

}