package cn.edu.hbcit.util;


/**
 * ������������ͳ�Ƶķ�װ����
 * @param countType ͳ�Ƶ�����=1--������ͳ�ƣ�=2--������ͳ��
 * @param startTime endTime ʱ��
 * @return
 *   syj
 */
public class CountNews {
	private String Unit;//ͳ�Ƶ�λ�����߻���
	private int Numbs;//ͳ������
	private String startTime;//ʱ�ε���ʵʱ��
	private String endTime;//ʱ�ε���ֹʱ��
	
	
	
	
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
