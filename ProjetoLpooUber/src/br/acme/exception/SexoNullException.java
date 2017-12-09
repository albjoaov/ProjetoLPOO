package br.acme.exception;

public class SexoNullException extends Exception {

	private String msg;

	public SexoNullException(String msg) {
		super(msg);
	}
}
