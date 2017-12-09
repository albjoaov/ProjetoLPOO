package br.acme.storage;

import java.io.Serializable;
import br.acme.exception.RepositorioException;
import br.acme.users.*;

public class RepositorioMotorista implements IRepositorio<Motorista>, Serializable {

	private Motorista[] motoristas;
	private int totalMotorista = 0;

	public RepositorioMotorista() {
		this.motoristas = new Motorista[200];
	}

	//	VERIFICA EXISTENCIA DO ID:
	// Se ja existir um Motorista com o mesmo id,
	// retorna 'false'. Que sera utilizado no
	// metodo adicionar. 
	
	public boolean verificarExistencia(Motorista Motorista) {
		for (int i = 0; i < motoristas.length; i++) {
			if (motoristas[i] != null && (motoristas[i].getId() == Motorista.getId())) {
				return false;   
			}
		}
		return true;
	}

	// MÉTODO ADICIONAR
	public void adicionar(Motorista Motorista) throws RepositorioException{
		if (Motorista != null) {
			if(verificarExistencia(Motorista)){							//Se o id do Motorista já existir, o booleano retornado será um false, dessa forma nao entra no condiconal.
				for (int i = 0; i < motoristas.length; i++) {			//e será imprimido na tela que o Id do Motorista já existe.
					if (motoristas[i] == null) { 						// No primeiro espaco vazio do vetor, 
						motoristas[i] = Motorista;						//  ele adiciona o parametro 'Motorista'
						System.out.println("Motorista '" + Motorista.getNome() + "' adicionado com sucesso.");									
						totalMotorista ++;
						break;
						
					}
				}
			} else
				throw new RepositorioException("Motorista com id já existente!");
		}
	}

	// MÉTODO REMOVER
	public void remover(long id) throws RepositorioException{	
		
		if(totalMotorista == 0){
			throw new RepositorioException("Repositorio Vazio!");
		}
		
		boolean b = false;
		int contador = 0;
		for (int i = 0; motoristas[i] != null; i++) {
			if (motoristas[i].getId() == id) {			//Remove o elemento do vetor que contem o atributo id
				motoristas[i] = null;
				System.out.println("Motorista removido com sucesso.");
				totalMotorista --;
				b = true;
				contador = i; 		// Contador para que a reorganizacao do vetor, que
				break;				// esta no for abaixo, comece a partir do					
			}						// elemento que foi removido
		}
		if(b == true){
			for (int i = contador; i < motoristas.length; i++) {	//Reorganizacao do vetor
				if (i + 1 < motoristas.length) {
					motoristas[i] = motoristas[i + 1];
					motoristas[i + 1] = null;
				}
			}
		}else{
			throw new RepositorioException("Motorista com id inexistente!");
		}
	}
			

	// MÉTODO ALTERAR
	public void alterar(long id, Motorista novoSolicitante) throws RepositorioException {

		boolean b = false;
		
		if(totalMotorista == 0){
			throw new RepositorioException("Repositorio Vazio!");
		}else{
			if (novoSolicitante != null) {
			for (int i = 0; i < motoristas.length ; i++) {
				if (motoristas[i] != null && motoristas[i].getId() == id) { 		// Verificacao da id desejada
					motoristas[i] = novoSolicitante;								// no parametro.
					System.out.println("Motorista alterado com sucesso.");			// Substitui o objeto da id
					b = true;														// desejada pelo novo Motorista do parametro
					break;																
				}
			}}
			if(b == false){
				throw new RepositorioException("Motorista que deseja alterar contém Id inexistente!");
			}
		}
	}

	// MÉTODO BUSCAR
	public Motorista buscar(long id) throws RepositorioException{
		boolean b = false;
		
		if(totalMotorista == 0){
			throw new RepositorioException("Repositorio Vazio!");
		}
		
		for (int i = 0; i < motoristas.length; i++) {
			if (motoristas[i] != null && motoristas[i].getId() == id) {
				return motoristas[i];
			}
		}if(b == false)
				throw new RepositorioException("Motorista que se deseja buscar inexistente!");
		
		return null;
	}

	// MÉTODO BUSCAR TODOS
	public Motorista[] buscarTodos() throws RepositorioException{
		
		if(totalMotorista == 0){
			throw new RepositorioException("Repositorio Vazio!");
		}
		
		for (int i = 0; i < motoristas.length; i++) {
			if (motoristas[i] != null)
				System.out.println(motoristas[i]);
		}
		return motoristas;
	}


	public Motorista[] buscarRepositorio() throws RepositorioException {
		
		if(totalMotorista == 0){
			throw new RepositorioException("Repositorio Vazio!");
		}
		return motoristas;
	}
	
	
}
