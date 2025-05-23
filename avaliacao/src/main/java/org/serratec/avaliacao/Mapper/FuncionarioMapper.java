package org.serratec.avaliacao.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.serratec.avaliacao.domain.Endereco;
import org.serratec.avaliacao.domain.Funcionario;
import org.serratec.avaliacao.dto.FuncionarioRequestDto;
import org.serratec.avaliacao.dto.FuncionarioResponseDto;

/************************************************************
 *                     EXPLICAÇÃO:                           *
 * BREVE EXPLICAÇÃO SOBRE O PACKAGE MAPPER E ESTA CLASSE     *
 * EM ESPECÍFICO, ELA SERVE PARA CONVERSÃO DOS DTOS, TANTO   *
 * PARA UM OBJETO DE UMA ENTIDADE, QUANTO PARA TRANSFORMAR O *
 * OBJETO DE UMA ENTIDADE EM UM DTO. ESSA CONVERSÃO PODERIA  *
 * SER FEITA NO SERVICE EM PROJETOS PEQUENOS, MAS POR UMA    *
 * QUESTÃO DE ORGANIZAÇÃO FAREI DESTA FORMA.                 *
 ************************************************************/
public class FuncionarioMapper {
	
	//ESTE MÉTODO VAI RECEBER UM OBJETO EM DTO, APÓS A CONVERSÃO RETORNARÁ UM OBJETO FUNCIONARIO
	public static Funcionario toFuncionario(FuncionarioRequestDto request) {
		
		Endereco endereco = new Endereco();
		endereco.setRua(request.getRua());
		endereco.setBairro(request.getBairro());
		endereco.setCidade(request.getCidade());
		endereco.setNumero(request.getNumero());
		endereco.setCep(request.getCep());
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(request.getNome());
		funcionario.setEndereco(endereco);
		
		return funcionario;
	}
	
	//AGORA VAI SER O CONTRÁRIO, ESTE MÉTODO VAI RECEBER UM FUNCIONARIO E RETORNAR UM FUNCIONARIORESPONSE
	public static FuncionarioResponseDto toFuncionarioResponse (Funcionario funcionario) {
		
		FuncionarioResponseDto funcionarioResponse = new FuncionarioResponseDto();
		
		funcionarioResponse.setId(funcionario.getId());
		funcionarioResponse.setNome(funcionario.getNome());
		
		funcionarioResponse.setRua(funcionario.getEndereco().getRua());
		funcionarioResponse.setBairro(funcionario.getEndereco().getBairro());
		funcionarioResponse.setCidade(funcionario.getEndereco().getCidade());
		funcionarioResponse.setNumero(funcionario.getEndereco().getNumero());
		funcionarioResponse.setCep(funcionario.getEndereco().getCep());
		
		return funcionarioResponse;
	}
	
	//ESTE MÉTODO VAI RECEBER UMA LISTA DE FUNCIONARIOS E ME RETORNAR UM FUNCIONARIORESPONSE
	public static List<FuncionarioResponseDto> toListFuncionarioResponse(List<Funcionario> listaFuncionarios) {
		
		List<FuncionarioResponseDto> listFuncionariosResponse = new ArrayList<>();
		
		for (Funcionario f: listaFuncionarios) {
			listFuncionariosResponse.add(toFuncionarioResponse(f));
		}
		
		return listFuncionariosResponse;
	}
		
}
