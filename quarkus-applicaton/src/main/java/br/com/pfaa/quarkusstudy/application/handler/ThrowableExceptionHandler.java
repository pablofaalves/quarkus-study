package br.com.pfaa.quarkusstudy.application.handler;

import br.com.pfaa.quarkusstudy.application.handler.response.ExceptionHandlerResponseDto;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.PersistenceException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Optional;
import java.util.logging.Logger;

@Provider
public class ThrowableExceptionHandler implements ExceptionMapper<Throwable> {
    private Logger logger = Logger.getLogger(ThrowableExceptionHandler.class.getName());


    @Override
    public Response toResponse(Throwable exception) {

        final ExceptionHandlerResponseDto exDto = ExceptionHandlerResponseDto.builder()
                .message(exception.getMessage())
                .build();

        logger.severe(exception.getMessage());

        Response.StatusType status = Response.Status.INTERNAL_SERVER_ERROR;

        if (exception instanceof PersistenceException) {
            if (ConstraintViolationException.class.isInstance(exception.getCause())) {
                status = Response.Status.BAD_REQUEST;
            }
        }
        else if (exception instanceof NotAllowedException) {
            status = Response.Status.METHOD_NOT_ALLOWED;
        }

        return Response.status(status).entity(exDto).build();
    }
}
