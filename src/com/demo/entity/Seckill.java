package com.demo.entity;

import java.util.Date;

public class Seckill {

	private long seckillId;

	private String name;

	private int number;

	private Date createtime;

	private Date starttime;

	private Date endtime;

	public long getSeckillId() {
		return seckillId;
	}
	
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	@Override
	public String toString() {
		return "Seckill [seckillId=" + seckillId + ", name=" + name + ", number=" + number + ", createtime="
				+ createtime + ", starttime=" + starttime + ", endtime=" + endtime + "]";
	}

}
