package br.com.pfaa.quarkusstudy.rest.controller;

import br.com.pfaa.quarkusstudy.domain.entity.Product;
import br.com.pfaa.quarkusstudy.domain.port.driver.IProductDriverPort;
import br.com.pfaa.quarkusstudy.rest.dto.request.ProductCreationDto;
import br.com.pfaa.quarkusstudy.rest.dto.response.ProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;

@Path("/products/v1")
public class ProductResource {

    @Inject
    IProductDriverPort productDriverPort;

    @Inject
    ModelMapper modelMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        return Response.ok().entity(productDriverPort.findAll().stream()
                .map(entity -> modelMapper.map(entity, ProductResponseDto.class))
                .collect(Collectors.toList())).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductResponseDto getProduct(@PathParam("id") Long productId) {
        return modelMapper.map(productDriverPort.findById(productId),
                ProductResponseDto.class);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postProduct(@Valid ProductCreationDto productBody,
                                @Context UriInfo uriInfo ) {

        Product product = productDriverPort.create(modelMapper.map(productBody, Product.class));

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(product.getId()));

        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Valid
    public Response putProduct(@PathParam("id") Long productId,
                                         ProductCreationDto productBody) {
        Product product = modelMapper.map(productBody, Product.class);
        product.setId(productId);

        productDriverPort.update(product);

        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") Long productId) {
        productDriverPort.delete(productId);

        return Response.noContent().build();
    }
}