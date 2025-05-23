package org.serratec.avaliacao.infra;

import java.time.LocalDateTime;

public class ErroResposta {
	
	private LocalDateTime timestamp;
    private Integer status;
    private String erro;
    private String mensagem;
    private String caminho;

    public ErroResposta(LocalDateTime timestamp, Integer status, String erro, String mensagem, String caminho) {
        this.timestamp = timestamp;
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.caminho = caminho;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;}

    public Integer getStatus() {
        return status;}

    public String getErro() {
        return erro;}

    public String getMensagem() {
        return mensagem;}

    public String getCaminho() {
        return caminho;}
    
}
