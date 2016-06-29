package com.perficient.spring.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.perficient.spring.web.jdbc.EmployeeMapper;
import com.perficient.spring.web.jdbc.EmployeeMapper2;
import com.perficient.spring.web.model.Employee;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int addEmployee(Employee entity) {
		try{
			System.out.println("TTTTTTTTTTTTTTTTTTTTT");
			Class.forName("org.h2.Driver");
			Connection con = DriverManager.getConnection("jdbc:h2:~/employeeService","sa","");
			PreparedStatement insertPreparedStatement = null;
			String insertQuery = "INSERT INTO EMPLOYEE (FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS, START_DATE, END_DATE, EMPLOYEETYPE_EN, EMPLOYEEDEPTTYPE_EN, USERROLE_EN, PASSWORD) VALUES (?,?,?,?,?,?,?,?,?,?)";
			try {
				insertPreparedStatement = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
				insertPreparedStatement.setString(1, entity.getFirstName());
				insertPreparedStatement.setString(2, entity.getLastName());
				insertPreparedStatement.setString(3, entity.getPhoneNumber());
				insertPreparedStatement.setString(4, entity.getEmailAddress());
				insertPreparedStatement.setDate(5, java.sql.Date.valueOf(entity.getStartDate()));
				if (entity.getEndDate() == null) {
					insertPreparedStatement.setDate(6, null);
				} else {
					insertPreparedStatement.setDate(6, java.sql.Date.valueOf(entity.getEndDate()));
				}
				insertPreparedStatement.setInt(7, entity.getEmployeeType() + 1);
				insertPreparedStatement.setInt(8, entity.getEmployeeDept() + 1);
				insertPreparedStatement.setInt(9, entity.getUserRole() + 1);
				insertPreparedStatement.setString(10, null);
				insertPreparedStatement.executeUpdate();
				ResultSet generatedKeys = insertPreparedStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					entity.setEmployeeID(generatedKeys.getInt(1));
				}
				insertPreparedStatement.close();
			} catch (SQLException e) {
				System.out.println("Insert to database for add failed");
				System.out.println(e.getMessage());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return entity.getEmployeeID();
	}

	@Override
	public Employee findOne(int id) {
		System.out.println("in repositoryimpl findone, id: " + id);

		SqlParameterSource namedParameters = new MapSqlParameterSource("ids", Integer.valueOf(id));
		Employee empl = (Employee) jdbc.queryForObject("SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = :ids;",
				namedParameters, new EmployeeMapper());
		return empl;
	}

	@Override
	public Employee saveEmployee(Employee entity) {
		try{
			System.out.println("In saveEmployee function, about to update employee");
			Class.forName("org.h2.Driver");
			Connection con = DriverManager.getConnection("jdbc:h2:~/employeeService","sa","");
			PreparedStatement updatePreparedStatement = null;
			System.out.println(entity.getEmployeeID());
			System.out.println(entity.getFirstName());
			String insertQuery = "UPDATE EMPLOYEE SET (FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL_ADDRESS, START_DATE, END_DATE, EMPLOYEETYPE_EN, EMPLOYEEDEPTTYPE_EN, USERROLE_EN) = (?,?,?,?,?,?,?,?,?)"
					+ " WHERE EMPLOYEE_ID=" + entity.getEmployeeID();
			try {
				updatePreparedStatement = con.prepareStatement(insertQuery);
				updatePreparedStatement.setString(1, entity.getFirstName());
				updatePreparedStatement.setString(2, entity.getLastName());
				updatePreparedStatement.setString(3, entity.getPhoneNumber());
				updatePreparedStatement.setString(4, entity.getEmailAddress());
				updatePreparedStatement.setDate(5, java.sql.Date.valueOf(entity.getStartDate()));
				if (entity.getEndDate() == null) {
					updatePreparedStatement.setDate(6, null);
				} else {
					updatePreparedStatement.setDate(6, java.sql.Date.valueOf(entity.getEndDate()));
				}
				updatePreparedStatement.setInt(7, entity.getEmployeeType() + 1);
				updatePreparedStatement.setInt(8, entity.getEmployeeDept() + 1);
				updatePreparedStatement.setInt(9, entity.getUserRole() + 1);
				updatePreparedStatement.executeUpdate();
				updatePreparedStatement.close();
				
				System.out.println("Updated employee information in database");
			} catch (SQLException e) {
				System.out.println("Insert to database for save failed");
				System.out.println(e.getMessage());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return entity;
	}
	
	@Override
	public ArrayList<String> findAll(String params) {
		List<Object> a = null;
		ArrayList<String> toReturn = new ArrayList<String>();
		System.out.println("in findall method");
		String[] searchParams = StringUtils.split(params, " ");
		String f_name = "", l_name = "";
		if (searchParams == null) {
			System.out.println("There is one argument in search bar");
			// Not the best way to do this, as catch block is used in flow of code but wasn't sure how to do it otherwise
			try {
				int searchInt = Integer.parseInt(params);
				// Know it is an int, so they are searching by employee id
				String insertQuery = "SELECT FIRST_NAME, LAST_NAME, EMPLOYEE_ID FROM EMPLOYEE WHERE EMPLOYEE_ID =" + searchInt;
				try {
					a =  jdbcTemplate.query(insertQuery, new EmployeeMapper2());
				} catch (DataAccessException d) {
					System.out.println(d.getMessage());
					System.out.println(d.getLocalizedMessage());
				}
				if (a == null) {
					System.out.println("a is null");
				} else {
					for (int i = 0; i < a.size(); i++) {
						System.out.println(a.get(i));
						System.out.println(((Employee) a.get(i)).getFirstName());
						toReturn.add(((Employee) a.get(i)).getFirstName() + 
								" " + ((Employee) a.get(i)).getLastName() + " " + ((Employee) a.get(i)).getEmployeeID());
					}
				}
				System.out.println("Executed query based on person id");
			} catch (NumberFormatException e) {
				// if the Integer.parseInt fails, know they are searching by last name
				l_name = StringUtils.capitalize(params);
				String insertQuery = "SELECT FIRST_NAME, LAST_NAME, EMPLOYEE_ID FROM EMPLOYEE WHERE LAST_NAME LIKE '" + l_name + "'";
				try {
					a =  jdbcTemplate.query(insertQuery, new EmployeeMapper2());
				} catch (DataAccessException d) {
					System.out.println(d.getMessage());
					System.out.println(d.getLocalizedMessage());
				}
				if (a == null) {
					System.out.println("a is null");
				} else {
					for (int i = 0; i < a.size(); i++) {
						System.out.println(a.get(i));
						System.out.println(((Employee) a.get(i)).getFirstName());
						toReturn.add(((Employee) a.get(i)).getFirstName() + 
								" " + ((Employee) a.get(i)).getLastName() + " " + ((Employee) a.get(i)).getEmployeeID());
					}
				}
				System.out.println("Executed query based on last name only");
			}
		} else if (searchParams.length == 2) {
			System.out.println("There are two arguments in search bar");
			f_name = StringUtils.capitalize(searchParams[0]);
			l_name = StringUtils.capitalize(searchParams[1]);
			String insertQuery = "SELECT FIRST_NAME, LAST_NAME, EMPLOYEE_ID FROM EMPLOYEE WHERE FIRST_NAME LIKE '"
					+ f_name + "' AND LAST_NAME LIKE '" + l_name + "'";
			System.out.println(insertQuery);
			try {
				a =  jdbcTemplate.query(insertQuery, new EmployeeMapper2());
			} catch (DataAccessException d) {
				System.out.println(d.getMessage());
				System.out.println(d.getLocalizedMessage());
			}
			System.out.println("Executed query for list");
			if (a == null) {
				System.out.println("a is null");
			} else {
				for (int i = 0; i < a.size(); i++) {
					System.out.println(a.get(i));
					System.out.println(((Employee) a.get(i)).getFirstName());
					toReturn.add(((Employee) a.get(i)).getFirstName() + 
							" " + ((Employee) a.get(i)).getLastName() + " " + ((Employee) a.get(i)).getEmployeeID());
				}
			}
			System.out.println(f_name + " " + l_name);
		}
		return toReturn;
	}
	
	// Used EmployeeMapper and just added password to it. Should create separate mapper class for just password, but this is POC
	@Override
	public int changePassword(String password, String newpassword, int id) {
		System.out.println("in changepassword");
		SqlParameterSource namedParameters = new MapSqlParameterSource("ids", Integer.valueOf(id));
		System.out.println("id: " + namedParameters.getValue("ids"));
		Employee empl = (Employee) jdbc.queryForObject("SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = :ids;",
				namedParameters, new EmployeeMapper());
		if (empl == null) {
			System.out.println("empl is null");
		} else {
			if (empl.getPassword().equals(password)) {
				System.out.println("Password matches, need to update password in database");
				try{
					System.out.println("In changepassword function, about to update employee password");
					Class.forName("org.h2.Driver");
					Connection con = DriverManager.getConnection("jdbc:h2:~/employeeService","sa","");
					PreparedStatement updatePreparedStatement = null;
					String insertQuery = "UPDATE EMPLOYEE SET (PASSWORD) = (?)"
							+ " WHERE EMPLOYEE_ID=" + id;
					try {
						updatePreparedStatement = con.prepareStatement(insertQuery);
						updatePreparedStatement.setString(1, newpassword);
						updatePreparedStatement.executeUpdate();
						updatePreparedStatement.close();
						
						System.out.println("Updated employee password in database");
					} catch (SQLException e) {
						System.out.println("Insert to database for changing password failed");
						System.out.println(e.getMessage());
					}
				}
				catch (Exception e){
					e.printStackTrace();
				}
			} else {
				System.out.println("Password does not match");
			}
		}
		return id;
	}

}
