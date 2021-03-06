package com.alencar.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alencar.cursomc.domain.Categoria;
import com.alencar.cursomc.dto.CategoriaDTO;
import com.alencar.cursomc.repositories.CategoriaRepository;
import com.alencar.cursomc.services.exceptions.DataIntegrityException;
import com.alencar.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	
	@Autowired
	private CategoriaRepository repo;
	
	//metodo de pesquisa por codigo no banco de dados
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! Id: " + id + ", Tipo: " + Categoria.class.getName())); 
	}
	
	//metodo de inserir no banco de dados
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	//metodo de atualizar 
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
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
			throw new DataIntegrityException("Não é possivel excluir uma Categoria que possui Produtos!");
		}			
	}
	
	//metodo que lista todas as categorias
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	//metodo de lista com paginação 
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	//metodo auxiliar que busca o objeto no banco de dados e altera os dados novos mais mantendo os outros dados intactos
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
	
	

}
