package com.avantetech.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.avantetech.cursomc.domain.Cliente;
import com.avantetech.cursomc.dto.ClienteDTO;
import com.avantetech.cursomc.dto.ClienteNewDTO;
import com.avantetech.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes") // responde por este endpoint
public class ClientesResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // estou esperando um ID como parametro
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		// tenho que informa a anotação @PathVariable para saber q espero um id

		Cliente obj = service.find(id);
		// passo a responsabilidade para meu service.

		return ResponseEntity.ok().body(obj);
		// retorno um ok, e no corpo da msg o objeto encontrado

	}
	
	// INSERÇÃO
		@RequestMapping(method = RequestMethod.POST) // iformando que o metodo é post
		public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){ // requesBody, faz o json transformar para objeto java
			Cliente obj = service.fromDTO(objDto);
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
					path("/{id}").buildAndExpand(obj.getId()).toUri(); // pega a uri do novo recurso inserido
			return ResponseEntity.created(uri).build();
		}

	// UPDATE
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	// DELETE
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // estou esperando um ID como parametro
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// buscar all
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	// por paginação
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);

	}

}
