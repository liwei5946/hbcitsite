package cn.edu.hbcit.pojo;

public class Log {
	private int id;
	private String logtime;
	private String msg;
	private String loglevel;
	private String logclass;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getLoglevel() {
		return loglevel;
	}
	public void setLoglevel(String loglevel) {
		this.loglevel = loglevel;
	}
	public String getLogclass() {
		return logclass;
	}
	public void setLogclass(String logclass) {
		this.logclass = logclass;
	}
}
