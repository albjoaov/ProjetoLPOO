package br.acme.exception;

public class SexoVazioException extends Exception{
	private String msg;

	public SexoVazioException(String msg){
		super(msg);
	}
}
