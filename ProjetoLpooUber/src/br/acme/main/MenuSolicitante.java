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

public class MenuSolicitante {

	private static String emailAux;

	public static void iniciarAplicativoSolicitante()
			throws CPFInvalidoException, CPFNullException, CPFVazioException, NomeVazioException, NomeNullException,
			SenhaVaziaException, SenhaNullException, EmailVazioException, EmailNullException, SexoVazioException,
			SexoNullException, RepositorioException, IOException, ClassNotFoundException {

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

		int escolha;
		Random rand = new Random();
		Scanner entrada = new Scanner(System.in);
		boolean b = true;

		while (b) {
			System.out.println("Bem vindo ao Menu Solicitante.");
			System.out.println("Selecione a opção que você deseja: ");
			System.out.println("1) Cadastro");
			System.out.println("2) Login.");
			System.out.println("3) Sair");

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
				senhaS = entrada.next();

				// Verificacao email duplicado
				try {
					boolean bEmail1 = true;
					while (bEmail1) {
						System.out.println("Digite seu email:");
						emailS = entrada.next();
						if (Gerente.verificarEmailSolicitante(emailS) && Gerente.verificarEmailMotorista(emailS)) {
							emailAux = emailS;
							bEmail1 = false;
						} else
							System.out.println("Email já existente. Tente outro");
					}
				} catch (RepositorioException e) {
					System.out.println("Ocorreu um erro. Digite seu email novamentes:");
					emailAux = entrada.next();
				}

				System.out.println("Diga o seu sexo.");
				sexoS = entrada.next();

				System.out.println("Digite o numero do seu celular:");
				numeroCelularS = entrada.nextInt();
				entrada.nextLine();

				Solicitante s1 = new Solicitante(idS, cpfS, nomeS, senhaS, emailAux, sexoS, data, numeroCelularS);
				Gerente.cadastrarSolicitante(s1);
				DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
				break;

			case 2:
				String emailLogin, senhaLogin;
				System.out.println("Digite seu email:");
				emailLogin = entrada.next();
				System.out.println("Digite sua senha:");
				senhaLogin = entrada.next();
				if (Gerente.verificarLoginSolicitante(emailLogin, senhaLogin)) {
					System.out.println("Logado com Sucesso!");
				}

				boolean b1 = true;
				while (b1) {
					// LOGADO
					long idUsuario = 0;
					for (int i = 0; i < Gerente.getRepoSolicitante().buscarRepositorio().length; i++) {
						if (Gerente.getRepoSolicitante().buscarRepositorio()[i] != null) {
							if (Gerente.getRepoSolicitante().buscarRepositorio()[i].getEmail().equals(emailLogin)) {
								if (Gerente.getRepoSolicitante().buscarRepositorio()[i].getSenha().equals(senhaLogin)) {
									idUsuario = Gerente.getRepoSolicitante().buscarRepositorio()[i].getId();
								}
							}
						}
					}

					Solicitante usuario = Gerente.getRepoSolicitante().buscar(idUsuario);

					System.out.println("Bem vindo, " + usuario.getNome());
					System.out.println("Escolha o que deseja fazer:");
					System.out.println("1) Pedir carona");
					System.out.println("2) Listar viagens");
					System.out.println("3) Alterar dados");
					System.out.println("4) Excluir conta");
					System.out.println("5) Logout");

					escolha = entrada.nextInt();
					entrada.nextLine();
					switch (escolha) {
					case 1:
						long idM;
						double valor = 10;
						String pagamento, idOrigem, endOrigem, idDestino, endDestino;

						System.out.println("A lista de todos os motoristas é:");
						try {
							Gerente.listarMotoristasDisponives();
						} catch (RepositorioException e) {
							System.out.println("Não há motoristas disponíveis, tente novamente mais tarde.");
							break;
						}

						System.out.println("Digite o id do motorista desejado:");
						idM = entrada.nextInt();
						entrada.nextLine();
						
						Motorista motorista = Gerente.getRepoMotorista().buscar(idM);

						System.out.println("Digite a sua forma de pagamento");
						pagamento = entrada.nextLine();

						System.out.println("Informe o seu local de origem");
						idOrigem = entrada.nextLine();
						System.out.println("Informe o seu endereco de origem");
						endOrigem = entrada.nextLine();

						System.out.println("Informe o seu local de destino");
						idDestino = entrada.nextLine();
						System.out.println("Informe o seu endereco de destino");
						endDestino = entrada.nextLine();

						Lugar origem = new Lugar(idOrigem, endOrigem);
						Lugar destino = new Lugar(idDestino, endDestino);

						Viagem v1 = new Viagem(rand.nextInt(100), usuario, motorista, origem, destino, valor,
								pagamento);

						boolean b3 = true;
						while (b3) {
							System.out.println("Voce deseja CANCELAR viagem?");
							System.out.println("Digite 1 - Sim");
							System.out.println("Digite 2 - Nao");
							escolha = entrada.nextInt();
							entrada.nextLine();

							switch (escolha) {
							case 1:
								System.out.println("Cancelando viagem e retornando ao Menu anterior...");
								b3 = false;
								break;
							case 2:
								System.out.println("Completando viagem");
								Gerente.getRepoSolicitante().buscar(idUsuario).solicitarCarona(v1);
								DATABASE.SalvarEstadoViagem(Gerente.getRepoViagem());
								b3 = false;
								break;
							default:
								System.out.println("Digite um numero valido" + "\n");
								break;

							}
						}

						break;

					case 2:
						Gerente.getRepoSolicitante().buscar(idUsuario).historico();
						break;

					case 3:
						boolean b2 = true;
						while (b2) {

							String nome, cpf, senha, sexo, email;
							int telefone;
							System.out.println("Menu de alteração de dados.");
							System.out.println("Qual dado que você deseja alterar?");
							System.out.println("1) Nome.");
							System.out.println("2) CPF.");
							System.out.println("3) Senha.");
							System.out.println("4) Email.");
							System.out.println("5) Sexo.");
							System.out.println("6) Numero do Celular.");
							System.out.println("7) Sair.");

							escolha = entrada.nextInt();
							entrada.nextLine();

							switch (escolha) {

							case 1:
								System.out.println("Você desejou alterar o nome.");
								nome = entrada.nextLine();
								Gerente.getRepoSolicitante().buscar(idUsuario).setNome(nome);
								DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
								b1 = false;
								break;

							case 2:
								System.out.println(
										"Você desejou alterar o cpf. Lembre-se que o modelo correto é 'xxx.xxx.xxx-xx' ");
								cpf = entrada.next();
								Gerente.getRepoSolicitante().buscar(idUsuario).setCpf(cpf);
								DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
								b1 = false;
								break;
							case 3:
								System.out.println("Você desejou alterar a senha.");
								senha = entrada.next();
								Gerente.getRepoSolicitante().buscar(idUsuario).setSenha(senha);
								DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
								b1 = false;
								break;

							case 4:
								System.out.println("Você desejou alterar o email.");

								// Verificacao email duplicado
								try {
									boolean bEmail2 = true;
									while (bEmail2) {

										System.out.println("Digite o novo email:");
										emailS = entrada.next();
										if (Gerente.verificarEmailSolicitante(emailS)
												&& Gerente.verificarEmailMotorista(emailS)) {
											Gerente.getRepoSolicitante().buscar(idUsuario).setEmail(emailS);
											DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
											b1 = false;
											bEmail2 = false;
											break;
										} else
											System.out.println("Email já existente. Tente outro");
									}
								} catch (RepositorioException e) {
									System.out.println("Ocorreu um erro. Digite seu email novamente:");
									emailS = entrada.next();	
									Gerente.getRepoSolicitante().buscar(idUsuario).setEmail(emailS);
									DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
									b1 = false;
								}
								// Fim verificacao
								b1 = false;
								break;
							case 5:
								System.out.println("Você desejou alterar o sexo.");
								sexo = entrada.next();
								Gerente.getRepoSolicitante().buscar(idUsuario).setSexo(sexo);
								DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
								b1 = false;
								break;
							case 6:
								System.out.println("Você desejou alterar o telefone");
								telefone = entrada.nextInt();
								entrada.nextLine();
								Gerente.getRepoSolicitante().buscar(idUsuario).setNumeroCelular(telefone);
								DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
								b1 = false;
								break;
							case 7:
								System.out.println("Você saiu.");
								b2 = false;
								break;
							default:
								System.out.println("Digite um numero valido" + "\n");
								break;
							}
						}

						break;

					case 4:
						b1 = false;
						Gerente.removerSolicitante(usuario.getId());
						DATABASE.SalvarEstadoSolicitante(Gerente.getRepoSolicitante());
						break;

					case 5:
						System.out.println("Você saiu.");
						b1 = false;
						break;

					default:
						System.out.println("Digite um numero valido" + "\n");
						break;
					}

				}
				break;

			// FIM DO MENU DE LOGIN DO SOLICITANTE
			case 3:
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
