package com.avantetech.cursomc.repositories;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avantetech.cursomc.domain.Cliente;

//A interface vai herda de outra interface, onde se passa por parametro
//o tipo e o tipo do atributo indentificado, que no caso é o id
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);

	Cliente findByCpfOuCnpj(String cpfOuCnpj);
}
