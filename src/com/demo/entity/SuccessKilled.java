package com.demo.entity;

import java.util.Date;

public class SuccessKilled {
	
	private long seckillId;
	
	private long userphone;
	
	private short stat;
	
	private Date createtime;
	
	private Seckill seckill;

	
	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getUserphone() {
		return userphone;
	}

	public void setUserphone(long userphone) {
		this.userphone = userphone;
	}

	public short getStat() {
		return stat;
	}

	public void setStat(short stat) {
		this.stat = stat;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "SuccessKilled [seckillId=" + seckillId + ", userphone=" + userphone + ", stat=" + stat + ", createtime="
				+ createtime + "]";
	}

}
