package com.perficient.spring.web.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;



@Component("candidateDAO")
final class CandidateDAO {

	private final RowMapper<Candidate> candidateMapper = new RowMapper<Candidate>() {

		@Override
		public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
			Candidate c = new Candidate();

			c.setPersonID(rs.getInt("PersonID"));

			c.setFirstName(rs.getString("FirstName"));
			c.setLastName(rs.getString("LastName"));
			c.setPhoneNumber(rs.getString("PhoneNumber"));
			c.setEmailAddress(rs.getString("EmailAddress"));

			c.setStartDate(rs.getDate("StartDate"));
			c.setDegree(rs.getString("D.Description"));
			c.setMajor(rs.getString("Major"));
			c.setSkillset(rs.getString("Skillset"));

			c.setGraduationDate(rs.getDate("GraduationDate"));
			c.setStatus(rs.getString("S.Description"));
			c.setComments(rs.getString("Comments"));
			c.setResume(rs.getBlob("Resume"));

			return c;
		}
	};


	private NamedParameterJdbcTemplate jdbc;
	//@formatter:off
	private final String CANDIDATE_QUERY = "SELECT	PersonID,"
			+ " FirstName, LastName, PhoneNumber, EmailAddress,"
			+ " StartDate, D.Description, Major, Skillset,"
			+ " GraduationDate, S.Description, Comments, Resume"
			+ " FROM	PERSON as P"
			+ " JOIN	DEGREE_ENUM as D"
			+ " ON		P.Degree_EN = D.EnumID"
			+ " JOIN	STATUS_ENUM as S"
			+ " ON		P.Status_EN = S.EnumID";
	// @formatter:on	


	//	@Autowired
	//	public void setDataSource(DataSource source) {
	//		jdbc = new NamedParameterJdbcTemplate(source);
	//	}
	//
	public Candidate getCandidateByID(final int personID) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("PersonID", personID);
		return jdbc.queryForObject(CANDIDATE_QUERY, parameters, candidateMapper);
	}

	public List<Candidate> getCandidates() {
		return jdbc.query(CANDIDATE_QUERY, candidateMapper);
	}

}
