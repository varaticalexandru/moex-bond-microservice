package org.alexv.moexservice.mapper.impl;

import lombok.AllArgsConstructor;
import org.alexv.moexservice.dto.BondPriceDto;
import org.alexv.moexservice.dto.MoexBondDto;
import org.alexv.moexservice.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BondPriceMapper implements Mapper<MoexBondDto, BondPriceDto> {

    ModelMapper modelMapper;
    @Autowired
    public BondPriceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.typeMap(MoexBondDto.class, BondPriceDto.class)
                .addMapping(MoexBondDto::getTicker, BondPriceDto::setFigi)
                .addMapping(MoexBondDto::getPrice, BondPriceDto::setPrice);
    }

    @Override
    public BondPriceDto mapTo(MoexBondDto moexBondDto) {
        return modelMapper.map(moexBondDto, BondPriceDto.class);
    }

    @Override
    public MoexBondDto mapFrom(BondPriceDto bondPriceDto) {
        return modelMapper.map(bondPriceDto, MoexBondDto.class);
    }

    @Override
    public List<BondPriceDto> mapTo(List<MoexBondDto> a) {
        return a.
                stream().
                map(this::mapTo)
                .toList();
    }

    @Override
    public List<MoexBondDto> mapFrom(List<BondPriceDto> b) {
        return b
                .stream()
                .map(this::mapFrom)
                .toList();
    }
}
