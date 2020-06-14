package br.com.pfaa.quarkusstudy.domain.port.driver;

import br.com.pfaa.quarkusstudy.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDriverPort {

    Product findById(Long productId);
    List<Product> findAll();
    Product create(Product product);
    void update(Product product);
    void delete(Long productId);
}
