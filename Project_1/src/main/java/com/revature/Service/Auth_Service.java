package com.revature.Service;



public class Auth_Service {
	
	public int login(String username, String password) {
		if(username.equals("manager") && password.equals("password")) {
			return 1;
		}
		else if(username.equals("employee") && password.equals("password")) {
			return 2;
		}
		else {
			return 0;
		}
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
}
	

