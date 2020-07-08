package br.com.pfaa.quarkusstudy.domain.exception;

public class EntityNotFoundException extends ApplicationException {

    public EntityNotFoundException(Long entityId, String entityName) {
        super(String.format("%s with ID %d not found", entityName, entityId));
    }
}
