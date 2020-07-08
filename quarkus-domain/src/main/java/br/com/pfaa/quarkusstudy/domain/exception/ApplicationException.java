package br.com.pfaa.quarkusstudy.domain.exception;


public abstract class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }
}
