package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject implements Serializable {
	private int entYear;
	private String studentNo;
	private String studentName;
	private String classNum;
	private Map<Integer, Integer> points;
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
	 * @return studentNo
	 */
	public String getStudentNo() {
		return studentNo;
	}
	/**
	 * @param studentNo セットする studentNo
	 */
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	/**
	 * @return studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName セットする studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	 * @return points
	 */
	public Map<Integer, Integer> getPoints() {
		return points;
	}
	/**
	 * @param points セットする points
	 */
	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}
}
