package com.avantetech.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.avantetech.cursomc.domain.Pedido;
import com.avantetech.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos") // responde por este endpoint
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) // estou esperando um ID como parametro
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		// tenho que informa a anotação @PathVariable para saber q espero um id
		
		Pedido obj = service.find(id);
		// passo a responsabilidade para meu service.
		
		return ResponseEntity.ok().body(obj);
		// retorno um ok, e no corpo da msg o objeto encontrado
		
	}
	

	// INSERÇÃO
	@RequestMapping(method = RequestMethod.POST) // iformando que o metodo é post
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){ // requesBody, faz o json transformar para objeto java
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(obj.getId()).toUri(); // pega a uri do novo recurso inserido
		return ResponseEntity.created(uri).build();
	}

}
