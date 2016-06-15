package com.perficient.spring.web.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.perficient.spring.web.model.EnumTableRow;

public class EnumTableRowMapper {
	public EnumTableRow mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub

		EnumTableRow en = new EnumTableRow();

		en.setId(rs.getInt("ENUM_ID"));

		en.setDescription(rs.getString("DESCRIPTION"));

		return en;

	}

}
