package cn.edu.hbcit.util;

public class UserRight {
	private String rightName;
	private int rightID;
	private int lmLevel;
	private int pRightID;//
	
	public int getpRightID() {
		return pRightID;
	}
	public void setpRightID(int pRightID) {
		this.pRightID = pRightID;
	}
	public int getLmLevel() {
		return lmLevel;
	}
	public void setLmLevel(int lmLevel) {
		this.lmLevel = lmLevel;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public int getRightID() {
		return rightID;
	}
	public void setRightID(int rightID) {
		this.rightID = rightID;
	}
}
