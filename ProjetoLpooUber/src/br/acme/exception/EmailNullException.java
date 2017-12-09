package br.acme.exception;

public class EmailNullException extends Exception{
	
	private String msg;
	
	public EmailNullException(String msg) {
		super(msg);
	}

}