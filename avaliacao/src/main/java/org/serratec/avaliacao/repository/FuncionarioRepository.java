package org.serratec.avaliacao.repository;

import java.util.List;
import java.util.Optional;
import org.serratec.avaliacao.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/************************************************************
 *                     EXPLICAÇÃO:                           *
 * PRA QUE É UTILIZADA O REPOSITORIO? ESTÁ UMA EXPLICAÇÃO    *
 * 100% VINDA DOS FANTASMAS DA MINHA CABEÇA, PORTANTO NÃO É  *
 * NEM UM POUCO PRECISA. MAS PERCEBI QUE ELA REALMENTE SE    *
 * CONECTA COM A ENTIDADE FUNCIONARIO, SE REPARAR ELA JÁ     *
 * CONTEM UMA ARRAY DE TODOS OS OBJETOS DE FUNCIONARIOS, POIS*
 * OS PRÓPRIOS MÉTODOS DELA JÁ MAPEIAM A ENTIDADE, ALÉM DOS  *
 * DIVERSOS MÉTODOS PERSONALIZADOS LEGAIS, A EXISTÊNCIA DELA *
 * PERMITE UTILIZAR MUITOS OUTROS JÁ PRONTO, UTILIZEI 		 *
 * PRINCIPALMENTE NO SERVICE.								 *
 ************************************************************/

@Repository
public interface FuncionarioRepository extends JpaRepository <Funcionario, Long> {
	
	//BUSCA FUNCIONARIO POR NOME, NÃO PRECISANDO ESTAR COMPLETO
	List<Funcionario> findByNomeIgnoreCaseStartingWith(String nome);
	
	//BUSCAR PELO NOME COMPLETO
	Optional<Funcionario> findByNomeIgnoreCase (String nome);
	
	//BUSCA FUNCIONÁRIO PELO ID, É OPCIONAL
	Optional<Funcionario> findById(Long id);
	
	//BUSCA FUNCIONÁRIO PELO ID, NÃO É OPCIONAL
	@Query("SELECT f FROM Funcionario f WHERE f.id = :id")
	Funcionario findByIdObrigatorio(@Param("id") Long id);
}
