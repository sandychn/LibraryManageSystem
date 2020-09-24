package entity;

import java.util.Date;

// 借阅记录实体类
public class BookLendingRecord {

	private String bISBN, bName, uName;
	private Date blTime;
	private Date brTime; // brTime = null 表示未还
	private long blDue;

	public BookLendingRecord(String bISBN, String bName, String uName, Date blTime, Date brTime, long blDue) {
		this.bISBN = bISBN;
		this.bName = bName;
		this.uName = uName;
		this.blTime = blTime;
		this.brTime = brTime;
		this.blDue = blDue;
	}

	public String getbISBN() {
		return bISBN;
	}

	public void setbISBN(String bISBN) {
		this.bISBN = bISBN;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public Date getBlTime() {
		return blTime;
	}

	public void setBlTime(Date blTime) {
		this.blTime = blTime;
	}

	public Date getBrTime() {
		return brTime;
	}

	public void setBrTime(Date brTime) {
		this.brTime = brTime;
	}

	public long getBlDue() {
		return blDue;
	}

	public void setBlDue(long blDue) {
		this.blDue = blDue;
	}

}
