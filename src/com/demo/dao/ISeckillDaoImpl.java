package com.demo.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.demo.entity.Seckill;

public class ISeckillDaoImpl extends JdbcDaoSupport implements ISeckillDao {

	
	
	@Override
	protected void checkDaoConfig() {
		// TODO Auto-generated method stub
		super.checkDaoConfig();
	}

	@Override
	protected JdbcTemplate createJdbcTemplate(DataSource dataSource) {
		// TODO Auto-generated method stub
		return super.createJdbcTemplate(dataSource);
	}

	@Override
	protected void initTemplateConfig() {
		// TODO Auto-generated method stub
		super.initTemplateConfig();
	}

	@Override
	public String getSeckillName(Integer seckillId) {
		String sql = "select name from seckill where seckill_id = "+ seckillId;
		return this.getJdbcTemplate().queryForObject(sql, String.class);
	}

	@Override
	public void addSeckill(Seckill sk) {
		String sql = "insert into seckill(name,number,start_time,end_time) values (?,?,?,?)";
		this.getJdbcTemplate().update(sql, sk.getName(),sk.getNumber(),sk.getStarttime(),sk.getEndtime());
	}

//	@Override
//	public List<Seckill> getSkList() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
