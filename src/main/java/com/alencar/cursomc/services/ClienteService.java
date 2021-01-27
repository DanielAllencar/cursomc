package com.alencar.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alencar.cursomc.domain.Cliente;
import com.alencar.cursomc.repositories.ClienteRepository;
import com.alencar.cursomc.services.exceptions.ObjectNotFoundException;

import java.util.Optional;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! Id: " + id + ", Tipo: " + Cliente.class.getName())); 

		
	}
	

}
