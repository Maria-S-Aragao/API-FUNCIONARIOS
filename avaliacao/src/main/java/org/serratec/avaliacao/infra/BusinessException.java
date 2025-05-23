package org.serratec.avaliacao.infra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**********************************************************************************************
 * BASICAMENTE, É UMA CLASSE QUE EXTENDS A CLASSE RUNTIMEEXCEPTION, QUE É EXTREMAMENTE ÚTIL   *
 * EM ERROS DE NEGÓCIOS, QUE OCORREM NAS FUNÇÕES DO SERVICE, OU SEJA, QUALQUER ERRO QUE       *
 * OCORRE DURANTE A EXECUÇÃO DO PROGRAMA, ENTÃO QUANDO A EXCEÇÃO É ATIVADA, ELA NATURALMENTE  *
 * INTERROMPE O FLUXO NORMAL.           													  *
 **********************************************************************************************/

/**********************************************************************************************
 * MAIS UMA EXPLICAÇÃO INTERESSANTE, O QUE É @RESPONSESTATUS? NA REALIDADE, O QUE ELE FAZ,    *
 * ELE DIZ QUAL O STATUS HTTP DEVE RETORNAR QUANDO A EXECUÇÃO PERSONALIZADA FOR EXECUTADA.    *
 * NESTE CASO É A 422(UNPROCESSABLE_ENTITY), QUE INFORMA QUE O SERVIDOR ENTENDEU A REQUISIÇÃO,*
 * ENTRETANTO NÃO PODE EXECUTAR PORQUE VIOLA A REGRA DE NEGÓCIOS, OU QUANDO UM OBJETO NÃO É   *
 * ENCONTRADO.							  													  *
 **********************************************************************************************/

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends RuntimeException {
	
	/*BASICAMENTE BOA PRÁTICA E PORQUE FICOU AMARELINHO, EU PODERIA IGNORAR, MAS TEM SUA 
	 *UTILIDADE, DIZ QUAL A VERSÃO DA CLASSE, ELE APARENTEMENTE MUDA A VERSÃO AUTOMATICAMENTE?!
	 *NÃO TENHO MUITA CERTEZA, MAS É ISSO. */
	private static final long serialVersionUID = 1L;

	public BusinessException(String mensagem) {
        super(mensagem);
    }
}
