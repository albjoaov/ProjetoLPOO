package br.acme.exception;

public class SenhaNullException extends Exception {
	
	private String msg;
	
	public SenhaNullException(String msg) {
		super(msg);
	}

}
