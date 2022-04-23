/**
 * @author Lijingwen
 *
 */
package com.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.SeckillMapper;
import com.demo.dao.SuccessKilledMapper;
import com.demo.entity.Seckill;
import com.demo.service.SeckillService;

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