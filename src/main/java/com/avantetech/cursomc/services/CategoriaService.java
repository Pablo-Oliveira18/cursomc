package com.avantetech.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.avantetech.cursomc.domain.Categoria;
import com.avantetech.cursomc.repositories.CategoriaRepository;
import com.avantetech.cursomc.services.exceptions.DataIntegrityException;
import com.avantetech.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {

	@Autowired // faz a auto instaciação do objeto
	private CategoriaRepository repo;

	public Categoria find(Integer id)  {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	
	public Categoria update(Categoria obj) {
		find(obj.getId()); // para ver se o objeto existe
		return repo.save(obj);
	}
	
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produto");
		}
		
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}

}
