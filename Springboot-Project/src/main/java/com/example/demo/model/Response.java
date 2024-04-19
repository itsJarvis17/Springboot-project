package com.example.demo.model;

public class Response {

	private int id;
	private String userResponse;

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUserResponse() {
		return userResponse;
	}



	public void setUserResponse(String userResponse) {
		this.userResponse = userResponse;
	}



	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Response(int id, String userResponse) {
		super();
		this.id = id;
		this.userResponse = userResponse;
	}

	
	
}
