package org.serratec.avaliacao.controller;

import java.util.List;
import org.serratec.avaliacao.Mapper.FuncionarioMapper;
import org.serratec.avaliacao.domain.Funcionario;
import org.serratec.avaliacao.dto.FuncionarioRequestDto;
import org.serratec.avaliacao.dto.FuncionarioResponseDto;
import org.serratec.avaliacao.dto.FuncionarioUpdateDto;
import org.serratec.avaliacao.infra.BusinessException;
import org.serratec.avaliacao.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;


/*PERCEBI POR AGORA QUE OS MEUS MÉTODOS NÃO ESTÃO MUITO SEGUROS, 
 * MESMO UTILIZANDO OS DTOs, SIMPLESMENTE PORQUE EU USO ESSES NESTA
 * CLASSE, TRANSFORMADO OS OBJETOS DIRETAMENTE POR AQUI EM OBJETOS 
 * DO TIPO FUNCIONARIO, ISTO NÃO DEVERIA SER ASSIM. VAI SER A PRÓXIMA
 * MELHORIA, FAÇA TODAS AS CONVERÇÕES NO SERVICE.*/
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService servico;

	//GET's
	@GetMapping("/listar")
	public List<FuncionarioResponseDto> listar() {	
		List<FuncionarioResponseDto> listaFuncionariosResponse = FuncionarioMapper.toListFuncionarioResponse(servico.listarTodos());
		return listaFuncionariosResponse;
	}
	
	@GetMapping("/buscarPorId/{id}")
	public FuncionarioResponseDto buscarPorId(@PathVariable Long id) {
	    Funcionario funcionario = servico.buscarPorId(id).orElseThrow(() -> new BusinessException("Não há nenhum funcionário cadastrado com este id!"));
	    return FuncionarioMapper.toFuncionarioResponse(funcionario);
	}
	
	@GetMapping("/buscarPorNome/{nome}")
	public List<FuncionarioResponseDto> buscarPorNome(@PathVariable String nome) {
		List<FuncionarioResponseDto> listaFuncionariosResponse = FuncionarioMapper.toListFuncionarioResponse(servico.buscarPorNome(nome));
		return listaFuncionariosResponse;
	}
	
	//POST
	@PostMapping("/cadastrar")
	public FuncionarioResponseDto cadastrar(@Valid @RequestBody FuncionarioRequestDto request) {
		Funcionario novoFuncionario = servico.salvar(FuncionarioMapper.toFuncionario(request));
		FuncionarioResponseDto funcionarioResponse = FuncionarioMapper.toFuncionarioResponse(novoFuncionario);
		return funcionarioResponse;
		
		/*O PADRÃO DO JSON:
		 * {
			  "nome": "Maria Oliveira",
			  "rua": "Rua das Flores",
			  "bairro": "Jardim Primavera",
			  "cidade": "Teresópolis",
			  "numero": 0,
			  "cep": "25900-000"
		}*/
	}
	
	//PUT
	@PutMapping("/atualizacaoCompleta/{id}")
	public FuncionarioResponseDto atualizacaoCompleta(@PathVariable Long id, @RequestBody FuncionarioRequestDto request) {
		servico.buscarPorId(id).orElseThrow(() -> new BusinessException("Não há nenhum funcionário cadastrado com este id!"));
		Funcionario funcionario =  servico.atualizacao(id, FuncionarioMapper.toFuncionario(request));
		FuncionarioResponseDto funcionarioResponse = FuncionarioMapper.toFuncionarioResponse(funcionario);
		return funcionarioResponse;
		
		/*O PADRÃO DO JSON:
		 * {
			  "nome": "Maria Oliveira",
			  "rua": "Rua das Flores",
			  "bairro": "Jardim Primavera",
			  "cidade": "Teresópolis",
			  "numero": 0,
			  "cep": "25900-000"
		}*/
	}
	
	//PATCH
	@PatchMapping ("/atualizacaoParcial/{id}")
	public FuncionarioResponseDto atualizacaoParcial(@PathVariable Long id, @RequestBody FuncionarioUpdateDto request) {
		servico.buscarPorId(id).orElseThrow(() -> new BusinessException("Não há nenhum funcionário cadastrado com este id!"));
		
		Funcionario funcionario =  servico.atualizacaoParcial(id, request);
		FuncionarioResponseDto funcionarioResponse = FuncionarioMapper.toFuncionarioResponse(funcionario);
		return funcionarioResponse;
		
		/*O PADRÃO DO JSON:
		  {
		  "nome": "João da Silva",
		  "endereco": {
		    "rua": "Rua do Sol",
		    "bairro": "Centro",
		    "cidade": "Petrópolis",
		    "numero": 0,
		    "cep": "25600-123"
		  }
		}
  		*/
	}
	
	//DELETE
	@DeleteMapping("/exclusao/{id}")
	public void exclusaoFuncionario(@PathVariable Long id) {
		servico.buscarPorId(id).orElseThrow(() -> new BusinessException("Não há nenhum funcionário cadastrado com este id!"));
		servico.deletarPorId(id);
	}
	
}
