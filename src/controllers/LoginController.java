package controllers;

import javax.faces.bean.ManagedBean; 
import javax.faces.context.FacesContext;
import beans.User;

@ManagedBean
public class LoginController {

	public String onSubmit() {
		//get the user values from the input form
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		//shows the user object data in the console log
		System.out.println("----------------------------------------------");
		System.out.println("the username is " + user.getUserName());
		System.out.println("the password is " + user.getPassWord());
	
		//put the user object into the POST request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		//shows the next page
		return "LoginResponse.xhtml";
	}

	}

