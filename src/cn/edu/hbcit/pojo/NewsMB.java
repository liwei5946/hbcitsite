package cn.edu.hbcit.pojo;



public class NewsMB {

	private int id;
	private String mid;//内存储模板页内容
	private String icon;//内存显示新闻前面的图标
	private String title;//内存显示新闻前面的图标
	private String listmb;//内存显示新闻列示的模版
	
	
	
	
	public String getListmb() {
		return listmb;
	}
	public void setListmb(String listmb) {
		this.listmb = listmb;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id= id;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
}
