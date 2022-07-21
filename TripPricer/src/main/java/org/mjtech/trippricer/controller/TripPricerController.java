package org.mjtech.trippricer.controller;

import lombok.extern.slf4j.Slf4j;
import org.mjtech.trippricer.model.Provider;
import org.mjtech.trippricer.service.TripPricerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class TripPricerController {

  private final TripPricerService tripPricerService;

  public TripPricerController(TripPricerService tripPricerService) {
    this.tripPricerService = tripPricerService;
  }

  @GetMapping("/getTripPrice")
  public ResponseEntity<List<Provider>> getTripPrice(@RequestParam String apiKey, @RequestParam UUID attractionId, @RequestParam int adults, @RequestParam int children, @RequestParam int nightsStay,
                                                     @RequestParam int rewardsPoints){
    System.out.println(tripPricerService.getPrice(apiKey, attractionId, adults, children, nightsStay, rewardsPoints));
    return ResponseEntity.ok(tripPricerService.getPrice(apiKey, attractionId, adults, children, nightsStay, rewardsPoints));
  }

  @GetMapping("/getProvideName")
  public ResponseEntity<String> getProviderName(@RequestParam String apiKey, @RequestParam int adult){
    return ResponseEntity.ok(tripPricerService.getProviderName(apiKey, adult));
  }
}
