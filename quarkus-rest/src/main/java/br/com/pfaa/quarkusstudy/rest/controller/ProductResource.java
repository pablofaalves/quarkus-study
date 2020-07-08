package br.com.pfaa.quarkusstudy.rest.controller;

import br.com.pfaa.quarkusstudy.domain.entity.Product;
import br.com.pfaa.quarkusstudy.domain.port.driver.IProductUseCase;
import br.com.pfaa.quarkusstudy.rest.dto.request.ProductCreationDto;
import br.com.pfaa.quarkusstudy.rest.dto.response.ProductResponseDto;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.stream.Collectors;

@Valid
@Path("/products/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    IProductUseCase productDriverPort;

    @Inject
    ModelMapper modelMapper;

    @GET
    public Response getProducts() {
        return Response.ok().entity(productDriverPort.findAll().stream()
                .map(entity -> modelMapper.map(entity, ProductResponseDto.class))
                .collect(Collectors.toList()))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") Long productId) {
        return Response.ok()
                .entity(modelMapper.map(productDriverPort.findById(productId),
                        ProductResponseDto.class))
                .build();
    }

    @POST
    public Response postProduct(@Valid ProductCreationDto productBody,
                                @Context UriInfo uriInfo) {

        Product product = productDriverPort.create(modelMapper.map(productBody, Product.class));

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(product.getId()));

        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    public Response putProduct(@PathParam("id") Long productId,
                               @Valid ProductCreationDto productBody) {
        Product product = modelMapper.map(productBody, Product.class);
        product.setId(productId);

        productDriverPort.update(product);

        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") Long productId) {
        productDriverPort.delete(productId);

        return Response.noContent().build();
    }
}