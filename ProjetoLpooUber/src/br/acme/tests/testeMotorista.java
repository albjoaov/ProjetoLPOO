package br.acme.tests;

import br.acme.users.*;
import br.acme.exception.*;
import br.acme.location.*;
import br.acme.storage.*;

public class testeMotorista {
	public static void main(String[] args) throws RepositorioException, CPFInvalidoException, CPFNullException, CPFVazioException, NomeVazioException, NomeNullException, SenhaVaziaException, SenhaNullException, EmailVazioException, EmailNullException, SexoVazioException, SexoNullException {

		// INSTANCIAS
		IRepositorio<Motorista> repoMotorista = new RepositorioMotorista();
	
		
		Motorista m1 = new Motorista(1, "111.111.111-11", "M1", "123456", "m1@email.com", "M", true);
		Motorista m2 = new Motorista(2, "222.222.222-22", "M2", "1234567", "m2@email.com", "M", false);
		Motorista m3 = new Motorista(3, "333.333.333-33", "M3", "12345678", "m3@email.com", "H", true);
		Motorista m4 = new Motorista(4, "444.444.444-44", "M4", "12345678", "m4@email.com", "H", false);
		Motorista m5 = new Motorista(5, "444.444.444-44", "M4", "12345678", "m4@email.com", "H", false);
		
		Lugar origem = new Lugar("Poli", "Rua benfica");
		Lugar destino = new Lugar("Casa", "Agamenon Magalhaes");
		Solicitante s1 = new Solicitante(1, "111.111.111-11", "user@email.com", "1234", "Teste", "F", null, 100);
		Viagem v1 = new Viagem(1, s1, m1, origem, destino, 100, "Dinheiro");
		
		// ADICIONAR
		/*repoMotorista.adicionar(m1);		
		repoMotorista.adicionar(m2);			//Adiciona os 4 Motoristas
		repoMotorista.adicionar(m3);
		repoMotorista.adicionar(m4);
		repoMotorista.adicionar(m5);*/ // Não adiciona o 'm5' pois, é o mesmo objeto que o 'm4'
		Gerente.cadastrarMotorista(m1);
		
		// REMOVER
		//repoMotorista.remover(2);				//Remove m2(id = 2)

		// ALTERAR
		//repoMotorista.alterar(3, m4);			//Altera m3(id = 3) por m4

		// BUSCAR
		//System.out.println(repoMotorista.buscar(4));	//Busca m4 (id = 4)

		// BUSCAR TODOS
		//repoMotorista.buscarTodos();				// Lista todos os presentes, ao final do processo
	} 
}
