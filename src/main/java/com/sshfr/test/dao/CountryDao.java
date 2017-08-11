package com.sshfr.test.dao;

import java.util.List;

import com.sshfr.test.entity.Country;

public interface CountryDao {
	void addCountry(Country country);
	void addBatch(List<Country>countrys);
	void deleteCountry(Country country);
	void updateCountry(Country country);
	Country queryCountry(Integer cid);
	List<Country>queryAll();
}
