package com.sshfr.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sshfr.test.dao.CountryDao;
import com.sshfr.test.entity.Country;
@Repository("countryDao")
public class CountryDaoImpl implements CountryDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void addCountry(Country country) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(country);
	}

	@Override
	public void addBatch(List<Country> countrys) {
		// TODO Auto-generated method stub
		hibernateTemplate.getSessionFactory().getCurrentSession().doWork(new Work() {
			@Override
			public void execute(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				String sql = "insert into t_country(cname)values(?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				conn.setAutoCommit(false);
				try {
					for(int i=1;i<10001;i++){
						pStmt.setString(1, "woody");
						pStmt.addBatch();
						if(i%100==0){
							pStmt.executeBatch();
							conn.commit();
						}
					}
					pStmt.executeBatch();
					conn.commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					conn.rollback();
					e.printStackTrace();
				}finally{
					if(null!=conn){
						pStmt.close();
					}
				}
			}
		});
	}
	
	@Override
	public void deleteCountry(Country country) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(country);
	}

	@Override
	public void updateCountry(Country country) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(country);
	}

	@Override
	public Country queryCountry(Integer cid) {
		// TODO Auto-generated method stub
		String sql = "from Country c where c.cid=?";
		@SuppressWarnings("unchecked")
		List<Country>countrys = (List<Country>) hibernateTemplate.find(sql, cid);
		return countrys.get(0); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> queryAll() {
		// TODO Auto-generated method stub
		String sql = "from Country";
		return (List<Country>) hibernateTemplate.find(sql);
	}

	

	

}
