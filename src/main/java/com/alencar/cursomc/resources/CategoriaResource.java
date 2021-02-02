package com.alencar.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alencar.cursomc.domain.Categoria;
import com.alencar.cursomc.dto.CategoriaDTO;
import com.alencar.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	//metodo de pesquisa por codigo                               
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	//Mapeamento que indica um metodo de inserir 
	@RequestMapping(method = RequestMethod.POST)
	//metodo post de inserir no banco de dados que chama na pasta de services
	//@RequestBody faz com que o Json seja convertido para o objeto java automaticamente 
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){ 
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//metodo de atualizar
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> Update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	//metodo de excluir
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Categoria> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//metodo de pesquisa por lita                           
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<CategoriaDTO>> findAll() {
			List<Categoria> list = service.findAll();
			List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); // converte a lista de categorias retirando os produtos
			return ResponseEntity.ok().body(listDto);		
		}
}
	
	

