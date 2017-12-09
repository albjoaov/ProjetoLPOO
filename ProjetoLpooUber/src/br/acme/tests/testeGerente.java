package br.acme.tests;

import br.acme.users.*;
import br.acme.exception.*;
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioMotorista;

public class testeGerente {
	public static void main(String[] args) throws RepositorioException, CPFInvalidoException, CPFNullException, CPFVazioException, NomeVazioException, NomeNullException, SenhaVaziaException, SenhaNullException, EmailVazioException, EmailNullException, SexoVazioException, SexoNullException{
		
		//INSTANCIAS DE OBJETOS		
		IRepositorio<Motorista> repoMotorista = new RepositorioMotorista();
		
		Gerente g1 = new Gerente(1,"190.345.564-55","Manager","12345","g1@email.com","M");
		Motorista m1 = new Motorista(1, "111.111.111-11", "M1", "123456", "m1@email.com", "M", true);
		Motorista m2 = new Motorista(2, "222.222.222-22", "M2", "1234567", "m2@email.com", "M", true);
		Solicitante s1 = new Solicitante(1, "111.111.111-11", "user@email.com", "1234", "Teste", "F", null, 100);

		
		//TESTE CADASTRO
		g1.cadastrarMotorista(m1);
		g1.cadastrarMotorista(m2);
		g1.cadastrarSolicitante(s1);
		
		//TESTE REMOCAO
		g1.removerMotorista(2);
		
		//TESTE LISTAR MOTORISTAS
		g1.listarMotoristas();
		
		//TESTE LISTAR CLIENTES
		g1.listarSolicitantes(); 
		
	
	
	}
}
