package br.com.pfaa.quarkusstudy.application.handler;

import br.com.pfaa.quarkusstudy.application.handler.response.ExceptionHandlerResponseDto;
import br.com.pfaa.quarkusstudy.domain.exception.ApplicationException;
import br.com.pfaa.quarkusstudy.domain.exception.EntityNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Optional;
import java.util.logging.Logger;

@Provider
public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationException> {
    private Logger logger = Logger.getLogger(ApplicationExceptionHandler.class.getName());

    @Override
    public Response toResponse(ApplicationException exception) {
        final ExceptionHandlerResponseDto exDto = ExceptionHandlerResponseDto.builder()
                .message(exception.getMessage())
                .build();

        logger.severe(exception.getMessage());

        Response.StatusType status = Response.Status.INTERNAL_SERVER_ERROR;
        if (exception instanceof EntityNotFoundException) {
            status = Response.Status.NOT_FOUND;
        }

        return Response.status(status).entity(exDto).build();
    }
}
