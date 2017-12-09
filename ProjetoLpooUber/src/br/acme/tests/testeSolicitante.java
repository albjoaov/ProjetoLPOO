package br.acme.tests;

import br.acme.users.*;
import br.acme.exception.RepositorioException;
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.storage.*;
import java.util.Date;

public class testeSolicitante {
	
	public static void main(String[] args) throws RepositorioException, Exception { 
		
		// INSTANCIAS DE OBJETOS
		
		IRepositorio<Solicitante> repoSolicitante = new RepositorioSolicitante();
		Date data = new Date(1990, 05, 01);
		
		Solicitante s1 = new Solicitante(1, "111.111.111-11", "Teste", "1234", "user@email.com", "F", data, 100);
		Solicitante s2 = new Solicitante(2, "444.444.444-44", "Teste2", "123456", "user2@email.com", "M", data, 400);
		Solicitante s3 = new Solicitante(7, "444.444.444-44", "Teste2", "123456", "user2@email.com", "M", data, 400);
		Solicitante Victor = new Solicitante(3, "222.222.222-22", "Victor Saad", "12345", "victorsaad@gmail.com", "M", data, 200);
		Solicitante Joao = new Solicitante(4, "333.333.333-33", "João Victor", "123456", "jvoa@ecomp.poli.br", "M", data, 300);
		
		Lugar origem = new Lugar("Poli", "Rua benfica");
		Lugar destino = new Lugar("Casa", "Agamenon Magalhaes");
		Motorista m1 = new Motorista(1, "111.111.111-11", "M1", "123456", "m1@email.com", "M", true);
		Viagem v1 = new Viagem(10, s1, m1, origem, destino, 100, "Dinheiro");
		
		
		// ADICIONAR
		repoSolicitante.adicionar(s1);				//Adiciona os 4 solicitantes (s1, s2, Victor e Joao)
		repoSolicitante.adicionar(s2);
		repoSolicitante.adicionar(s3);
		repoSolicitante.adicionar(Victor);
		repoSolicitante.adicionar(Joao);

			
		// REMOVER
		repoSolicitante.remover(9);			// Remove s2 (que tem id = 2)

		// ALTERAR
		//repoSolicitante.alterar(8, Victor);		//Altera s1(id = 1) por Victor

		// BUSCAR
		//System.out.println(repoSolicitante.buscar(3));			//Busca s1 (id = 1)

		// BUSCAR TODOS
		repoSolicitante.buscarTodos();					//Lista todos os Solicitantes ao final do processo
		
	}
}
