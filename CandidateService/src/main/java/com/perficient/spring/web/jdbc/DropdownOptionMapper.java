package com.perficient.spring.web.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.perficient.spring.web.model.DropdownOption;

public class DropdownOptionMapper {
	public DropdownOption mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub

		DropdownOption en = new DropdownOption();

		en.setId(rs.getInt("ENUM_ID"));

		en.setDescription(rs.getString("DESCRIPTION"));

		return en;

	}

}
