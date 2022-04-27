package fr.fms.entities;

public class Book {
	
	private int id;
	private String title;
	private String author;
	private String editor;
	private String description;
	private double unitaryPrice;
	private int themeId;
	
	//To manipulate cart for later
	private int quantity;
	
	/**
	 * 
	 */
	public Book() {
	}

	/**
	 * 
	 * @param id
	 * @param title
	 * @param author
	 * @param editor
	 * @param description
	 * @param unitaryPrice
	 */
	public Book(int id, String title, String author, String editor, String description, double unitaryPrice) {//, int idTheme) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.editor = editor;
		this.description = description;
		this.unitaryPrice = unitaryPrice;
		//this.themeId = idTheme;
	}
	
	public Book(int id, String title, String author, String editor, String description, double unitaryPrice, int quantity) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.editor = editor;
		this.description = description;
		this.unitaryPrice = unitaryPrice;
		this.quantity = quantity;
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
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public double getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", editor=" + editor + ", description="
				+ description + ", unitaryPrice=" + unitaryPrice + "]";
	}

	public int getThemeId() {
		return themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
