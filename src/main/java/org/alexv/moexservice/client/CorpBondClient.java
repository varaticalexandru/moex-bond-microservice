package org.alexv.moexservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "corporate-bonds", url = "${moex.bonds.corporate.url}", configuration = FeignConfig.class)
@CircuitBreaker(name = "bond-breaker")
@Retry(name = "bond-retry")
public interface CorpBondClient {
    @GetMapping
    String getBondsFromMoex();
}
