package br.com.pfaa.quarkusstudy.domain.adapter;

import br.com.pfaa.quarkusstudy.domain.entity.Product;
import br.com.pfaa.quarkusstudy.domain.exception.EntityNotFoundException;
import br.com.pfaa.quarkusstudy.domain.port.driven.IProductRespositoryDrivenPort;
import br.com.pfaa.quarkusstudy.domain.port.driver.IProductDriverPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ProductAdapter implements IProductDriverPort {

    private final IProductRespositoryDrivenPort repositoryPort;

    @Override
    public Product findById(final Long productId) {
        return findByIdOrElseThrow(productId);
    }

    @Override
    public List<Product> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Product create(final Product product) {
        return repositoryPort.create(product);
    }

    @Override
    public void update(final Product product) {
        findByIdOrElseThrow(product.getId());
        repositoryPort.update(product);
    }

    @Override
    public void delete(final Long productId) {
        Boolean isDeleted = repositoryPort.delete(productId);
        if (!isDeleted) {
            new EntityNotFoundException(productId,
                    Product.class.getSimpleName());
        }
    }

    private Product findByIdOrElseThrow(final Long productId) {
        return repositoryPort.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(productId,
                        Product.class.getSimpleName()));
    }
}
