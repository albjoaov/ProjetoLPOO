package br.acme.storage;

import java.io.Serializable;

import br.acme.exception.RepositorioException;
import br.acme.location.Viagem;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class RepositorioViagem implements IRepositorio<Viagem>, Serializable {

	private Viagem[] viagens;
	private int totalViagem = 0;

	public RepositorioViagem() {
		this.viagens = new Viagem[200];
	}

	//	VERIFICA EXISTENCIA DO ID:
	// Se ja existir um Viagem com o mesmo id,
	// retorna 'false'. Que sera utilizado no
	// metodo adicionar. 
	
	public boolean verificarExistencia(Viagem Viagem) {
		for (int i = 0; i < viagens.length; i++) {
			if (viagens[i] != null && (viagens[i].getId() == Viagem.getId())) {
				return false;   
			}
		}
		return true;
	}

	// MÉTODO ADICIONAR
	public void adicionar(Viagem Viagem) throws RepositorioException {
		if (Viagem != null) {
			if(verificarExistencia(Viagem)){						//Se o id do Viagem já existir, o booleano retornado será um false, dessa forma nao entra no condiconal.
				for (int i = 0; i < viagens.length; i++) {			//e será imprimido na tela que o Id do Viagem já existe.
					if (viagens[i] == null) { 						// No primeiro espaco vazio do vetor, 
						viagens[i] = Viagem;					//  ele adiciona o parametro 'Viagem'
						System.out.println("Viagem adicionada com sucesso.");									
						totalViagem ++;
						break;
					}
				}
			} else
				throw new RepositorioException("Viagem com id já existente!");
		}
	}

	// MÉTODO REMOVER
	public void remover(long id) throws RepositorioException{	 // boolean para que os retornos tenham
																// como indicar numa futura interface se
																// alguem foi removido, facilmente.
		if(totalViagem == 0){
			throw new RepositorioException("Repositorio Vazio!");
		}
		boolean b = false;
		int contador = 0;
		for (int i = 0; viagens[i] != null; i++) {
			if (viagens[i].getId() == id) {			//Remove o elemento do vetor que contem o atributo id
				viagens[i] = null;
				System.out.println("Viagem removida com sucesso.");
				totalViagem --;
				b = true;
				contador = i; 		// Contador para que a reorganizacao do vetor, que
				break;				// esta no for abaixo, comece a partir do
									// elemento que foi removido

			}
		}
		for (int i = contador; i < viagens.length; i++) {
			if (i + 1 < viagens.length) {
				viagens[i] = viagens[i + 1];
				viagens[i + 1] = null;
			}
		
		if (b == false) {
			throw new RepositorioException("Viagem com id inexistente!");
			}
		}
	}

	// MÉTODO BUSCAR
		public Viagem buscar(long id) throws RepositorioException{
			boolean b = false;
			
			if(totalViagem == 0){
				throw new RepositorioException("Repositorio Vazio!");
			}
			
			for (int i = 0; i < viagens.length; i++) {
				if (viagens[i] != null && viagens[i].getId() == id) {
					return viagens[i];
				}
			}if(b == false)
					throw new RepositorioException("Viagem que se deseja buscar inexistente!");
			
			return null;
		}

	// MÉTODO BUSCAR TODOS
		public Viagem[] buscarTodos() throws RepositorioException{
			
			if(totalViagem == 0){
				throw new RepositorioException("Repositorio Vazio!");
			}
			
			for (int i = 0; i < viagens.length; i++) {
				if (viagens[i] != null)
					System.out.println(viagens[i]);
			}
			return viagens;
		}
		
		public Viagem[] buscarRepositorio() throws RepositorioException {
			if(totalViagem == 0){
				throw new RepositorioException("Repositorio Vazio!");
			}
			return viagens;
		}
		
		public void alterar(long id, Viagem t) throws RepositorioException {
			//Esse metodo foi criado por causa da interface IRepositorio. Danilo disse que estava ok em deixa-lo aqui. 
		}
		
	}
	
