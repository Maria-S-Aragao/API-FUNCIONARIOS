package org.serratec.avaliacao.service;

import java.util.List;
import java.util.Optional;
import org.serratec.avaliacao.domain.Funcionario;
import org.serratec.avaliacao.dto.EnderecoUpdateDto;
import org.serratec.avaliacao.dto.FuncionarioUpdateDto;
import org.serratec.avaliacao.infra.BusinessException;
import org.serratec.avaliacao.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;


@Service
public class FuncionarioService {
	
	private FuncionarioRepository repositorio;

    public FuncionarioService(FuncionarioRepository repositorio) {
        this.repositorio = repositorio;
    }
    
    //SALVAR
    public Funcionario salvar(Funcionario funcionario) {
    	/*VERIFICAÇÃO SE O FUNCIONÁRIO JÁ EXISTE, NÃO ESTOU ACEITANDO NA MINHA EMPRESA 
    	 * FUNCIONÁRIOS COM O MESMO NOME, MINHA REGRA DE NEGÓCIO*/
    	Optional<Funcionario> existeIgual = repositorio.findByNomeIgnoreCase(funcionario.getNome());
    	boolean igualzinho = false;
    	
    	if (existeIgual.isPresent()) {
    		/*AQUI EU VERIFICO SE REALMENTE SÓ ESTÁ TENTANDO CADASTRAR O FUNCIONÁRIO 
    		 * NOVAMENTE POR MEIO DO ENDEREÇO, JÁ QUE ASSIM ESTARIA IDENTICO, O IDEAL 
    		 * SERIA A UTILIZAÇÃO DO ID, MAS O PRÓPRIO SISTEMA VAI INCREMENTANDO E 
    		 * CRIANDO.*/
    		if(existeIgual.get().getEndereco().equals(funcionario.getEndereco())) {
    			igualzinho = true;
    		}
    	}
    	
    	/*O MÉTODO THROW PARA A APLICAÇÃO, O NEW JÁ CRIA O "OBJETO/FUNÇÃO", 
    	 * BEM É UMA MISTURA, MAS É POR ISSO QUE PODE SER FEITO DESTA MANEIRA*/
    	if (igualzinho) {
    		throw new BusinessException("Este funcionário já está cadastrado!");
    	}
    	
    	return repositorio.save(funcionario);
    }
    
    //LISTAR TODOS
    public List<Funcionario> listarTodos() {
    	return repositorio.findAll();
    } 
    
    //LISTAR POR NOME
    public List<Funcionario> buscarPorNome (String nome){
    	return repositorio.findByNomeIgnoreCaseStartingWith(nome);
    }
    
    //LISTAR POR ID, ESTE OPTIONAL É PORQUE PODE SER QUE NÃO SEJA ENCONTRADO
	public Optional<Funcionario> buscarPorId(Long id) {
    	return repositorio.findById(id);
    }
    
    //DELETAR POR ID
	public void deletarPorId(Long id) {
		repositorio.deleteById(id);
	}
	
	//ATUALIZAÇÃO COMPLETA. MENTIRA, PORQUE NÃO FAZ A VERIFICAÇÃO SE REALMENTE TODOS OS DADOS SÃO DIFERENTES
	public Funcionario atualizacao(Long id, Funcionario funcionarioAtualizado) {
		Funcionario funcionario = repositorio.findByIdObrigatorio(id);
		
		funcionario.setNome(funcionarioAtualizado.getNome());
		funcionario.setEndereco(funcionarioAtualizado.getEndereco());
			
		//OBSERVAÇÃO INTERESSANTE, O MÉTODO SAVE SERVE TANTO PRA SALVAR QUANTO PRA ATUALIZAR CASO O ID EXISTA
		return repositorio.save(funcionario);
	}
	
	
	//ATUALIZAÇÃO PARCIAL
	public Funcionario atualizacaoParcial(Long id, FuncionarioUpdateDto funcionarioAtualizado) {
	    Funcionario funcionarioExiste = repositorio.findByIdObrigatorio(id); 

	    boolean houveAlteracao = false;

	    if (funcionarioAtualizado.getNome() != null && !funcionarioAtualizado.getNome().equals(funcionarioExiste.getNome())) {
	        funcionarioExiste.setNome(funcionarioAtualizado.getNome());
	        houveAlteracao = true;
	    }
	    
	    EnderecoUpdateDto enderecoDto = funcionarioAtualizado.getEndereco();
		    if (enderecoDto != null) {
			        EnderecoUpdateDto enderecoUpdate = funcionarioAtualizado.getEndereco();
			        
			        if (enderecoUpdate == null) {
			            funcionarioAtualizado.setEndereco(enderecoUpdate);
			        }
		
			        //RUA
			        if (enderecoUpdate.getRua() != null && !enderecoUpdate.getRua().equals(funcionarioExiste.getEndereco().getRua())) {
			            funcionarioExiste.getEndereco().setRua(enderecoUpdate.getRua());
			            houveAlteracao = true;
			        }
		
			       //BAIRRO
			        if (enderecoUpdate.getBairro() != null && !enderecoUpdate.getBairro().equals(funcionarioExiste.getEndereco().getBairro())) {
			        	funcionarioExiste.getEndereco().setBairro(enderecoUpdate.getBairro());
			            
			            houveAlteracao = true;
			        }
			        
			       //CIDADE
			        if (enderecoUpdate.getCidade() != null && !enderecoUpdate.getCidade().equals(funcionarioExiste.getEndereco().getCidade())) {
			        	funcionarioExiste.getEndereco().setCidade(enderecoUpdate.getCidade());
			            houveAlteracao = true;
			        }
			        
			       //NUMERO
			        if (enderecoUpdate.getNumero() != null && !enderecoUpdate.getNumero().equals(funcionarioExiste.getEndereco().getNumero())) {
			        	funcionarioExiste.getEndereco().setNumero(enderecoUpdate.getNumero());
			            houveAlteracao = true;
			        }
			        
			       //CEP
			        if (enderecoUpdate.getCep() != null && !enderecoUpdate.getCep().equals(funcionarioExiste.getEndereco().getCep())) {
			        	funcionarioExiste.getEndereco().setCep(enderecoUpdate.getCep());
			            houveAlteracao = true;
			        }
	
		    if (!houveAlteracao) {
		        throw new BusinessException("Nenhuma modificação foi realizada.");
		    }
	    }
		    
	    return repositorio.save(funcionarioExiste);
	}
	
}
