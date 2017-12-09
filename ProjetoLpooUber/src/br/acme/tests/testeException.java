package br.acme.tests;

import java.io.IOException;
import br.acme.exception.*;
import br.acme.location.*;
import br.acme.storage.*;
import br.acme.users.*;


public class testeException {
	public static void main(String[] args) throws RepositorioException, CPFInvalidoException, CPFNullException, CPFVazioException, NomeVazioException, NomeNullException, SenhaVaziaException, SenhaNullException, EmailVazioException, EmailNullException, SexoVazioException, SexoNullException, IOException, ClassNotFoundException {
		
		//OBS: Os lançamentos das excecoes (nos metodos de validacao) se encontram na classe Usuario - para as excecoes referentes a CPF, nome, sexo, etc
		//OBS2: Os lancamentos das excecoes dos metodos adicionar, remover, alterar, buscar e buscarTodos se encontram nas classes de Repositorio (no pacote .storage)
		
		//************** Observe os comentarios para observar os testes das exceptions ***********************************************
		
		
		//TESTE EXCEPTION DE ATRIBUTOS - CPF, NOME, SENHA, EMAIL E SEXO
		
			Gerente g1 = new Gerente(1,"111.111.111-11", "Gerente", "12345", "gerente@email.com", "F");
		    //Gerente g2 = new Gerente(1,"", "Gerente", "12345", "gerente@email.com", "F");	
			//Gerente g3 = new Gerente(1,"112.3443.233-33", "Gerente", "12345", "gerente@email.com", "F");
	        
			// - O gerente g2 acima nao irá ser instanciado (CPFVazioException), pois o atributo cpf esta vazio (descomente para testar)
			// - O gerente g3 acima nao irá ser instanciado (CPFInvalidoException), pois o atributo cpf esta invalido (descomente para testar)

		 
			Solicitante s1 = new Solicitante(1, "111.111.111-11", "Teste", "1234", "user@email.com", "F", null, 100);
			//Solicitante s2 = new Solicitante(2, "444.444.444-44", null, "123456", "user2@email.com", "M", null, 400);
			// - O solicitante s2 acima nao irá ser instanciado (NomeNullException), pois o atributo nome está como null (descomente para testar)
			
			// Apenas 2 exemplos das exceptions do Usuario, mas se vc mudar qualquer um dos atributos que tem exception para VAZIO("") ou null, ira ocorrer uma exception
			
			
		// TESTE EXCEPTION DAS EXCECOES DOS METODOS DO REPOSITORIO
			
			IRepositorio<Solicitante> repoSolicitante = new RepositorioSolicitante();
			
			repoSolicitante.adicionar(s1);
			//Solicitante s3 = new Solicitante(1, "111.111.111-11", "Teste", "1234", "user@email.com", "F", null, 100);
			//repoSolicitante.adicionar(s3);
			// - ira ocorrer um (RepositorioException: Solicitante com id ja existente!) descomente o adicionar acima && o Solicitante s3 acima (descomente para testar)
			
			//repoSolicitante.remover(98);
			//- ira ocorrer um (RepositorioException: Solicitante com id inexistente!) descomente o remover acima
			
			//repoSolicitante.buscar(299);
			//- ira ocorrer um (RepositorioException: Solicitante que se deseja buscar inexistente!) descomente o buscar acima
			
			//Apenas 3 exemplos das exceptions dos Repositorios, mas se vc buscar, remover ou alterar objetos inexistentes ou se estiverem com repositoriosVazios ira ocorrer uma exception
			
			
		    
	}
}