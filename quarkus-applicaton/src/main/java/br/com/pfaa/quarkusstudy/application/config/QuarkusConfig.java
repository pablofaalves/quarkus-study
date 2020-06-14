package br.com.pfaa.quarkusstudy.application.config;

import br.com.pfaa.quarkusstudy.domain.adapter.ProductAdapter;
import br.com.pfaa.quarkusstudy.domain.port.driven.IProductRespositoryDrivenPort;
import br.com.pfaa.quarkusstudy.domain.port.driver.IProductDriverPort;
import io.quarkus.arc.DefaultBean;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
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
    public IProductDriverPort productDriverPort(IProductRespositoryDrivenPort respositoryDrivenPort) {
        return new ProductAdapter(respositoryDrivenPort);
    }
}
