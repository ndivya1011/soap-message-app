package com.calculator.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.calculator.model.Gcd;
import com.calculator.model.GcdMapper;

@Component
public class GcdDaoImpl implements GcdDao {
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public GcdDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private final String SQL_GET_ALL = "select gcd from calculator";
	private final String SQL_INSERT_PERSON = "insert into calculator(id, gcd values(?,?)";

	@Override
	public List<Gcd> getAllGcd() {
		return jdbcTemplate.query(SQL_GET_ALL, new GcdMapper());
	}

	@Override
	public boolean insertGcd(Gcd gcd) {
		return jdbcTemplate.update(SQL_INSERT_PERSON, gcd.getId(), gcd.getGcd()) > 0;
	}

}
