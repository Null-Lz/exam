package bean;

import java.io.Serializable;

public class School implements Serializable {
	private String cd;
	private String name;
	/**
	 * @return cd
	 */
	public String getCd() {
		return cd;
	}
	/**
	 * @param cd セットする cd
	 */
	public void setCd(String cd) {
		this.cd = cd;
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
	
	
}
