package fr.fms.entities;

public class Category {
	private int id;
	private String catName;

	/**
	 *
	 */
	public Category() {
	}
	
	/**
	 * 
	 * @param id
	 * @param catName
	 */
	public Category(int id, String catName) {
		super();
		this.id = id;
		this.catName = catName;
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
	public String getCatName() {
		return catName;
	}

	/**
	 * 
	 * @param catName
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Category [id=" + id + ", catName=" + catName + "]";
	}

}
