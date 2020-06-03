package main.models;

public class User {
	private String user_name;
	private String user_pass;
	private String user_type;
	private UserRole user_role;

	public User(String user_name, String user_pass, UserRole user_role) {
		this.user_name = user_name;
		this.user_pass = user_pass;
		this.user_role = user_role;
	}

	public User(String user_name, String user_pass, String user_type) {
		this.user_name = user_name;
		this.user_pass = user_pass;
		this.user_type = user_type;
	}

	public User() {
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public UserRole getUser_role() {
		return user_role;
	}

	public void setUser_role(UserRole user_role) {
		this.user_role = user_role;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}



}
