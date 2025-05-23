package org.serratec.avaliacao.infra;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

	 //QUANDO A FALHAS EM VALIDAÇÕES DO TIPO @VALID, HTTP 400
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            erros.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(erros);
    }

	 //QUANDO A ENTIDADE NÃO FOI ENCONTRADA PELO ID, HTTP 404
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroResposta> handleEntidadeNaoEncontrada(EntityNotFoundException ex, HttpServletRequest request) {
        ErroResposta erro = new ErroResposta(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Não foi encontrado nenhum resultado com o ID informado.",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    //QUANDO O JSON ESTÁ MAL FORMATADO, HTTP 400
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResposta> handleJsonInvalido(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ErroResposta erro = new ErroResposta(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Requisição inválida",
                "Formato JSON inválido ou campo mal preenchido.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    //QUANDO APARECE QUALQUER ERRO INESPERADO
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> handleErroInterno(Exception ex, HttpServletRequest request) {
        ErroResposta erro = new ErroResposta(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "ERRO DESCONHECIDO!! Brincadeira, deve ser o servidor, ao menos, eu espero que sim.",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
    
    /*BASICAMENTE, USO SEMPRE QUE EU QUISER IMPEDIR UMA AÇÃO
     * UTILIZANDO: throw new BusinessException("QUALQUER MENSAGEM.");*/
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErroResposta> handleBusinessException(BusinessException ex, HttpServletRequest request) {
        ErroResposta erro = new ErroResposta(
            LocalDateTime.now(),
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "Violação das minhas regras de negócio, repense!",
            ex.getMessage(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }
}
