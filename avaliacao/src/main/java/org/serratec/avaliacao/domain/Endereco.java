package org.serratec.avaliacao.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
	private String rua;
	private String bairro;
	private String cidade;
	private Integer numero;
	private String cep;
	
	public Endereco () {}	
	
	public Endereco (String rua, String bairro, String cidade, Integer numero, String cep) {
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.numero = numero;
		this.cep = cep;
	}

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
