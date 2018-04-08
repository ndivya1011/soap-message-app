package com.calculator.dao;

import java.util.List;

import com.calculator.model.Gcd;

public interface GcdDao {

	List<Gcd> getAllGcd();

	boolean insertGcd(Gcd gcd);

}
