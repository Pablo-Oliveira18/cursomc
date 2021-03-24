package com.avantetech.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avantetech.cursomc.domain.Cliente;
import com.avantetech.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes") // responde por este endpoint
public class ClientesResource {
	
	@Autowired
	private ClienteService service;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) // estou esperando um ID como parametro
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		// tenho que informa a anotação @PathVariable para saber q espero um id
		
		Cliente obj = service.find(id);
		// passo a responsabilidade para meu service.
		
		return ResponseEntity.ok().body(obj);
		// retorno um ok, e no corpo da msg o objeto encontrado
		
	
	}

}
