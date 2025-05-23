package org.serratec.avaliacao.repository;

import java.util.List;
import java.util.Optional;
import org.serratec.avaliacao.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FuncionarioRepository extends JpaRepository <Funcionario, Long> {
	
	//BUSCA FUNCIONARIO POR NOME, NÃO PRECISANDO ESTAR COMPLETO
	List<Funcionario> findByNomeIgnoreCaseStartingWith(String nome);
	
	//BUSCAR PELO NOME COMPLETO
	Optional<Funcionario> findByNomeIgnoreCase (String nome);
	
	//BUSCA FUNCIONÁRIO PELO ID, É OPCIONAL
	Optional<Funcionario> findById(Long id);
	
	//BUSCA FUNCIONÁRIO PELO ID, NÃO É OPCIONAL
	Funcionario findByIdObrigatorio (Long id);
}
