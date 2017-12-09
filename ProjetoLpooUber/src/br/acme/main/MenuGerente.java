package br.acme.main;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
//import java.util.Random;
import java.util.Scanner;
import br.acme.exception.*;
import br.acme.storage.*;
import br.acme.users.*;

public class MenuGerente {

	private static String emailAuxS;
	private static String emailAuxM;

	public static void iniciarAplicativoGerente()
			throws ClassNotFoundException, IOException, RepositorioException, CPFInvalidoException, CPFNullException,
			CPFVazioException, NomeVazioException, NomeNullException, SenhaVaziaException, SenhaNullException,
			EmailVazioException, EmailNullException, SexoVazioException, SexoNullException {

		// Carregamento dos dados do sistema.
		// Gerente único = static.
		// Try catch para tratar o erro de estar sem o arquivo .txt
		try {
			if (DATABASE.LerBaseSolicitante() != null) {
				Gerente.setRepoSolicitante(DATABASE.LerBaseSolicitante());
			}
		} catch (FileNotFoundException e) {
			DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
		} catch (EOFException e) {
			DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
		}

		try {
			if (DATABASE.LerBaseMotorista() != null) {
				Gerente.setRepoMotorista(DATABASE.LerBaseMotorista());
			}
		} catch (FileNotFoundException e) {
			DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());
		} catch (EOFException e) {
			DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());
		}

		try {
			if (DATABASE.LerBaseViagem() != null) {
				Gerente.setRepoViagem(DATABASE.LerBaseViagem());
			}
		} catch (FileNotFoundException e) {
			DATABASE.SalvarEstadoViagem(Gerente.getRepoViagem());
		} catch (EOFException e) {
			DATABASE.SalvarEstadoViagem(Gerente.getRepoViagem());
		}
		
		//Variaveis para o menu
		int escolha;
		boolean b = true;
		Scanner entrada = new Scanner(System.in);
		
		//Boolean para o controle do while
		while (b) {
			System.out.println("Seja bem-vindo ao menu Gerente.");
			System.out.println("1) Adicionar Solicitante.");
			System.out.println("2) Adicionar Motorista.");
			System.out.println("3) Remover Solicitante.");
			System.out.println("4) Remover Motorista.");
			System.out.println("5) Listar Solicitantes.");
			System.out.println("6) Listar Motoristas.");
			System.out.println("7) Gerar relatórios de Viagem.");
			System.out.println("8) Sair.");

			escolha = entrada.nextInt();
			entrada.nextLine();
			switch (escolha) {

			case 1:
				long idS;
				int numeroCelularS;
				String cpfS, nomeS, senhaS, emailS, sexoS;
				Date data = new Date(1990, 01, 01);

				System.out.println("Precisamos de alguns dados.");

				System.out.println("Digite seu id:");
				idS = entrada.nextLong();
				entrada.nextLine();

				System.out.println("Digite seu cpf: ( Deve ser no formato: xxx.xxx.xxx-xx )");
				cpfS = entrada.next();
				entrada.nextLine();

				System.out.println("Digite seu nome:");
				nomeS = entrada.nextLine();

				System.out.println("Digite uma senha:");
				senhaS = entrada.nextLine();

				// Verificacao email duplicado
				try {
					boolean bEmail1 = true;
					while (bEmail1) {
						System.out.println("Digite seu email:");
						emailS = entrada.next();
						if (Gerente.verificarEmailSolicitante(emailS) && Gerente.verificarEmailMotorista(emailS)) {
							emailAuxS = emailS;
							bEmail1 = false;
						} else
							System.out.println("Email já existente. Tente outro");
					}
				} catch (RepositorioException e) {
					System.out.println("Ocorreu um erro. Digite seu email novamentes:");
					emailAuxS = entrada.next();
				}

				// Fim verificacao

				System.out.println("Diga o seu sexo.");
				sexoS = entrada.next();

				System.out.println("Digite o numero do seu celular:");
				numeroCelularS = entrada.nextInt();
				entrada.nextLine();

				Solicitante s1 = new Solicitante(idS, cpfS, nomeS, senhaS, emailAuxS, sexoS, data, numeroCelularS);
				Gerente.cadastrarSolicitante(s1);
				DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
				break;

			case 2:
				long idM;
				boolean disponivelM = true;
				String cpfM, nomeM, senhaM, emailM, sexoM;

				System.out.println("Precisamos de alguns dados.");

				System.out.println("Digite seu id:");
				idM = entrada.nextLong();
				entrada.nextLine();

				System.out.println("Digite seu cpf: ( Deve ser no formato: xxx.xxx.xxx-xx )");
				cpfM = entrada.next();
				entrada.nextLine();

				System.out.println("Digite seu nome:");
				nomeM = entrada.nextLine();

				System.out.println("Digite uma senha:");
				senhaM = entrada.nextLine();

				// Verificacao email duplicado
				try {
					boolean bEmail2 = true;
					while (bEmail2) {
						System.out.println("Digite seu email:");
						emailM = entrada.next();
						if (Gerente.verificarEmailSolicitante(emailM) && Gerente.verificarEmailMotorista(emailM)) {
							emailAuxM = emailM;
							bEmail2 = false;
						} else
							System.out.println("Email já existente. Tente outro");
					}
				} catch (RepositorioException e) {
					System.out.println("Ocorreu um erro. Digite seu email novamente:");
					emailAuxM = entrada.next();
				}
				// Fim verificacao

				System.out.println("Diga o seu sexo.");
				sexoM = entrada.next();

				Motorista m1 = new Motorista(idM, cpfM, nomeM, senhaM, emailAuxM, sexoM, disponivelM);
				// Todos Motorista é inicializado com o disponivel = true.
				Gerente.cadastrarMotorista(m1);
				DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());
				break;

			case 3:

				System.out.println("A lista de todos solicitantes é:");
				try {
					DATABASE.LerBaseSolicitante();
					Gerente.listarSolicitantes();

				} catch (RepositorioException e) {
					System.out.println("Não há solicitantes cadastrados.");
					break;
				}

				System.out.println("Digite o ID que deseja remover:");
				int idRemoverS;
				idRemoverS = entrada.nextInt();
				Gerente.removerSolicitante(idRemoverS);
				DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
				break;

			case 4:

				System.out.println("A lista de todos os motoristas é:");
				try {
					DATABASE.LerBaseMotorista();
					Gerente.listarMotoristas();

				} catch (RepositorioException e) {
					System.out.println("Não há motoristas cadastrados.");
					break;
				}

				System.out.println("Digite o ID que deseja remover:");
				int idRemoverM;
				idRemoverM = entrada.nextInt();
				Gerente.removerMotorista(idRemoverM);
				DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());
				break;

			case 5:

				System.out.println("A lista de todos solicitantes é:");
				try {
					DATABASE.LerBaseSolicitante();
					Gerente.listarSolicitantes();

				} catch (RepositorioException e) {
					System.out.println("Não há solicitantes cadastrados.");
				}
				break;

			case 6:
				System.out.println("A lista de todos os motoristas é:");
				try {
					DATABASE.LerBaseMotorista();
					Gerente.listarMotoristas();

				} catch (RepositorioException e) {
					System.out.println("Não há motoristas cadastrados.");
				}
				break;

			case 7:
				System.out.println("Relatório de todas as viagens: ");

				try {
					DATABASE.LerBaseViagem();
					Gerente.getRepoViagem().buscarTodos();

				} catch (RepositorioException e) {
					System.out.println("Não existem viagens realizadas.");
				}
				break;

			case 8:
				System.out.println("Você saiu.");
				b = false;
				break;

			case 9:
				// Remover Viagem
				System.out.println("Relatório de todas as viagens: ");

				try {
					DATABASE.LerBaseViagem();
					Gerente.getRepoViagem().buscarTodos();

				} catch (RepositorioException e) {
					System.out.println("Não existem viagens realizadas.");
					break;
				}

				System.out.println("Digite o ID que deseja remover:");
				int idRemoverV;
				idRemoverV = entrada.nextInt();
				Gerente.removerViagem(idRemoverV);
				DATABASE.SalvarEstadoViagem(Gerente.getRepoViagem());
				break;

			default:
				System.out.println("Digite um numero valido" + "\n");
				break;

			}
		}
	}
}