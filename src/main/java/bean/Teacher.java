package bean;

import java.io.Serializable;

public class Teacher extends User implements Serializable {
	private String id;
	private String password;
	private String name;
	private School school;
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
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
