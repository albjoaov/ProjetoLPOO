package br.acme.exception;

public class EmailVazioException extends Exception {

private String msg;
	
	public EmailVazioException(String msg) {
		super(msg);
	}

}
