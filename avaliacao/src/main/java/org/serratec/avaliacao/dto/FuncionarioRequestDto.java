package org.serratec.avaliacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

//SERÁ O DTO UTILIZADO PRA PEDIR OS DADOS AO USUÁRIO NA CRIAÇÃO DO OBJETO (POST)
public class FuncionarioRequestDto {

	@NotBlank (message="O campo nome deve ser preenchido!")
	private String nome;
	
	@NotBlank (message="O campo rua deve ser preenchido!")
	@Size(min=1, max=50, message="A rua precisa ter um nome válido!")
	private String rua;
	
	@NotBlank (message="O campo bairro deve ser preenchido!")
	@Size(min=1, max=50, message="O bairro precisa ter um nome válido!")
	private String bairro;
	
	@NotBlank (message="O campo cidade deve ser preenchido!")
	@Size(min=1, max=58, message="A cidade precisa ter um nome válido!")
	private String cidade;
	
	@Positive (message="O número residencial não pode ser negativo; caso não exista, deixe o campo vázio!")
	private Integer numero;
	
	@NotBlank (message="O campo CEP deve ser preenchido!")
	@Size(min=9, max=9, message="O CEP deve ser válido, no formato de XXXXX-XXX.")
	private String cep;
	
	public FuncionarioRequestDto () {}
	
	public FuncionarioRequestDto (String nome, String rua, String bairro, String cidade, Integer numero, String cep) {
		this.nome = nome;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.numero = numero;
		this.cep = cep;
	}

	public String getNome() {
		return nome;}
	public void setNome(String nome) {
		this.nome = nome;}

	public String getRua() {
		return rua;}
	public void setRua(String rua) {
		this.rua = rua;}

	public String getBairro() {
		return bairro;}
	public void setBairro(String bairro) {
		this.bairro = bairro;}

	public String getCidade() {
		return cidade;}
	public void setCidade(String cidade) {
		this.cidade = cidade;}

	public Integer getNumero() {
		return numero;}
	public void setNumero(Integer numero) {
		this.numero = numero;}

	public String getCep() {
		return cep;}
	public void setCep(String cep) {
		this.cep = cep;}
}
