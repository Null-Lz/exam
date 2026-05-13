package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable {
	private String subjectName;
	private String subject;
	private int num;
	private int point;
	/**
	 * @return subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName セットする subjectName
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	/**
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject セットする subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num セットする num
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @return point
	 */
	public int getPoint() {
		return point;
	}
	/**
	 * @param point セットする point
	 */
	public void setPoint(int point) {
		this.point = point;
	}
}
