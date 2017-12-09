package br.acme.exception;

public class RepositorioException extends Exception {

	private String msg;

	public RepositorioException(String msg) {
		super(msg);
	}

}