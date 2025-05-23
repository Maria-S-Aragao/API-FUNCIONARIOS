package org.serratec.avaliacao.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Funcionario {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(nullable=false, unique=true)
	private Long id;
	
	@Column (nullable=false)
	private String nome;
	
	@Embedded
	private Endereco endereco;
	
	public Funcionario () {}
	
	
	public Funcionario(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Long getId() {
		return id;}
	public void setId(Long id) {
		this.id = id;}

	public String getNome() {
		return nome;}
	public void setNome(String nome) {
		this.nome = nome;}

	public Endereco getEndereco() {
		return endereco;}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;}

}

