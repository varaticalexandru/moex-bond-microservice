package org.alexv.moexservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alexv.moexservice.client.CorpBondClient;
import org.alexv.moexservice.client.GovBondClient;
import org.alexv.moexservice.dto.MoexBondDto;
import org.alexv.moexservice.exception.RequestsLimitException;
import org.alexv.moexservice.parser.Parser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class BondRepository {

    private final CorpBondClient corpBondClient;
    private final GovBondClient govBondClient;
    private final Parser parser;

    @Cacheable(value = "corps")
    public List<MoexBondDto> getCorpBonds() {
        log.info("Getting corporation bonds from Moex.");
        var result = corpBondClient.getBondsFromMoex();
        var bondList = parser.parse(result);

        if (bondList.isEmpty()) {
            log.error("Moex isn't asnwering for getting corporate bonds.");
            throw new RequestsLimitException("Moex isn't asnwering for getting corporate bonds.");
        }

        return bondList;
    }

    @Cacheable(value = "govs")
    public List<MoexBondDto> getGovBonds() {
        log.info("Getting government bonds from Moex.");
        var result = govBondClient.getBondsFromMoex();
        var bondList = parser.parse(result);

        if (bondList.isEmpty()) {
            log.error("Moex isn't asnwering for getting corporate bonds.");
            throw new RequestsLimitException("Moex isn't asnwering for getting corporate bonds.");
        }

        return bondList;
    }
}
