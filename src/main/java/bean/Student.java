package bean;

import java.io.Serializable;

public class Student implements Serializable {
	private String no;
	private String name;
	private int entYear;
	private String classNum;
	private boolean isAttend;
	private School school;
	/**
	 * @return no
	 */
	public String getNo() {
		return no;
	}
	/**
	 * @param no セットする no
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return entYear
	 */
	public int getEntYear() {
		return entYear;
	}
	/**
	 * @param entYear セットする entYear
	 */
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	/**
	 * @return classNum
	 */
	public String getClassNum() {
		return classNum;
	}
	/**
	 * @param classNum セットする classNum
	 */
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	/**
	 * @return isAttend
	 */
	public boolean isAttend() {
		return isAttend;
	}
	/**
	 * @param isAttend セットする isAttend
	 */
	public void setAttend(boolean isAttend) {
		this.isAttend = isAttend;
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
