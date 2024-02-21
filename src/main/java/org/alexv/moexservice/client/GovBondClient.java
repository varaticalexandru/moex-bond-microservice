package org.alexv.moexservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "gov-bonds", url = "${moex.bonds.government.url}", configuration = FeignConfig.class)
@CircuitBreaker(name = "bond-breaker")
@Retry(name = "bond-retry")
public interface GovBondClient {
    @GetMapping
    String getBondsFromMoex();
}
