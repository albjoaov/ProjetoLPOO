package br.acme.storage;

import java.io.Serializable;

import br.acme.exception.RepositorioException;
import br.acme.users.*;

public class RepositorioSolicitante implements IRepositorio<Solicitante>, Serializable {

	private Solicitante[] solicitantes;
	private int totalSolicitante = 0;

	public RepositorioSolicitante() {
		this.solicitantes = new Solicitante[200];
	}

	//	VERIFICA EXISTENCIA DO ID:
	// Se ja existir um solicitante com o mesmo id,
	// retorna 'false'. Que sera utilizado no
	// metodo adicionar. 
	
	public boolean verificarExistencia(Solicitante solicitante) {
		for (int i = 0; i < solicitantes.length; i++) {
			if (solicitantes[i] != null && (solicitantes[i].getId() == solicitante.getId())) {
				return false;   
			}
		}
		return true;
	}

	// MÉTODO ADICIONAR
	public void adicionar(Solicitante solicitante) throws RepositorioException{
		if (solicitante != null) {
			if(verificarExistencia(solicitante)){						//Se o id do solicitante já existir, o booleano retornado será um false, dessa forma nao entra no condiconal.
				for (int i = 0; i < solicitantes.length; i++) {			//e será imprimido na tela que o Id do solicitante já existe.
					if (solicitantes[i] == null) { 						// No primeiro espaco vazio do vetor, 
						solicitantes[i] = solicitante;					//  ele adiciona o parametro 'Solicitante'
						System.out.println("Solicitante '" + solicitante.getNome() + "' adicionado com sucesso.");									
						totalSolicitante ++;
						break;
					}
				}
			} else
				throw new RepositorioException("Solicitante com id já existente!");
		}
	}

	// MÉTODO REMOVER
	public void remover(long id) throws RepositorioException{	
		
		if(totalSolicitante == 0){
			throw new RepositorioException("Repositorio Vazio!");
		}
		
		boolean b = false;
		int contador = 0;
		for (int i = 0; solicitantes[i] != null; i++) {
			if (solicitantes[i].getId() == id) {			
				solicitantes[i] = null;					//Remove o elemento do vetor que contem o atributo id
				System.out.println("Solicitante removido com sucesso.");
				totalSolicitante --;
				b = true;
				contador = i; 		// Contador para que a reorganizacao do vetor, que
				break;				// esta no for abaixo, comece a partir do					
			}						// elemento que foi removido
		}
		if(b == true){
			for (int i = contador; i < solicitantes.length; i++) {	//Reorganizacao do vetor
				if (i + 1 < solicitantes.length) {
					solicitantes[i] = solicitantes[i + 1];
					solicitantes[i + 1] = null;
				}
			}
		}else{
			throw new RepositorioException("Solicitante com id inexistente!");
		}
	}
			

	// MÉTODO ALTERAR
	public void alterar(long id, Solicitante novoSolicitante) throws RepositorioException {

		boolean b = false;
		
		if(totalSolicitante == 0){
			throw new RepositorioException("Repositorio Vazio!");
		}else{
			if (novoSolicitante != null) {
			for (int i = 0; i < solicitantes.length ; i++) {
				if (solicitantes[i] != null && solicitantes[i].getId() == id) { 		// Verificacao da id desejada
					solicitantes[i] = novoSolicitante;									// no parametro.
					System.out.println("Solicitante alterado com sucesso.");			// Substitui o objeto da id
					b = true;															// desejada pelo novo solicitante do parametro
					break;															   		
				}
			}}
			if(b == false){
				throw new RepositorioException("Solicitante que deseja alterar contém Id inexistente!");
			}
		}
	}

	// MÉTODO BUSCAR
	public Solicitante buscar(long id) throws RepositorioException{
		boolean b = false;
		
		if(totalSolicitante == 0){
			throw new RepositorioException("Repositorio Vazio!");
		}
		
		for (int i = 0; i < solicitantes.length; i++) {
			if (solicitantes[i] != null && solicitantes[i].getId() == id) {
				return solicitantes[i];
			}
		}
		if(b == false)
				throw new RepositorioException("Solicitante que se deseja buscar inexistente!");
		
		return null;
	}
	
	// MÉTODO BUSCAR TODOS
	public Solicitante[] buscarTodos() throws RepositorioException{
		
		if(totalSolicitante == 0){
			throw new RepositorioException("Repositorio Vazio!");
		}
		
		for (int i = 0; i < solicitantes.length; i++) {
			if (solicitantes[i] != null)
				System.out.println(solicitantes[i]);
		}
		return solicitantes;
	}
	
	public Solicitante[] buscarRepositorio() throws RepositorioException{
		
		if(totalSolicitante == 0){
			throw new RepositorioException("Repositorio Vazio!");
		}
		return solicitantes;
	}

}