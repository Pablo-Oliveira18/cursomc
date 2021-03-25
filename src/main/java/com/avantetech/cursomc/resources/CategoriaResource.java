package com.avantetech.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.avantetech.cursomc.domain.Categoria;
import com.avantetech.cursomc.dto.CategoriaDTO;
import com.avantetech.cursomc.services.CategoriaService;


@RestController
@RequestMapping(value="/categorias") // responde por este endpoint
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) // estou esperando um ID como parametro
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		// tenho que informa a anotação @PathVariable para saber q espero um id
		
		Categoria obj = service.find(id);
		// passo a responsabilidade para meu service.
		
		return ResponseEntity.ok().body(obj);
		// retorno um ok, e no corpo da msg o objeto encontrado
		
	}
	
	// INSERÇÃO
	@RequestMapping(method = RequestMethod.POST) // iformando que o metodo é post
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){ // requesBody, faz o json transformar para objeto java
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(obj.getId()).toUri(); // pega a uri do novo recurso inserido
		return ResponseEntity.created(uri).build();
	}
	
	// UPDATE
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	// DELETE	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) // estou esperando um ID como parametro
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	// buscar all	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> list = service.findAll();		
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	// por paginação
	@RequestMapping(value = "/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page",defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC") String direction) {
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);		
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
