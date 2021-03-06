package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
public class Products {
	
	int id = 0;

	@NotNull(message = "Please, enter product name!")
	@Size(min = 4, max = 15)
	String bookName = "";
	
	@NotNull(message = "Please, enter product price!")
	@Max(100000)
	float price = 0;
	
	@NotNull(message = "Please, enter product category!")
	@Size(min = 4, max = 100)
	String bookGenre = "";
	
	@NotNull(message = "Please, enter product short description!")
	@Size(min = 4, max = 100)
	String bookAuthor = "";
	
	@NotNull(message = "Please, enter product long description!")
	@Size(min = 4, max = 500)
	String bookDescription = "";
	
	@NotNull(message = "Please, enter product quantity!")
	@Max(10000)
	int quantity = 0;
	
	
	
	public Products(int id, String bookName, float price, String bookGenre, String bookAuthor, String bookDescription,
			int quantity) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.price = price;
		this.bookGenre = bookGenre;
		this.bookAuthor = bookAuthor;
		this.bookDescription = bookDescription;
		this.quantity = quantity;
	}
	
	public Products()
	{
		bookName = "";
		price = 0;
		bookGenre = "";
		bookDescription = "";
		bookAuthor = "";
		quantity = 1;
	}
	// product properties
	// data validation for all fields except id
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getBookGenre() {
		return bookGenre;
	}


	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}


	public String getBookAuthor() {
		return bookAuthor;
	}


	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}


	public String getBookDescription() {
		return bookDescription;
	}


	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	}