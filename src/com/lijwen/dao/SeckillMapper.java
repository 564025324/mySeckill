package com.lijwen.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lijwen.entity.Seckill;

public interface SeckillMapper {
	/**
	 * 减库存
	 * 
	 * 如果影响行数等于1，表示更新的记录为1条。
	 */
	int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime") Date killTime);
	
	/**
	 * 根据ID查商品信息
	 * 
	 */
	Seckill queryById(@Param("seckillId")long seckillId);
	
	/**
	 * 根据偏移量查询秒杀商品列表
	 * 
	 */
	List<Seckill> queryAll(@Param("iffet")int iffet,@Param("limit") int limit);
	
	

}
