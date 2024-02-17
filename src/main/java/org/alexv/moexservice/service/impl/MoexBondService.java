package org.alexv.moexservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alexv.moexservice.dto.*;
import org.alexv.moexservice.exception.BondNotFoundException;
import org.alexv.moexservice.mapper.Mapper;
import org.alexv.moexservice.model.Bond;
import org.alexv.moexservice.service.BondService;
import org.alexv.moexservice.utils.Utilities;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MoexBondService implements BondService {

    private final Mapper<Bond, MoexBondDto> bondMapper;
    private final Mapper<MoexBondDto, BondPriceDto> bondPriceMapper;
    private final BondRepository bondRepository;
    private final CacheManager cacheManager;

    @Override
    public BondsDto getBondsByTickers(TickersDto tickersDto) {
        List<MoexBondDto> moexBonds = new ArrayList<>();
        moexBonds.addAll(bondRepository.getCorpBonds());
        moexBonds.addAll(bondRepository.getGovBonds());


        var tickers = new ArrayList<>(tickersDto.getTickers());
        tickers.removeAll(
                moexBonds.stream().map(b -> b.getTicker()).toList()
        );

        if (!tickers.isEmpty()) {
            log.error("Bonds {} not found.", tickers);
            throw new BondNotFoundException(String.format("Bonds %s not found.", tickers));
        }

        List<Bond> bonds = moexBonds
                .stream()
                .filter(b -> tickersDto.getTickers().contains(b.getTicker()))
                .map(bondMapper::mapFrom)
                .map(b -> {
                    b.setCurrency(Utilities.currency());
                    b.setSource(Utilities.source());
                    b.setType(Utilities.securityType());

                    return b;
                })
                .toList();

        return BondsDto.builder()
                .bonds(bonds)
                .build();
    }

    @Override
    public BondsPricesDto getBondsPricesByFigies(FigiesDto figiesDto) {
        List<MoexBondDto> moexBonds = new ArrayList<>();
        moexBonds.addAll(bondRepository.getCorpBonds());
        moexBonds.addAll(bondRepository.getGovBonds());

        List<String> figies = new ArrayList<>(figiesDto.getFigies());
        figies.removeAll(
                moexBonds.stream().map(MoexBondDto::getTicker).toList()
        );

        if (!figies.isEmpty()) {
            log.error("Bonds {} not found.", figies);
            throw new BondNotFoundException(String.format("Bonds %s not found.", figies));
        }

        var bondsPrices = moexBonds
                .stream()
                .filter(b -> figiesDto.getFigies().contains(b.getTicker()))
                .map(bondPriceMapper::mapTo)
                .map(bondPrice -> {
                    bondPrice.setPrice(bondPrice.getPrice() * 10);

                    return bondPrice;
                })
                .toList();

        return BondsPricesDto.builder()
                .prices(bondsPrices)
                .build();
    }
}
