package br.com.pfaa.quarkusstudy.application.config;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

public class ModelMapperConfig {
    private final @Getter ModelMapper modelMapper;

    public ModelMapperConfig() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldAccessLevel(
                Configuration.AccessLevel.PRIVATE);
    }
}
