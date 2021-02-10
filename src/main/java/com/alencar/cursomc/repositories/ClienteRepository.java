package com.alencar.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alencar.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	//implementa metdo de busca por email
	@Transactional(readOnly=true)//readOnly significa que ela nao e uma trasação de banco de dados deixa a busca mais rapida
	Cliente findByEmail(String email);
	
	

}
