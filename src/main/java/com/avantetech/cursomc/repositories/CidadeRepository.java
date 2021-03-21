package com.avantetech.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avantetech.cursomc.domain.Cidade;

//A interface vai herda de outra interface, onde se passa por parametro
//o tipo e o tipo do atributo indentificado, que no caso é o id
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
	
	

}
