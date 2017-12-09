package br.acme.users;

import java.io.IOException;
import java.io.Serializable;

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
import br.acme.location.Viagem;
import br.acme.storage.*;

public class Gerente extends Usuario implements Serializable {

	// OBJETOS
	private static IRepositorio<Motorista> repoMotorista = new RepositorioMotorista();
	private static IRepositorio<Solicitante> repoSolicitante = new RepositorioSolicitante();
	private static IRepositorio<Viagem> repoViagem = new RepositorioViagem();

	// CONSTRUTOR
	public Gerente(long id, String cpf, String nome, String senha, String email, String sexo)
			throws CPFInvalidoException, CPFNullException, CPFVazioException, NomeVazioException, NomeNullException,
			SenhaVaziaException, SenhaNullException, EmailVazioException, EmailNullException, SexoVazioException,
			SexoNullException {
		super(id, cpf, nome, senha, email, sexo);
		this.repoMotorista = new RepositorioMotorista();
		this.repoSolicitante = new RepositorioSolicitante();
		this.repoViagem = new RepositorioViagem();
		super.validarCPF(cpf);
		super.validarNome(nome);
		super.validarSenha(senha);
		super.validarEmail(email);
		super.validarSexo(sexo);
	}

	// VERIFICACOES LOGIN
	public static boolean verificarLoginSolicitante(String email, String senha) throws RepositorioException {
		boolean valor = false;

		for (int i = 0; i < Gerente.getRepoSolicitante().buscarRepositorio().length; i++) {
			if (Gerente.getRepoSolicitante().buscarRepositorio()[i].getEmail().equals(email)) {
				if (Gerente.getRepoSolicitante().buscarRepositorio()[i].getSenha().equals(senha)) {
					valor = true;
					return valor;
				}
			}
		}
		System.out.println("Login ou Senha incorretos");
		return valor;
	}

	public static boolean verificarLoginMotorista(String email, String senha) throws RepositorioException {
		boolean valor = false;

		for (int i = 0; i < Gerente.getRepoMotorista().buscarRepositorio().length; i++) {
			if (Gerente.getRepoMotorista().buscarRepositorio()[i].getEmail().equals(email)) {
				if (Gerente.getRepoMotorista().buscarRepositorio()[i].getSenha().equals(senha)) {
					valor = true;
					return valor;
				}
			}
		}
		System.out.println("Login ou Senha incorretos");
		return valor;
	}
	
	// VERIFICACOES DE REPETICAO DE EMAIL
	public static boolean verificarEmailSolicitante(String email) throws RepositorioException {
		for (int i = 0; i < Gerente.getRepoSolicitante().buscarRepositorio().length; i++) {
			if (Gerente.getRepoSolicitante().buscarRepositorio()[i] != null
					&& (Gerente.getRepoSolicitante().buscarRepositorio()[i].getEmail().equals(email))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean verificarEmailMotorista(String email) throws RepositorioException {
		for (int i = 0; i < Gerente.getRepoMotorista().buscarRepositorio().length; i++) {
			if (Gerente.getRepoMotorista().buscarRepositorio()[i] != null
					&& (Gerente.getRepoMotorista().buscarRepositorio()[i].getEmail().equals(email))) {
				return false;
			}
		}
		return true;
	}

	// TO STRING
	@Override
	public String toString() {
		return "Gerente [" + super.toString() + "]";
	}

	// MÉTODOS GERAIS MOTORISTA
	public static void cadastrarMotorista(Motorista motorista) throws RepositorioException {
		repoMotorista.adicionar(motorista);
	}

	public static void removerMotorista(long idMotorista) throws RepositorioException {
		repoMotorista.remover(idMotorista);
	}

	public static void listarMotoristas() throws RepositorioException {
		repoMotorista.buscarTodos();
	}

	public static void listarMotoristasDisponives() throws RepositorioException {
		for (int i = 0; i < repoMotorista.buscarRepositorio().length; i++) {
			if (repoMotorista.buscarRepositorio()[i] != null
					&& repoMotorista.buscarRepositorio()[i].getDisponivel() == true) {
				System.out.println(repoMotorista.buscarRepositorio()[i]);
			}
		}
	}

	// METODOS GERAIS SOLICITANTE
	public static void cadastrarSolicitante(Solicitante solicitante) throws RepositorioException {
		repoSolicitante.adicionar(solicitante);
	}

	public static void removerSolicitante(long idSolicitante) throws RepositorioException {
		repoSolicitante.remover(idSolicitante);
	}

	public static void listarSolicitantes() throws RepositorioException {
		repoSolicitante.buscarTodos();
	}

	// METODOS GERAIS VIAGEM
	public static void cadastrarViagem(Viagem viagem) throws RepositorioException {
		repoViagem.adicionar(viagem);
	}

	public static void removerViagem(long idViagem) throws RepositorioException {
		repoViagem.remover(idViagem);
	}

	public static void listarViagens() throws RepositorioException {
		repoViagem.buscarTodos();
	}
	

	// GETS E SETS REPOSITORIOS
	public static IRepositorio<Motorista> getRepoMotorista() {
		return repoMotorista;
	}

	public static void setRepoMotorista(IRepositorio<Motorista> repoMotorista) {
		Gerente.repoMotorista = repoMotorista;
	}

	public static IRepositorio<Solicitante> getRepoSolicitante() {
		return repoSolicitante;
	}

	public static void setRepoSolicitante(IRepositorio<Solicitante> repoSolicitante) {
		Gerente.repoSolicitante = repoSolicitante;
	}

	public static IRepositorio<Viagem> getRepoViagem() {
		return repoViagem;
	}

	public static void setRepoViagem(IRepositorio<Viagem> repoViagem) {
		Gerente.repoViagem = repoViagem;
	}
}
