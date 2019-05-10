package com.springmvc.dto;

import com.springmvc.utils.annontation.CsvColumn;

public class T011Dto {
	
	/** 姓名 */
	@CsvColumn(index = 1)
	private String name;

	/** 性别 */
	@CsvColumn(index = 2)
	private String sex;

	/** 年龄 */
	@CsvColumn(index = 3)
	private int age;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

}
