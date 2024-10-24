package service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Cliente;
import model.ClienteRepository;
import model.Endereco;
import model.EnderecoRepository;
import service.ClienteService;
import service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ViaCepService ViacepService;
	
	
	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	
	@Override
	public Cliente buscarPorId(Long id) {
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElse(null);//Para caso o cliente não exista, pois é um optional!
	}

	
	@Override
	public void inserir(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(()-> {//Pega o cep do endereço do cliente, e se não pegar pois não existe, irá consultar o endereço viacepService e adicionar um endereço!! 
		
		Endereco novoEndereco = ViacepService.consultarCep(cep);
		enderecoRepository.save(novoEndereco);
		
		return novoEndereco;//Irá retornar essa váriavel para o "endereco"!!!
		});
		
		cliente.setEndereco(endereco);
		
		clienteRepository.save(cliente);
	}

	
	@Override
	public void atualizar(Long id, Cliente cliente) {
		
		Optional<Cliente> clienteUpdate = clienteRepository.findById(id);
		
		if (clienteUpdate.isPresent()) {
			String cep = cliente.getEndereco().getCep();
			
			Endereco endereco = enderecoRepository.findById(cep).orElseGet(()-> {//Pega o cep do endereço do cliente, e se não pegar pois não existe, irá consultar o endereço viacepService e adicionar um endereço!! 
			
			Endereco novoEndereco = ViacepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			
			return novoEndereco;//Irá retornar essa váriavel para o "endereco"!!!
			});
			cliente.setEndereco(endereco);
			clienteRepository.save(cliente);
		}
	}

	
	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}

	
}
