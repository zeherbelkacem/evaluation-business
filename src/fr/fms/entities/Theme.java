package fr.fms.entities;

public class Theme {
	private int id;
	private String themeName;

	/**
	 *
	 */
	public Theme() {
	}
	
	/**
	 * 
	 * @param id
	 * @param catName
	 */
	public Theme(int id, String themeName) {
		super();
		this.id = id;
		this.themeName = themeName;
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getThemeName() {
		return themeName;
	}

	/**
	 * 
	 * @param catName
	 */
	public void setCatName(String themeName) {
		this.themeName = themeName;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Category [id=" + id + ", themeName=" + themeName + "]";
	}

}