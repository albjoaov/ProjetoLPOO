package br.acme.location;

import java.io.Serializable;

import br.acme.users.*;

public class Viagem implements Serializable {

	//ATRIBUTOS
	private long id;
	private Solicitante cliente;
	private Motorista motorista;
	private Lugar origem;
	private Lugar destino;
	private double valorViagem;
	private String formaPagamento;
	
	//CONSTRUTOR
	public Viagem(long id, Solicitante cliente, Motorista motorista, Lugar origem, Lugar destino,
			double valorViagem, String formaPagamento) {
		this.id = id;
		this.cliente = cliente;
		this.motorista = motorista;
		this.origem = origem;
		this.destino = destino;
		this.valorViagem = valorViagem;
		this.formaPagamento = formaPagamento;
	}
	
	//TO STRING
	@Override
	public String toString() {
		return "Viagem [id=" + id + ", cliente=" + cliente + ", motorista=" + motorista + ", origem=" + origem
				+ ", destino=" + destino + ", valorViagem=" + valorViagem + ", formaPagamento=" + formaPagamento + "]"
				+ "	Motorista da viagem [nome = " + motorista.getNome() + "]";
	}

	// GETS E SETS
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public double getValorViagem() {
		return valorViagem;
	}

	public void setValorViagem(double valorViagem) {
		this.valorViagem = valorViagem;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Solicitante getSolicitante() {
		return cliente;
	}

	public void setSolicitante(Solicitante cliente) {
		this.cliente = cliente;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Lugar getOrigem() {
		return origem;
	}

	public void setOrigem(Lugar origem) {
		this.origem = origem;
	}

	public Lugar getDestino() {
		return destino;
	}

	public void setDestino(Lugar destino) {
		this.destino = destino;
	}

}