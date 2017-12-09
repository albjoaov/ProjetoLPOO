package br.acme.main;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import br.acme.storage.*;
import br.acme.users.*;
import br.acme.location.*;
import br.acme.exception.*;

public class MenuMotorista {

	public static void iniciarAplicativoMotorista()
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

		// Variaveis para o menu
		int escolha;
		boolean b = true;
		Scanner entrada = new Scanner(System.in);

		while (b) {
			System.out.println("Bem vindo ao Menu Motorista");
			System.out.println("1) Login.");
			System.out.println("2) Sair");

			escolha = entrada.nextInt();
			entrada.nextLine();
			switch (escolha) {

			case 1:
				String emailLogin, senhaLogin;
				System.out.println("Digite seu email:");
				emailLogin = entrada.next();
				System.out.println("Digite sua senha:");
				senhaLogin = entrada.next();
				if (Gerente.verificarLoginMotorista(emailLogin, senhaLogin)) {
					System.out.println("Logado com Sucesso!");
				}

				// Boolean para controle do while
				boolean b1 = true;
				while (b1) {
					// Criando referencia para o Motorista logado. Feito a
					// partir do idMotorista
					long idMotorista = 0;
					for (int i = 0; i < Gerente.getRepoMotorista().buscarRepositorio().length; i++) {
						if (Gerente.getRepoMotorista().buscarRepositorio()[i] != null) {
							if (Gerente.getRepoMotorista().buscarRepositorio()[i].getEmail().equals(emailLogin)) {
								if (Gerente.getRepoMotorista().buscarRepositorio()[i].getSenha().equals(senhaLogin)) {
									idMotorista = Gerente.getRepoMotorista().buscarRepositorio()[i].getId();
								}
							}
						}
					}
					Motorista motorista = Gerente.getRepoMotorista().buscar(idMotorista);

					System.out.println("Bem vindo, " + motorista.getNome());
					System.out.println("Escolha o que deseja fazer:");
					System.out.println("1) Alterar status.");
					System.out.println("2) Alterar dados.");
					System.out.println("3) Excluir conta.");
					System.out.println("4) Logout.");

					escolha = entrada.nextInt();
					entrada.nextLine();
					switch (escolha) {

					case 1:

						System.out.println("Atualmente seu status é :" + motorista.getDisponivel());

						if (Gerente.getRepoMotorista().buscar(idMotorista).getDisponivel() == true) {
							Gerente.getRepoMotorista().buscar(idMotorista).setDisponivel(false);
							System.out.println("Você alterou sua disponibilidade para: false");
							DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());

						} else {
							Gerente.getRepoMotorista().buscar(idMotorista).setDisponivel(true);
							System.out.println("Sua disponibilidade foi alterada para: true");
							DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());

						}
						break;

					case 2:
						boolean b2 = true;
						while (b2) {

							String nome, cpf, senha, sexo, emailM;
							System.out.println("Menu de alteração de dados.");
							System.out.println("Qual dado que você deseja alterar?");
							System.out.println("1) Nome.");
							System.out.println("2) CPF.");
							System.out.println("3) Senha.");
							System.out.println("4) Email.");
							System.out.println("5) Sexo.");
							System.out.println("6) Sair.");

							escolha = entrada.nextInt();
							entrada.nextLine();
							switch (escolha) {

							case 1:
								System.out.println("Você desejou alterar o nome.");
								nome = entrada.nextLine();
								Gerente.getRepoMotorista().buscar(idMotorista).setNome(nome);
								DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());
								b1 = false;
								break;

							case 2:
								System.out.println(
										"Você desejou alterar o cpf. Lembre-se que o modelo correto é 'xxx.xxx.xxx-xx' ");
								cpf = entrada.next();
								Gerente.getRepoMotorista().buscar(idMotorista).setCpf(cpf);
								DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());
								b1 = false;
								break;
							case 3:
								System.out.println("Você desejou alterar a senha.");
								senha = entrada.next();
								Gerente.getRepoMotorista().buscar(idMotorista).setSenha(senha);
								DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());
								b1 = false;
								break;

							case 4:
								System.out.println("Você desejou alterar o email.");

								// Verificacao email duplicado
								try {
									boolean bEmail = true;
									while (bEmail) {

										System.out.println("Digite o novo email:");
										emailM = entrada.next();
										if (Gerente.verificarEmailSolicitante(emailM)
												&& Gerente.verificarEmailMotorista(emailM)) {
											Gerente.getRepoMotorista().buscar(idMotorista).setEmail(emailM);
											DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());
											b1 = false;
											bEmail = false;
											break;
										} else
											System.out.println("Email já existente. Tente outro");
									}
								} catch (RepositorioException e) {
									System.out.println("Ocorreu um erro. Digite seu email novamente:");
									emailM = entrada.next();
									Gerente.getRepoMotorista().buscar(idMotorista).setEmail(emailM);
									DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());
									b1 = false;
								}
								// Fim verificacao
								b1 = false;
								break;
							case 5:
								System.out.println("Você desejou alterar o sexo.");
								sexo = entrada.next();
								Gerente.getRepoMotorista().buscar(idMotorista).setSexo(sexo);
								DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());
								b1 = false;
								break;
							case 6:
								System.out.println("Você saiu.");
								b2 = false;
								break;
							default:
								System.out.println("Digite um numero valido" + "\n");
								break;
							}
						}

						break;

					case 3:
						b1 = false;
						Gerente.removerMotorista(motorista.getId());
						DATABASE.SalvarEstadoMotorista(Gerente.getRepoMotorista());
						break;

					case 4:
						System.out.println("Você saiu.");
						b1 = false;
						break;

					default:
						System.out.println("Digite um numero valido" + "\n");
						break;
					}

				}

				break;

			case 2:
				System.out.println("Você saiu.");
				b = false;
				break;

			default:
				System.out.println("Digite um numero valido" + "\n");
				break;

			}
		}
	}
}
