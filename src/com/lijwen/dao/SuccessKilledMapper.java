package com.lijwen.dao;

import org.apache.ibatis.annotations.Param;

import com.lijwen.entity.SuccessKilled;

public interface SuccessKilledMapper {

	/**
	 * 插入一条详细购买记录
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

	/**
	 * 根据秒杀商品Id查询SuccessKilled的明细信息
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
