/**
 * @author Lijingwen
 *
 */
package com.lijwen.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lijwen.dao.SeckillMapper;
import com.lijwen.dao.SuccessKilledMapper;
import com.lijwen.entity.Seckill;
import com.lijwen.entity.SuccessKilled;
import com.lijwen.service.SeckillService;

@Service("seckillService")
public class SeckillServiceImp implements SeckillService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillMapper seckillMapper;

	@Autowired
	private SuccessKilledMapper successKilledMapper;

	@Override
	public Seckill getById(long seckillId) {
		Seckill sk = seckillMapper.queryById(seckillId);
		return sk;
	}
	
	@Override
	public List<Seckill> getSeckillList() {
		List<Seckill> skList = seckillMapper.queryAll(0, 10);
		return skList;
	}

}