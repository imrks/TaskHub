package com.stackhack.taskmanagement.response;

public class Response {
	private int code;
	private String message;
	public Response(String message, int code) {
		super();
		this.setCode(code);
		this.setMessage(message);
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
