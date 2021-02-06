package com.alencar.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alencar.cursomc.domain.Cliente;
import com.alencar.cursomc.dto.ClienteDTO;
import com.alencar.cursomc.repositories.ClienteRepository;
import com.alencar.cursomc.services.exceptions.DataIntegrityException;
import com.alencar.cursomc.services.exceptions.ObjectNotFoundException;


import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	public Cliente find (Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}
	
	//metodo de atualizar 
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());//metodo para verificar se ja existe uma categoria para ser alterada
		updateData(newObj, obj);//metodo auxiliar que busca no banco e adiciona as atualizações
		return repo.save(newObj);
	}
		
	//metodo de delete
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas!");
		}			
	}
		
	//metodo que lista todas as categorias
	public List<Cliente> findAll(){
		return repo.findAll();
	}
		
	//metodo de lista com paginação 
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
			
	}
		
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	//metodo auxiliar que busca o objeto no banco de dados e altera os dados novos mais mantendo os outros dados intactos
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
		
	

}
