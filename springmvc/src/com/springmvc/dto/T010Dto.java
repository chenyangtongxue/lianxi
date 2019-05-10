package com.springmvc.dto;

import com.springmvc.utils.annontation.CsvColumn;

public class T010Dto {

	/** 姓名 */
	@CsvColumn(index = 1)
	private String name;

	/** 性别 */
	@CsvColumn(index = 2)
	private String sex;

	/** 年龄 */
	@CsvColumn(index = 3)
	private String age;

	/** 作成ID */
	@CsvColumn(index = 4)
	private String sCrId;

	/** 作成时间 */
	@CsvColumn(index = 5)
	private String sCrDt;

	/** 更新ID */
	@CsvColumn(index = 6)
	private String sUpId;

	/** 更新时间 */
	@CsvColumn(index = 7)
	private String sUpDt;

	/** 版本号 */
	@CsvColumn(index = 8)
	private String sVersionNo;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getsCrId() {
		return sCrId;
	}

	public void setsCrId(String sCrId) {
		this.sCrId = sCrId;
	}

	public String getsCrDt() {
		return sCrDt;
	}

	public void setsCrDt(String sCrDt) {
		this.sCrDt = sCrDt;
	}

	public String getsUpId() {
		return sUpId;
	}

	public void setsUpId(String sUpId) {
		this.sUpId = sUpId;
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
		return "name=" + name + ", sex=" + sex + ", age=" + age + ", sCrId=" + sCrId + ", sCrDt=" + sCrDt
				+ ", sUpId=" + sUpId + ", sUpDt=" + sUpDt + ", sVersionNo=" + sVersionNo;
	}

}
