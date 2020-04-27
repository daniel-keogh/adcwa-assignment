package com.sales.models;

import javax.validation.constraints.Min;

public class OrderForm {
	private Long pId;
	private Long cId;
	
	@Min(1)
	private int qty;
	
	public Long getpId() {
		return pId;
	}
	
	public void setpId(Long pId) {
		this.pId = pId;
	}
	
	public Long getcId() {
		return cId;
	}
	
	public void setcId(Long cId) {
		this.cId = cId;
	}
	
	public int getQty() {
		return qty;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	@Override
	public String toString() {
		return "OrderForm [pId=" + pId + ", cId=" + cId + ", qty=" + qty + "]";
	}
}
