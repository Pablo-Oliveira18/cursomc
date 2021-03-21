package com.avantetech.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avantetech.cursomc.domain.Categoria;
import com.avantetech.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	
	@Autowired // faz a auto instaciação do objeto
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
		
	}
	
}
