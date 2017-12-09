package br.acme.users;

import java.io.Serializable;
import br.acme.exception.*;

public abstract class Usuario implements Serializable {

	//ATRIBUTOS
	private long id;
	private String cpf;
	private String nome;
	private String senha;
	private String email;
	private String sexo;
	
	//CONSTRUTOR
	public Usuario(long id, String cpf, String nome, String senha, String email, String sexo) {
		this.id = id;		
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.sexo = sexo;

	}
	
	//METODOS VALIDACAO
	public void validarNome(String nome) throws NomeVazioException, NomeNullException {
		if (nome == null) {
			throw new NomeNullException("Nome do Usuario recebeu null.");
			
		}else if ("".equals(nome.trim())) {
			throw new NomeVazioException("Nome do Usuario vazio.");
			
		}else
			setNome(nome);
	}
	
	public void validarSenha(String senha) throws SenhaVaziaException, SenhaNullException {
		if (senha == null) {
			throw new SenhaNullException("Senha do Usuario recebeu null.");
			
		}else if ("".equals(senha.trim())) {
			throw new SenhaVaziaException("Senha do Usuario vazio.");
			
		}else
			setSenha(senha);
	}
	
	public void validarEmail(String email) throws EmailVazioException, EmailNullException {
		if (email == null) {
			throw new EmailNullException("Email do Usuario recebeu null.");
			
		}else if ("".equals(email.trim())) {
			throw new EmailVazioException("Email do Usuario vazio.");
			
		}else
			setEmail(email);
	}
	
	public void validarSexo(String sexo) throws SexoVazioException, SexoNullException {
		if (sexo == null) {
			throw new SexoNullException("Sexo do Usuario recebeu null.");
			
		}else if ("".equals(sexo.trim())) {
			throw new SexoVazioException("Sexo do Usuario vazio.");
			
		}else
			setSexo(sexo);
	}
	
	public void validarCPF(String cpf) throws CPFInvalidoException, CPFNullException, CPFVazioException {
		
		if (cpf == null) {
			throw new CPFNullException("CPF do Usuario recebeu null.");
			
		}else if ("".equals(cpf.trim())) {
			throw new CPFVazioException("CPF do Usuario vazio.");
			
		}else if((this.getCpf().matches("\\d\\d\\d\\.\\d\\d\\d\\.\\d\\d\\d-\\d\\d")) == false){
			throw new CPFInvalidoException("CPF invalido!");
			
		}else
			setCpf(cpf);
	}
	
	//****************************** FIM METODOS DE VALIDACAO
			

	//TO STRING
	@Override
	public String toString() {
		return "id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", senha=" + senha + ", email=" + email
				+ ", sexo=" + sexo + "";
	}

	//GETS E SETS
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
