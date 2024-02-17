package org.alexv.moexservice.service;

import org.alexv.moexservice.dto.BondsDto;
import org.alexv.moexservice.dto.BondsPricesDto;
import org.alexv.moexservice.dto.FigiesDto;
import org.alexv.moexservice.dto.TickersDto;

public interface BondService {
    BondsDto getBondsByTickers (TickersDto tickers);

    BondsPricesDto getBondsPricesByFigies(FigiesDto figies);
}
