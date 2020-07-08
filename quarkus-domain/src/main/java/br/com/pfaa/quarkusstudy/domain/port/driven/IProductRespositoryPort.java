package br.com.pfaa.quarkusstudy.domain.port.driven;

import br.com.pfaa.quarkusstudy.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRespositoryPort {

    Optional<Product> findById(Long productId);
    List<Product> findAll();
    Product create(Product product);
    void update(Product product);
    Boolean delete(Long productId);
}
