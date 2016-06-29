package com.perficient.spring.web.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.perficient.spring.web.model.Employee;

public class EmployeeMapper implements RowMapper<Object> {
	
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Employee e = new Employee();
		
		e.setEmployeeID(rs.getInt("EMPLOYEE_ID"));
		e.setFirstName(rs.getString("FIRST_NAME"));
		e.setLastName(rs.getString("LAST_NAME"));
		e.setPhoneNumber(rs.getString("PHONE_NUMBER"));
		e.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
		e.setStartDate(rs.getDate("START_DATE").toLocalDate());
		if (rs.getDate("END_DATE") == null) {
			e.setEndDate(null);
		} else {
			e.setEndDate(rs.getDate("END_DATE").toLocalDate());
		}
		e.setEmployeeType(rs.getInt("EMPLOYEETYPE_EN"));
		e.setEmployeeDept(rs.getInt("EMPLOYEEDEPTTYPE_EN"));
		e.setUserRole(rs.getInt("USERROLE_EN"));
		e.setPassword(rs.getString("PASSWORD"));
		
	return e;
		
	}

}
