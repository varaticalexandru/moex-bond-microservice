package org.alexv.moexservice.controller;

import lombok.AllArgsConstructor;
import org.alexv.moexservice.dto.BondsDto;
import org.alexv.moexservice.dto.BondsPricesDto;
import org.alexv.moexservice.dto.FigiesDto;
import org.alexv.moexservice.dto.TickersDto;
import org.alexv.moexservice.service.BondService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bonds")
@AllArgsConstructor
public class MoexBondController {

    private BondService bondService;
    @PostMapping("/byTickers")
    public ResponseEntity<BondsDto> getBondsByTickers (@RequestBody TickersDto tickers) {

        return new ResponseEntity<>(bondService.getBondsByTickers(tickers), HttpStatus.OK);
    }

    @PostMapping("/price/byFigies")
    public ResponseEntity<BondsPricesDto> getBondsPricesByFigies(@RequestBody FigiesDto figies) {

        return new ResponseEntity<>(bondService.getBondsPricesByFigies(figies), HttpStatus.OK);
    }
}
