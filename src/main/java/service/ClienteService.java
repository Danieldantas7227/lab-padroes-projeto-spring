package service;

import model.Cliente;

public interface ClienteService {

	//Só por redundância;
	public abstract Iterable<Cliente> buscarTodos();
	
	public abstract Cliente buscarPorId(Long id);
	
	void inserir(Cliente cliente);
	
	void atualizar(Long id, Cliente cliente);
	
	void deletar(Long id);
	
}
