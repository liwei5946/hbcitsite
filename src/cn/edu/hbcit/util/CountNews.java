package cn.edu.hbcit.util;


/**
 * 发布新闻数量统计的封装对象
 * @param countType 统计的类型=1--按作者统计；=2--按部门统计
 * @param startTime endTime 时段
 * @return
 *   syj
 */
public class CountNews {
	private String Unit;//统计单位：作者或部门
	private int Numbs;//统计数量
	private String startTime;//时段的其实时间
	private String endTime;//时段的终止时间
	
	
	
	
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public int getNumbs() {
		return Numbs;
	}
	public void setNumbs(int numbs) {
		Numbs = numbs;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
