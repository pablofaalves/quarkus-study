package br.com.pfaa.quarkusstudy.application.config;

import br.com.pfaa.quarkusstudy.domain.adapter.ProductAdapter;
import br.com.pfaa.quarkusstudy.domain.port.driven.IProductRespositoryPort;
import br.com.pfaa.quarkusstudy.domain.port.driver.IProductUseCase;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.transaction.Transactional;


@Dependent
public class QuarkusConfig {

    @Produces
    @ApplicationScoped
    public ModelMapper modelMapper() {
        return new ModelMapperConfig().getModelMapper();
    }

    @Produces
    @ApplicationScoped
    @Transactional
    public IProductUseCase productDriverPort(IProductRespositoryPort respositoryDrivenPort) {
        return new ProductAdapter(respositoryDrivenPort);
    }
}
