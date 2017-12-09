package br.acme.exception;

public class SenhaVaziaException extends Exception {

private String msg;
	
	public SenhaVaziaException(String msg) {
		super(msg);
	}

}
