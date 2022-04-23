/**
 * @author Lijingwen
 */
package com.demo.service;

import com.demo.entity.Seckill;

import java.util.List;


public interface SeckillService {
	/**
	 * 查询所有秒杀
	 */
	List<Seckill> getSeckillList();

	/**
	 * 查询单个秒杀商品信息
	 */
	Seckill getById(long seckillId);

	/**
	 * 开启秒杀时输出秒杀接口的地址，否则输出秒杀时间和系统时间
	 */

	/**
	 * 执行秒杀操作
	 */

}
