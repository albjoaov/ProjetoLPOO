package br.acme.users;

import java.io.Serializable;
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

public class Motorista extends Usuario implements Serializable {

	// ATRIBUTOS
	private boolean disponivel;

	// CONSTRUTOR
	public Motorista(long id, String cpf, String nome, String senha, String email, String sexo, boolean disponivel)
			throws CPFInvalidoException, CPFNullException, CPFVazioException, NomeVazioException, NomeNullException,
			SenhaVaziaException, SenhaNullException, EmailVazioException, EmailNullException, SexoVazioException,
			SexoNullException {
		super(id, cpf, nome, senha, email, sexo);
		this.disponivel = disponivel;
		super.validarCPF(cpf);
		super.validarNome(nome);
		super.validarSenha(senha);
		super.validarEmail(email);
		super.validarSexo(sexo);
	}

	// TO STRING
	@Override
	public String toString() {
		return "Motorista [" + super.toString() + ", disponivel=" + disponivel + "]";
	}

	// MÉTODOS
	public void historico() throws RepositorioException {
		boolean temViagem = false;
		System.out.println("Todas as viagens do '" + this.getNome() + "' sao:");

		for (int i = 0; i < Gerente.getRepoViagem().buscarRepositorio().length; i++) {

			if (Gerente.getRepoViagem().buscarRepositorio()[i] != null
					&& Gerente.getRepoViagem().buscarRepositorio()[i].getMotorista().getId() == this.getId()) {
				System.out.println(Gerente.getRepoViagem().buscarRepositorio()[i]);
				temViagem = true;
			}
		}if (temViagem == false)
			System.out.println("Nao existem viagens para '" + this.getNome() + "'");
	}

	// GETS E SETS
	public boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
}