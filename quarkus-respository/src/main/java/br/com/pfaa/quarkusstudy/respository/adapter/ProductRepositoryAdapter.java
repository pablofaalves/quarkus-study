package br.com.pfaa.quarkusstudy.respository.adapter;

import br.com.pfaa.quarkusstudy.domain.entity.Product;
import br.com.pfaa.quarkusstudy.domain.port.driven.IProductRespositoryPort;
import br.com.pfaa.quarkusstudy.respository.entity.ProductPanacheEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ApplicationScoped
public class ProductRepositoryAdapter implements IProductRespositoryPort {

    private final ModelMapper modelMapper;

    @Override
    public Optional<Product> findById(Long productId) {
        Optional<ProductPanacheEntity> optProductEntity = ProductPanacheEntity.findByIdOptional(productId);
        return optProductEntity.map(entity -> this.map(entity, Product.class));
    }

    @Override
    public List<Product> findAll() {
        return ProductPanacheEntity.findAll()
                .list()
                .parallelStream()
                .map(entity -> this.map(entity, Product.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Product create(Product product) {
        ProductPanacheEntity entity = this.map(product, ProductPanacheEntity.class);
        entity.persistAndFlush();

        return this.map(entity, Product.class);
    }

    @Override
    @Transactional
    public void update(Product product) {
        Optional<ProductPanacheEntity> dbProduct = ProductPanacheEntity.findByIdOptional(product.getId());
        dbProduct.ifPresent(entity -> {
            entity.setName(product.getName());
            entity.setPrice(product.getPrice());
        });
    }

    @Override
    @Transactional
    public Boolean delete(Long productId) {
        return ProductPanacheEntity.deleteById(productId);
    }

    private <T> T map(Object source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
