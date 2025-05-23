package org.serratec.avaliacao.controller;

import java.util.List;
import org.serratec.avaliacao.Mapper.FuncionarioMapper;
import org.serratec.avaliacao.domain.Funcionario;
import org.serratec.avaliacao.dto.FuncionarioRequestDto;
import org.serratec.avaliacao.dto.FuncionarioResponseDto;
import org.serratec.avaliacao.infra.BusinessException;
import org.serratec.avaliacao.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
	public FuncionarioResponseDto buscarPorId(@RequestParam Long id) {
	    Funcionario funcionario = servico.buscarPorId(id).orElseThrow(() -> new BusinessException("Não há nenhum funcionário cadastrado com este id!"));
	    return FuncionarioMapper.toFuncionarioResponse(funcionario);
	}
	
	@GetMapping("/buscarPorNome/{nome}")
	public List<FuncionarioResponseDto> buscarPorNome(@RequestParam String nome) {
		List<FuncionarioResponseDto> listaFuncionariosResponse = FuncionarioMapper.toListFuncionarioResponse(servico.buscarPorNome(nome));
		return listaFuncionariosResponse;
	}
	
	//POST
	@PostMapping("/cadastrar")
	public FuncionarioResponseDto cadastrar(@RequestBody FuncionarioRequestDto request) {
		Funcionario novoFuncionario = servico.salvar(FuncionarioMapper.toFuncionario(request));
		FuncionarioResponseDto funcionarioResponse = FuncionarioMapper.toFuncionarioResponse(novoFuncionario);
		return funcionarioResponse;
	}
	
	//PUT
	@PutMapping("/atualizacaoCompleta/{id}")
	public FuncionarioResponseDto atualizacaoCompleta(@RequestParam Long id, @RequestBody FuncionarioRequestDto request) {
		servico.buscarPorId(id).orElseThrow(() -> new BusinessException("Não há nenhum funcionário cadastrado com este id!"));
		Funcionario funcionario =  servico.atualizacao(id, FuncionarioMapper.toFuncionario(request));
		FuncionarioResponseDto funcionarioResponse = FuncionarioMapper.toFuncionarioResponse(funcionario);
		return funcionarioResponse;
	}
	
	//PATCH
	@PatchMapping ("/atualizacaoParcial/{id}")
	public FuncionarioResponseDto atualizacaoParcial(@RequestParam Long id, @RequestBody FuncionarioRequestDto request) {
		servico.buscarPorId(id).orElseThrow(() -> new BusinessException("Não há nenhum funcionário cadastrado com este id!"));
		Funcionario funcionario =  servico.atualizacaoParcial(id, FuncionarioMapper.toFuncionario(request));
		FuncionarioResponseDto funcionarioResponse = FuncionarioMapper.toFuncionarioResponse(funcionario);
		return funcionarioResponse;
	}
	
	//DELETE
	@DeleteMapping("/exclusao/{id}")
	public void exclusaoFuncionario(@RequestParam Long id) {
		servico.buscarPorId(id).orElseThrow(() -> new BusinessException("Não há nenhum funcionário cadastrado com este id!"));
		servico.deletarPorId(id);
	}
	
}
