package org.alexv.moexservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "corporate-bonds", url = "${moex.bonds.corporate.url}", configuration = FeignConfig.class)
public interface CorpBondClient {
    @GetMapping
    String getBondsFromMoex();
}
