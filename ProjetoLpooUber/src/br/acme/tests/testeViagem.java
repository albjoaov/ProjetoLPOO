package br.acme.tests;

import br.acme.users.*;
import br.acme.exception.*;
import br.acme.location.*;
import br.acme.storage.*;

public class testeViagem {
	public static void main(String[] args) throws RepositorioException, CPFInvalidoException, CPFNullException, CPFVazioException, NomeVazioException, NomeNullException, SenhaVaziaException, SenhaNullException, EmailVazioException, EmailNullException, SexoVazioException, SexoNullException {

		// INSTANCIAS
		DATABASE dados = new DATABASE();
		Lugar origem = new Lugar("Poli", "Rua benfica");
		Lugar destino = new Lugar("Casa", "Agamenom Magalhaes");

		Solicitante s1 = new Solicitante(1, "111.111.111-11", "Teste", "1234", "user@email.com", "F", null, 100);
		Solicitante s2 = new Solicitante(2, "444.444.444-44", "Teste2", "123456", "user2@email.com", "M", null, 400);
		Motorista m1 = new Motorista(1, "111.111.111-11", "M1", "123456", "m1@email.com", "M", true);
		Motorista m2 = new Motorista(2, "222.222.222-22", "M2", "123456", "m2@email.com", "M", true);

		Viagem v1 = new Viagem(1, s1, m1, origem, destino, 100, "Dinheiro");
		Viagem v2 = new Viagem(2, s2, m2, origem, destino, 200, "Cartao");
		Viagem v3 = new Viagem(3, s1, m1, origem, destino, 300, "Dinheiro");
		Viagem v4 = new Viagem(4, s1, m1, origem, destino, 300, "Dinheiro");

		
		s1.solicitarCarona(v2);
		//s1.solicitarCarona(v1, m2);
		//s1.cancelarCarona(v1);
		s1.historico();
		m2.historico();
		
		// ADICIONAR
		/*repoViagem.adicionar(v1);
		repoViagem.adicionar(v2); // ADICIONA 3 VIAGENS
		repoViagem.adicionar(v3);
		repoViagem.adicionar(v4);*/
			
		// REMOVER
		//repoViagem.remover(1); // REMOVE A v1
		
		// BUSCAR
		//System.out.println(repoViagem.buscar(1)); // BUSCA A v2
		
		// BUSCAR TODOS
		//repoViagem.buscarTodos(); // AO FINAL DO PROCESSO, TODAS SAO LISTADAS
		
		
		
		
	}
}
