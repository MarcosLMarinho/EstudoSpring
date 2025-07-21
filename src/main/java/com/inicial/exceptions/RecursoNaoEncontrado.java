package com.inicial.exceptions;

public class RecursoNaoEncontrado extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RecursoNaoEncontrado(String mensagem) {
        super(mensagem);
    }

    public RecursoNaoEncontrado(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
    
}
