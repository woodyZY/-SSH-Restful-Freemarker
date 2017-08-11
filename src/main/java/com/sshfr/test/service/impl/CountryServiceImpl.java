package com.sshfr.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;

import com.sshfr.test.dao.CountryDao;
import com.sshfr.test.entity.Country;
import com.sshfr.test.service.CountryService;
@Service("countryService")
public class CountryServiceImpl implements CountryService {

	@Resource(name="countryDao")
	private CountryDao countryDao;
	@Override
	public void addCountry(Country country) {
		// TODO Auto-generated method stub
		List<Country>countrys = new ArrayList<Country>();
		countryDao.addBatch(countrys);
//		countryDao.addCountry(country);
	}

	@Override
	public void deleteCountry(Integer cid) {
		// TODO Auto-generated method stub
		Country country = countryDao.queryCountry(cid);
		countryDao.deleteCountry(country);
	}

	@Override
	public void updateCountry(Country country) {
		// TODO Auto-generated method stub
		Country countryTemp = countryDao.queryCountry(country.getCid());
		countryTemp.setCname(country.getCname());
		countryDao.updateCountry(countryTemp);
	}

	@Override
	public Country queryCountry(Integer cid) {
		// TODO Auto-generated method stub
		return countryDao.queryCountry(cid);
	}

	@Override
	public List<Country> queryAll() {
		// TODO Auto-generated method stub
		return countryDao.queryAll();
	}

}
