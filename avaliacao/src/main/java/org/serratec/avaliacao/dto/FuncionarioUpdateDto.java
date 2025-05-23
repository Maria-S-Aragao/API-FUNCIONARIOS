package org.serratec.avaliacao.dto;

import jakarta.validation.constraints.NotBlank;

public class FuncionarioUpdateDto {

	@NotBlank (message="O campo nome deve ser preenchido!")
	private String nome;
	private EnderecoUpdateDto endereco;
	
	public FuncionarioUpdateDto () {}
	
	public FuncionarioUpdateDto (String nome, EnderecoUpdateDto endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;}
	public void setNome(String nome) {
		this.nome = nome;}

	public EnderecoUpdateDto getEndereco() {
		return endereco;}

	public void setEndereco(EnderecoUpdateDto endereco) {
		this.endereco = endereco;}
	
}
