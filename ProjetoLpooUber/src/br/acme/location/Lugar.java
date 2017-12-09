package br.acme.location;

import java.io.Serializable;

public class Lugar implements Serializable{

	//ATRIBUTOS
	private String identificadorLugar;
	private String endereco;

	//CONSTRUTOR
	public Lugar (String identificadorLugar, String endereco){
		this.identificadorLugar = identificadorLugar;
		this.endereco = endereco;
	}

	//TO STRING
	@Override
	public String toString() {
		return "Lugar [identificadorLugar=" + identificadorLugar + ", endereco=" + endereco + "]";
	}


	//GETS E SETS
	public void setIdLugar(String identificadorLugar){
		this.identificadorLugar = identificadorLugar;
	}

	public String getIdLugar(){
		return identificadorLugar;
	}

	public void setEndereco(String endereco){
		this.endereco = endereco;
	}

	public String getEndereco(){
		return endereco;
	}

}
