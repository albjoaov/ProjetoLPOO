package br.acme.users;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

import br.acme.exception.CPFInvalidoException;
import br.acme.exception.CPFNullException;
import br.acme.exception.CPFVazioException;
import br.acme.exception.EmailNullException;
import br.acme.exception.EmailVazioException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.RepositorioException;
import br.acme.exception.SenhaNullException;
import br.acme.exception.SenhaVaziaException;
import br.acme.exception.SexoNullException;
import br.acme.exception.SexoVazioException;
import br.acme.location.*;
import br.acme.storage.*;

public class Solicitante extends Usuario implements Serializable {

	// ATRIBUTOS
	private Date dataNascimento;
	private int numeroCelular;

	// CONSTRUTOR
	public Solicitante(long id, String cpf, String nome, String senha, String email, String sexo, Date dataNascimento,
			int numeroCelular) throws CPFInvalidoException, CPFNullException, CPFVazioException, NomeVazioException,
			NomeNullException, SenhaVaziaException, SenhaNullException, EmailVazioException, EmailNullException,
			SexoVazioException, SexoNullException {

		super(id, cpf, nome, senha, email, sexo);
		this.dataNascimento = dataNascimento;
		this.numeroCelular = numeroCelular;
		super.validarCPF(cpf);
		super.validarNome(nome);
		super.validarSenha(senha);
		super.validarEmail(email);
		super.validarSexo(sexo);
	}

	// METODOS
	public void solicitarCarona(Viagem viagem) throws RepositorioException {
		Gerente.cadastrarViagem(viagem);
	}

	public void historico() throws RepositorioException {
		boolean temViagem = false;
		System.out.println("Todas as viagens de '" + this.getNome() + "' sao:");

		for (int i = 0; i < Gerente.getRepoViagem().buscarRepositorio().length; i++) {
			if ((Gerente.getRepoViagem().buscarRepositorio()[i]) != null
					&& (Gerente.getRepoViagem().buscarRepositorio()[i].getSolicitante().getId() == this.getId())) {
				System.out.println(Gerente.getRepoViagem().buscarRepositorio()[i]);
				temViagem = true;
			}
		}
		if (temViagem == false)
			System.out.println("Nao existem viagens para '" + this.getNome() + "'");
	}

	// TO STRING
	@Override
	public String toString() {
		return "Solicitante [" + super.toString() + ", dataNascimento=" + dataNascimento + ", numeroCelular="
				+ numeroCelular + "]";
	}

	// GETS E SETS
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(int numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

}
