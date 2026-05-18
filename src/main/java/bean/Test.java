package bean;

import java.io.Serializable;

public class Test implements Serializable {
	private Student student;
	private String classNum;
	private Subject subject;
	private School school;
	private int no;
	private int point;
	/**
	 * @return student
	 */
	public Student getStudent() {
		return student;
	}
	/**
	 * @param student セットする student
	 */
	public void setStudent(Student student) {
		this.student = student;
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
	 * @return subject
	 */
	public Subject getSubject() {
		return subject;
	}
	/**
	 * @param subject セットする subject
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
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
	/**
	 * @return no
	 */
	public int getNo() {
		return no;
	}
	/**
	 * @param no セットする no
	 */
	public void setNo(int no) {
		this.no = no;
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
