package com.avantetech.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.avantetech.cursomc.domain.Cliente;
import com.avantetech.cursomc.domain.enums.TipoCliente;
import com.avantetech.cursomc.dto.ClienteNewDTO;
import com.avantetech.cursomc.repositories.ClienteRepository;
import com.avantetech.cursomc.resources.exception.FieldMessage;
import com.avantetech.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		
		
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "email ja existente"));
		}
		
		Cliente aux2 = repo.findByCpfOuCnpj(objDto.getCpfOuCnpj());
		
		if((aux2 != null) && BR.isValidCPF(aux2.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF ja existente"));
		}else if((aux2 != null) && BR.isValidCNPJ(aux2.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ ja existente"));
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}