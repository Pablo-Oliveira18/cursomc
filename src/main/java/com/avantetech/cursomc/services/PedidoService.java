package com.avantetech.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avantetech.cursomc.domain.Pedido;
import com.avantetech.cursomc.repositories.PedidoRepository;
import com.avantetech.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class PedidoService {

	@Autowired // faz a auto instaciação do objeto
	private PedidoRepository repo;

	public Pedido buscar(Integer id)  {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
