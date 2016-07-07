package com.perficient.spring.web.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.perficient.spring.web.jdbc.CourseMapper;
import com.perficient.spring.web.model.Course;

@Component
public class CourseRepositoryImpl implements CourseRepository{

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
	
	
	@Override
	public ArrayList<String> findAll(String searchKeyWord) {
		// TODO Auto-generated method stub
		List<Object> a = null;
		ArrayList<String> toReturn = new ArrayList<String>();
		System.out.println("in findall method");
		String[] searchParams = StringUtils.split(searchKeyWord, " ");
		//String f_name = "", l_name = "";
		String searchCrTitle = null, searchCrTitle1 = "COURSE TITLE LIKE %", searchCrTitle2 = "%";
		
		for(int i =0; i<searchParams.length; i++){
			searchCrTitle = searchCrTitle + searchCrTitle1 + searchParams[i] + searchCrTitle2 ;
			if(i+1 != searchParams.length){
				searchCrTitle = searchCrTitle +"OR";
			}
		}
		
	//	if (searchParams != null) {
			System.out.println("There is one argument in search bar");
			try {
				//int searchInt = Integer.parseInt(params);
				// Know it is an int, so they are searching by employee id
				String insertQuery = "SELECT COURSE_TITLE FROM COURSE WHERE" + searchCrTitle;
				try {
					a =  jdbcTemplate.query(insertQuery, new CourseMapper());
				} catch (DataAccessException d) {
					System.out.println(d.getMessage());
					System.out.println(d.getLocalizedMessage());
				}
				if (a == null) {
					System.out.println("a is null");
				} else {
					for (int i = 0; i < a.size(); i++) {
						System.out.println(a.get(i));
						System.out.println(((Course) a.get(i)).getCourseTitle());
						toReturn.add(((Course) a.get(i)).getCourseTitle());
					}
				}
				System.out.println("Executed query based on person id");
			} catch (Exception e) {
				e.printStackTrace();
			}
		//} else  {

		//	System.out.println("search key word is empty");
		//	to
		//}
		return toReturn;
		
		
	}
	
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<Course> arg0) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public List<Course> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> findAll(Iterable<Integer> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Course getOne(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Course> List<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Course> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Course> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Course arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Course> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Integer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Course findOne(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Course> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
