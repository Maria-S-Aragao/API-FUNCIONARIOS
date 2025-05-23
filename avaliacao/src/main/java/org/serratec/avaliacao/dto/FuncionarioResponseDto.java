package org.serratec.avaliacao.dto;

public class FuncionarioResponseDto {

	private Long id;
	private String nome;
	private String rua;
	private String bairro;
	private String cidade;
	private int numero;
	private String cep;
	
	public FuncionarioResponseDto () {}
	
	public FuncionarioResponseDto (Long id,String nome, String rua, String bairro, String cidade, int numero, String cep) {
		this.id = id;
		this.nome = nome;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.numero = numero;
		this.cep = cep;
	}

	public Long getId() {
		return id;}
	public void setId(Long id) {
		this.id = id;}
	
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

	public int getNumero() {
		return numero;}
	public void setNumero(int numero) {
		this.numero = numero;}

	public String getCep() {
		return cep;}
	public void setCep(String cep) {
		this.cep = cep;}
}

