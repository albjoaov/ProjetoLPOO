package br.acme.tests;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import br.acme.exception.*;
import br.acme.main.*;

public class Start {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, RepositorioException, CPFInvalidoException, CPFNullException, CPFVazioException, NomeVazioException, NomeNullException, SenhaVaziaException, SenhaNullException, EmailVazioException, EmailNullException, SexoVazioException, SexoNullException{
		
		Scanner entrada = new Scanner(System.in);
		int escolha;
		boolean b = true;
		
		while(b){
			System.out.println("BEM-VINDO AO TELETUBER.");
			System.out.println("\n"+ "Menu:"+"\n"+"1) Gerente.");
			System.out.println("2) Solicitante.");
			System.out.println("3) Motorista.");
			System.out.println("4) Sair.");

			
			escolha = entrada.nextInt();
			
			switch(escolha){
			case 1:
				MenuGerente.iniciarAplicativoGerente();
				break;
			case 2:
				MenuSolicitante.iniciarAplicativoSolicitante();
				break;
			case 3:
				MenuMotorista.iniciarAplicativoMotorista();
				break;
			case 4:
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
