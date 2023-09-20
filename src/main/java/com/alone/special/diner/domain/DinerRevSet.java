package com.alone.special.diner.domain;

import java.util.List;

public class DinerRevSet {
	private DinerRev dinerRev;
	private List<DinerRevFile> dRevFileList;
	public DinerRev getDinerRev() {
		return dinerRev;
	}
	public void setDinerRev(DinerRev dinerRev) {
		this.dinerRev = dinerRev;
	}
	public List<DinerRevFile> getdRevFileList() {
		return dRevFileList;
	}
	public void setdRevFileList(List<DinerRevFile> dRevFileList) {
		this.dRevFileList = dRevFileList;
	}
	@Override
	public String toString() {
		return "DinerRevSet [dinerRev=" + dinerRev + ", dRevFileList=" + dRevFileList + "]";
	}
	
	
}
