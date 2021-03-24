package com.avantetech.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
