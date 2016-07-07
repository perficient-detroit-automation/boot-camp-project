package com.perficient.spring.web.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.perficient.spring.web.model.Course;

public class CourseMapper implements RowMapper<Object>{

	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		Course c = new Course();
		
		c.setCourseId(rs.getInt("COURSE_ID"));

		c.setCourseTitle(rs.getString("COURSE_TITLE"));
		c.setCourseDescription(rs.getString("COURSE_DESCRIPTION"));
		c.setCourseUrl(rs.getString("COURSE_URL"));
		
		
	return c;
		
	}

}
