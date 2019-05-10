package com.springmvc.dto;

import static com.springmvc.utils.FormatUtils.FORMAT_DATE_2;
import static com.springmvc.utils.FormatUtils.FORMAT_INT_2;

import java.util.Date;

import com.springmvc.utils.annontation.CsvColumn;

public class T021Dto {

	/** 登录地点 */
	@CsvColumn(index = 5)
	private String place;

	/** 用户姓名 */
	@CsvColumn(index = 2)
	private String realName;

	/** 用户昵称 */
	@CsvColumn(index = 3)
	private String vName;

	/** 登录日期 */
	@CsvColumn(index = 4, targetFormat = FORMAT_DATE_2)
	private Date time;

	/** 用户ID */
	@CsvColumn(index = 1, targetFormat = FORMAT_INT_2)
	private String userId;

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}
