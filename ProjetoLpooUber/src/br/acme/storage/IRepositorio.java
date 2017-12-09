package br.acme.storage;

import br.acme.exception.RepositorioException;

public interface IRepositorio<T> {

	public boolean verificarExistencia(T t);

	public void adicionar(T t) throws RepositorioException;

	public void remover(long id) throws RepositorioException;

	public void alterar(long id,T t) throws RepositorioException;

	public T buscar(long id) throws RepositorioException;

	public T[] buscarTodos() throws RepositorioException;
	
	public T[] buscarRepositorio() throws RepositorioException;

}
