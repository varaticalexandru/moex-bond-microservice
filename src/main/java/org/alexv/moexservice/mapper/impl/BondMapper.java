package org.alexv.moexservice.mapper.impl;

import org.alexv.moexservice.dto.MoexBondDto;
import org.alexv.moexservice.mapper.Mapper;
import org.alexv.moexservice.model.Bond;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BondMapper implements Mapper<Bond, MoexBondDto> {

    ModelMapper modelMapper;

    @Autowired
    public BondMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.typeMap(Bond.class, MoexBondDto.class)
                .addMapping(Bond::getName, MoexBondDto::setName)
                .addMapping(Bond::getTicker, MoexBondDto::setTicker);

        modelMapper.typeMap(MoexBondDto.class, Bond.class)
                .addMapping(MoexBondDto::getName, Bond::setName)
                .addMapping(MoexBondDto::getTicker, Bond::setTicker)
                .addMapping(MoexBondDto::getTicker, Bond::setFigi);

    }

    @Override
    public MoexBondDto mapTo(Bond bond) {
        return modelMapper.map(bond, MoexBondDto.class);
    }

    @Override
    public Bond mapFrom(MoexBondDto moexBondDto) {
        return modelMapper.map(moexBondDto, Bond.class);
    }

    @Override
    public List<MoexBondDto> mapTo(List<Bond> a) {
        return a
                .stream()
                .map(this::mapTo)
                .toList();
    }

    @Override
    public List<Bond> mapFrom(List<MoexBondDto> b) {
        return b
                .stream()
                .map(this::mapFrom)
                .toList();
    }
}
