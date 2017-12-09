package br.acme.storage;

import br.acme.location.Viagem;
import br.acme.users.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class DATABASE implements Serializable {

	static File Motorista = new File("RepositorioMotorista.txt");
	static File Solicitante = new File("RepositorioSolicitante.txt");
	static File Viagem = new File("RepositorioViagem.txt");
	static File Gerente = new File("Gerente.txt");



	public static void SalvarEstadoMotorista(IRepositorio<Motorista> repoMotorista) throws IOException{

		OutputStream fw;
		fw = new FileOutputStream(Motorista);
		ObjectOutputStream write = new ObjectOutputStream(fw);
		write.writeObject(repoMotorista);
		fw.close();
	}

	public static RepositorioMotorista LerBaseMotorista() throws IOException, ClassNotFoundException{

		ObjectInputStream read;
		InputStream fr = new FileInputStream(Motorista);
		read = new ObjectInputStream(fr);
		RepositorioMotorista m = (RepositorioMotorista) read.readObject();
		return m;
	}

	public static void SalvarEstadoSolicitante(IRepositorio<Solicitante> repoSolicitante) throws IOException{

		OutputStream fw;
		fw = new FileOutputStream(Solicitante);
		ObjectOutputStream write = new ObjectOutputStream(fw);
		write.writeObject(repoSolicitante);
		fw.close();
	}

	public static RepositorioSolicitante LerBaseSolicitante() throws IOException, ClassNotFoundException{

		ObjectInputStream read;
		InputStream fr = new FileInputStream(Solicitante);
		read = new ObjectInputStream(fr);
		RepositorioSolicitante s = (RepositorioSolicitante) read.readObject();
		return s;
	}

	public static void SalvarEstadoViagem(IRepositorio <Viagem> repoViagem) throws IOException {
		
		OutputStream fw;
		fw = new FileOutputStream(Viagem);
		ObjectOutputStream write = new ObjectOutputStream(fw);
		write.writeObject(repoViagem);
		fw.close();
	}

	public static RepositorioViagem LerBaseViagem() throws IOException, ClassNotFoundException {

		ObjectInputStream read;
		InputStream fr = new FileInputStream(Viagem);
		read = new ObjectInputStream(fr);
		RepositorioViagem v = (RepositorioViagem) read.readObject();
		return v;

	}

	public static void SalvarEstadoGerente(Gerente gerente) throws IOException {

		OutputStream fw;
		fw = new FileOutputStream(Gerente);
		ObjectOutputStream write = new ObjectOutputStream(fw);
		write.writeObject(gerente);
		fw.close();
	}

	public static Gerente LerBaseGerente() throws IOException, ClassNotFoundException {

		ObjectInputStream read;
		InputStream fr = new FileInputStream(Gerente);
		read = new ObjectInputStream(fr);
		Gerente g = (Gerente) read.readObject();
		return g;
	}
}

			
			