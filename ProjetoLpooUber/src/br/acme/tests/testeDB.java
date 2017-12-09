package br.acme.tests;

import java.io.IOException;
import br.acme.exception.*;
import br.acme.location.*;
import br.acme.storage.*;
import br.acme.users.*;

public class testeDB {

	public static void main(String[] args)
			throws RepositorioException, CPFInvalidoException, CPFNullException, CPFVazioException, NomeVazioException,
			NomeNullException, SenhaVaziaException, SenhaNullException, EmailVazioException, EmailNullException,
			SexoVazioException, SexoNullException, IOException, ClassNotFoundException {

		IRepositorio<Viagem> repoViagem = new RepositorioViagem();
		IRepositorio<Solicitante> repoSolicitante = new RepositorioSolicitante();
			
		//Instanciando Solicitantes
			Solicitante s1 = new Solicitante(1, "111.111.111-11", "Teste", "1234", "user@email.com", "F", null, 100);
		   	//Solicitante s2 = new Solicitante(2, "444.444.444-44", "Teste2", "123456", "user2@email.com", "M", null, 400);
			
		//Instanciando Motoristas
			Motorista m1 = new Motorista(1, "111.111.111-11", "M1", "123456", "m1@email.com", "M", true);
			//Motorista m2 = new Motorista(2, "222.222.222-22", "M2", "123456", "m2@email.com", "M", true);
			
		//Instanciando Lugares
			Lugar origem = new Lugar("Poli", "Rua benfica");
		    Lugar destino = new Lugar("Casa", "Agamenom Magalhaes");
			
		//Instanciando Viagens
			Viagem v1 = new Viagem(1, s1, m1, origem, destino, 100, "Dinheiro");
		
		Gerente.cadastrarMotorista(m1);
		Gerente.cadastrarSolicitante(s1);
		Gerente.cadastrarViagem(v1);
		
		DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());		// Repositorio de Motorista arquivado (criou o .txt)
		DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());	// Repositorio de Solicitante arquivado (criou o .txt)
		DATABASE.SalvarEstadoViagem(Gerente.getRepoViagem());			// Repositorio de Viagem arquivado (criou o .txt)
		
		
		Gerente.setRepoMotorista(DATABASE.LerBaseMotorista());	
		Gerente.setRepoSolicitante(DATABASE.LerBaseSolicitante());		// Recebe os dados do .txt criado acima												
		Gerente.setRepoViagem(DATABASE.LerBaseViagem());
		
		Gerente.listarMotoristas();
		Gerente.listarSolicitantes();			// Retorna os dados lidos acima
		Gerente.listarViagens();
	}
}
