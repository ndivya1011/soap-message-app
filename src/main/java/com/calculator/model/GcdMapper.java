package com.calculator.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GcdMapper implements RowMapper<Gcd> {

	@Override
	public Gcd mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Gcd gcd = new Gcd();
		gcd.setId(resultSet.getLong("id"));
		gcd.setGcd(resultSet.getInt("gcd"));
		return gcd;
	}

}
