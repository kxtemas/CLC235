package beans;
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
public class User {
	// Setting user first name with constraints
	@NotNull(message = "Please enter a first name")
	@Size(min = 5, max = 15)
	String firstName = "";

	// Setting user last name with constraints
	@NotNull(message = "Please enter a last name")
	@Size(min = 5, max = 15)
	String lastName = "";

	// Setting user email with constraints
	@NotNull(message = "Please enter an email")
	@Size(min = 5, max = 15)
	String email = "";

	// Setting username with constraints
	@NotNull(message = "Please enter an username")
	@Size(min = 5, max = 15)
	String username = "";

	// Setting password with constraints
	@NotNull(message = "Please enter a password")
	@Size(min = 5, max = 15)
	String password = "";

	// Setting password with constraints
	@NotNull(message = "Please enter a birthdate")
	@Size(min = 5, max = 15)
	String birthdate = "";

	// Constructor
	public User() {
		firstName = "";
		lastName = "";
		email = "";
		username = "";
		password = "";
		birthdate = "";
	}
	
	

	// First Name Getter
	public String getFirstName() {
		return firstName;
	}

	// First Name Setter
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Last Name Getter
	public String getLastName() {
		return lastName;
	}

	// Last Name Setter
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// Email Getter
	public String getEmail() {
		return email;
	}

	// Email Setter
	public void setEmail(String email) {
		this.email = email;
	}

	// Username Getter
	public String getUsername() {
		return username;
	}

	// Username Setter
	public void setUsername(String username) {
		this.username = username;
	}

	// Password Getter
	public String getPassword() {
		return password;
	}

	// Password Setter
	public void setPassword(String password) {
		this.password = password;
	}

	// Birthdate Getter
	public String getBirthdate() {
		return birthdate;
	}

	// Birthdate Setter
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	public String register()
	{
		boolean success=false;
		String newUser= username+ ","+password;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/munoz/cst235/CLC256/src/beans/users.txt", true));
			writer.newLine();
			writer.write(newUser);
			writer.close();
			success=true;
		}
		catch(IOException e) {
			System.out.println("Error");	
		}
		if (success) {
			return "LoginForm.xthml";
		}
		else {
			return "accountRegistration.xthml";
		}
				
		
		
	}
	
	public String authenticate() {
		boolean found = false;
		String attemptUsername = "";
		String attemptPassword = "";

		try {
			Scanner confirm = new Scanner(new File("/Users/munoz/cst235/CLC256/src/beans/users.txt"));
			confirm.useDelimiter("[,\n]");
			while (confirm.hasNext() && !found) {
				attemptUsername = confirm.next();
				attemptPassword = confirm.next();

				if (attemptUsername.trim().equals(username.trim()) && attemptPassword.trim().equals(password.trim())) {
					found = true;

				}
			}
			confirm.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error");

		}

		if (found) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", this);
			return "LoginSuccess.xhtml";
		} else {
			return "LoginFailed.xhtml";
		}

	}

}