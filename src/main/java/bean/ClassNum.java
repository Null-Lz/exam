package bean;

import java.io.Serializable;

public class ClassNum implements Serializable {
	private String class_num;
	private School school;
	/**
	 * @return class_num
	 */
	public String getClass_num() {
		return class_num;
	}
	/**
	 * @param class_num セットする class_num
	 */
	public void setClass_num(String class_num) {
		this.class_num = class_num;
	}
	/**
	 * @return school
	 */
	public School getSchool() {
		return school;
	}
	/**
	 * @param school セットする school
	 */
	public void setSchool(School school) {
		this.school = school;
	}
	
}
