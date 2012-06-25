package cn.edu.hbcit.pojo;

public class DynamicSetLm {
	
	private int id;
	private String indexLmNo;//板块序号
	private String lm;//板块根栏目号
	private String lm2;////板块二级栏目号
	private String lm3;//板块三级栏目号
	private String indexLmPic;//板块top底片路径
	private String indexLmTitle;//板块标题
	private String indexLmTitleStyle;//板块标题风格
	private String notes;//板块说明

	
	
	
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIndexLmNo() {
		return indexLmNo;
	}
	public void setIndexLmNo(String indexLmNo) {
		this.indexLmNo = indexLmNo;
	}
	public String getLm() {
		return lm;
	}
	public void setLm(String lm) {
		this.lm = lm;
	}
	public String getLm2() {
		return lm2;
	}
	public void setLm2(String lm2) {
		this.lm2 = lm2;
	}
	public String getLm3() {
		return lm3;
	}
	public void setLm3(String lm3) {
		this.lm3 = lm3;
	}
	public String getIndexLmPic() {
		return indexLmPic;
	}
	public void setIndexLmPic(String indexLmPic) {
		this.indexLmPic = indexLmPic;
	}
	public String getIndexLmTitle() {
		return indexLmTitle;
	}
	public void setIndexLmTitle(String indexLmTitle) {
		this.indexLmTitle = indexLmTitle;
	}
	public String getIndexLmTitleStyle() {
		return indexLmTitleStyle;
	}
	public void setIndexLmTitleStyle(String indexLmTitleStyle) {
		this.indexLmTitleStyle = indexLmTitleStyle;
	}


}
