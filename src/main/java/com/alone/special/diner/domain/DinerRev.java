package com.alone.special.diner.domain;

import java.sql.Timestamp;

public class DinerRev {
	private int fDinerRevId;
	private String fDinerRevTitle;
	private String fDinerRevContent;
	private float fDinerRevStar;
	private Timestamp fDinerRevCDate;
	private Timestamp fDinerRevUDate;
	private int refFDinerId;
	private String fUserId;
	public int getfDinerRevId() {
		return fDinerRevId;
	}
	public void setfDinerRevId(int fDinerRevId) {
		this.fDinerRevId = fDinerRevId;
	}
	public String getfDinerRevTitle() {
		return fDinerRevTitle;
	}
	public void setfDinerRevTitle(String fDinerRevTitle) {
		this.fDinerRevTitle = fDinerRevTitle;
	}
	public String getfDinerRevContent() {
		return fDinerRevContent;
	}
	public void setfDinerRevContent(String fDinerRevContent) {
		this.fDinerRevContent = fDinerRevContent;
	}
	public float getfDinerRevStar() {
		return fDinerRevStar;
	}
	public void setfDinerRevStar(float fDinerRevStar) {
		this.fDinerRevStar = fDinerRevStar;
	}
	public Timestamp getfDinerRevCDate() {
		return fDinerRevCDate;
	}
	public void setfDinerRevCDate(Timestamp fDinerRevCDate) {
		this.fDinerRevCDate = fDinerRevCDate;
	}
	public Timestamp getfDinerRevUDate() {
		return fDinerRevUDate;
	}
	public void setfDinerRevUDate(Timestamp fDinerRevUDate) {
		this.fDinerRevUDate = fDinerRevUDate;
	}
	public int getRefFDinerId() {
		return refFDinerId;
	}
	public void setRefFDinerId(int refFDinerId) {
		this.refFDinerId = refFDinerId;
	}

	public String getfUserId() {
		return fUserId;
	}
	public void setfUserId(String fUserId) {
		this.fUserId = fUserId;
	}
	@Override
	public String toString() {
		return "DinerRev [fDinerRevId=" + fDinerRevId + ", fDinerRevNo=" +  fDinerRevTitle + ", fDinerRevContent=" + fDinerRevContent + ", fDinerRevStar=" + fDinerRevStar
				+ ", fDinerRevCDate=" + fDinerRevCDate + ", fDinerRevUDate=" + fDinerRevUDate + ", refFDinerId="
				+ refFDinerId + ", fUserId=" + fUserId + "]";
	}

	
	
	
	
	
}
