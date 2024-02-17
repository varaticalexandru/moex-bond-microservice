package org.alexv.moexservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "gov-bonds", url = "${moex.bonds.government.url}", configuration = FeignConfig.class)
public interface GovBondClient {
    @GetMapping
    String getBondsFromMoex();
}
