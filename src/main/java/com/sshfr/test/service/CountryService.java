package com.sshfr.test.service;

import java.util.List;

import com.sshfr.test.entity.Country;

public interface CountryService {
	void addCountry(Country country);
	void deleteCountry(Integer cid);
	void updateCountry(Country country);
	Country queryCountry(Integer cid);
	List<Country>queryAll();
}
