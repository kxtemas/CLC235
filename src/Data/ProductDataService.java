package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Database;
import beans.Products;

public class ProductDataService {
	
	private Database connection = null;

	/**
	 * Non defualt constructor takes database parameter
	 * @param connection
	 */
	public ProductDataService(Database connection) 
	{
		this.connection = connection;
	}

	/**
	 * Insert product to database
	 * @param product
	 * @return
	 */
	public boolean createProduct(Products product) {
		Connection conn = null;

		// tries connecting to the database and entering the product data into a
		// database table,
		// but prints an error message if it fails to connect or insert the data.
		try {
			// get database connection
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
			String sql = "INSERT INTO clc235.products(id,book_name, book_author, price, book_description, book_genre, quantity) VALUES (?,?, ?, ?, ?, ?, ?)";

			// prepare sql statement
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, product.getBookName());
			stmt.setString(2, product.getBookAuthor());
			stmt.setFloat(3, product.getPrice());
			stmt.setString(4, product.getBookDescription());
			stmt.setString(5, product.getBookGenre());
			stmt.setInt(6, product.getQuantity());

			if (stmt.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}

		}
		// prints error message if fails to connect or insert data into the database
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Find all products in the products database table
	 * @return
	 */
	public List<Products> findAllProducts() 
	{
		Connection conn = null;

		List<Products> products = new ArrayList<Products>();
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
			String sql = "SELECT * FROM clc235.products";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				products.add(new Products(-1, rs.getString("book_name"), rs.getFloat("price"), rs.getString("book_author"), rs.getString("book_description"), rs.getString("book_genre"),  rs.getInt("quantity")));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return products;
	}

	/**
	 * Delete product by id
	 */
	public void deleteProduct(int book_id)
	{
		try
		{
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
			String sql = "DELETE FROM clc235.products WHERE id=" + book_id + ";";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
	}

	/**
	 * Update product by id
	 */
	 public void updateProduct(int book_id, String bookName, float price, String bookGenre, String bookAuthor, String bookDescription, int quantity)
	 {
		try
		{
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
			String sql = "UPDATE clc235.products SET book_name=" + bookName + ", book_author=" + bookAuthor + ", price=" + price + ", book_description=" + bookDescription + ", book_genre=" + bookGenre + ", quantity=" + quantity + " WHERE id=" + book_id + ";";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
	 }
}