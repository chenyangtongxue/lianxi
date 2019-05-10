package com.springmvc.dto;

import com.springmvc.utils.annontation.CsvColumn;

public class T020Dto {

	/** 登录地点 */
	@CsvColumn(index = 5)
	private String place;

	/** 用户·姓 */
	@CsvColumn(index = 2)
	private String lastName;

	/** 用户·名 */
	@CsvColumn(index = 2)
	private String firstName;

	/** 用户昵称 */
	@CsvColumn(index = 3)
	private String name;

	/** 登录日期 */
	@CsvColumn(index = 4)
	private String time;

	/** 测试 */
	private String test;

	/** S作成日時 */
	@CsvColumn(index = 7)
	private String sInsDt;

	/** 作成ID */
	private String sCrId;

	/** 更新ID */
	@CsvColumn(index = 8)
	private String sUpId;

	/** 用户ID */
	@CsvColumn(index = 1)
	private String userId;

	/** 更新时间 */
	@CsvColumn(index = 9)
	private String sUpDt;

	/** 版本号 */
	@CsvColumn(index = 10)
	private String sVersionNo;

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getsInsDt() {
		return sInsDt;
	}

	public void setsInsDt(String sInsDt) {
		this.sInsDt = sInsDt;
	}

	public String getsCrId() {
		return sCrId;
	}

	public void setsCrId(String sCrId) {
		this.sCrId = sCrId;
	}

	public String getsUpId() {
		return sUpId;
	}

	public void setsUpId(String sUpId) {
		this.sUpId = sUpId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getsUpDt() {
		return sUpDt;
	}

	public void setsUpDt(String sUpDt) {
		this.sUpDt = sUpDt;
	}

	public String getsVersionNo() {
		return sVersionNo;
	}

	public void setsVersionNo(String sVersionNo) {
		this.sVersionNo = sVersionNo;
	}

	@Override
	public String toString() {
		return "place=" + place + ", lastName=" + lastName + ", firstName=" + firstName + ", name=" + name
				+ ", time=" + time + ", test=" + test + ", sInsDt=" + sInsDt + ", sCrId=" + sCrId + ", sUpId=" + sUpId
				+ ", userId=" + userId + ", sUpDt=" + sUpDt + ", sVersionNo=" + sVersionNo;
	}
	
	

}
