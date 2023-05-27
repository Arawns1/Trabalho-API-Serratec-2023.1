package com.residencia.trabalho_final.exception;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(NoSuchElementException.class)
    ProblemDetail handleNoSuchElementException(NoSuchElementException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        
        problemDetail.setTitle("Recurso Não Encontrado");
        problemDetail.setType(URI.create("https://api.trabalho_final.com/errors/not-found"));
        return problemDetail;
    }
	@ExceptionHandler(BadRequestException.class)
    ProblemDetail handleBadRequestException(BadRequestException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        
        problemDetail.setTitle("Bad request");
        problemDetail.setType(URI.create("https://api.trabalho_final.com/errors/bad-request"));
        return problemDetail;
    }
	
	@ExceptionHandler(ProdutoNotFoundException.class)
    ProblemDetail handleBookmarkNotFoundException(ProdutoNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Produto Não Encontrado");
        problemDetail.setType(URI.create("https://api.trabalho_final.com/errors/not-found"));
        return problemDetail;
    }
	@ExceptionHandler(ProdutoDescricaoDuplicadaException.class)
	ProblemDetail handleBookmarkBadRequestException(ProdutoDescricaoDuplicadaException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
		problemDetail.setTitle("Descrição ja existe");
		problemDetail.setType(URI.create("https://api.trabalho_final.com/errors/bad-request"));
		return problemDetail;
	}
	@ExceptionHandler(ClienteCpfDuplicadoException.class)
	ProblemDetail handleBookmarkBadRequestException(ClienteCpfDuplicadoException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
		problemDetail.setTitle("Cpf ja existe");
		problemDetail.setType(URI.create("https://api.trabalho_final.com/errors/bad-request"));
		return problemDetail;
	}
	@ExceptionHandler(ClienteNotFoundException.class)
    ProblemDetail handleBookmarkNotFoundException(ClienteNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Cliente nao encontrado");
        problemDetail.setType(URI.create("https://api.trabalho_final.com/errors/not-found"));
        return problemDetail;
    }
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, 
            HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ResponseEntity<Object> response = super.handleExceptionInternal(ex, body, headers, statusCode, request);

        if (response.getBody() instanceof ProblemDetail problemDetailBody) {
            problemDetailBody.setProperty("message", ex.getMessage());
            if (ex instanceof MethodArgumentNotValidException subEx) {
                BindingResult result = subEx.getBindingResult();
                problemDetailBody.setTitle("Erro na requisição");
                problemDetailBody.setDetail("Ocorreu um erro ao processar a Requisição");
                problemDetailBody.setProperty("message", "Validation failed for object='" + result.getObjectName());
                
                problemDetailBody.setProperty("errors", result.getAllErrors().get(0).getDefaultMessage());
            }
        }
        return response;
    }
}
	
	