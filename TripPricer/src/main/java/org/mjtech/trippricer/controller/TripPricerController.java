package org.mjtech.trippricer.controller;

import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.mjtech.trippricer.model.Provider;
import org.mjtech.trippricer.service.TripPricerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TripPricerController. class that manage
 * request/response logic of TripPricer.
 */
@RestController
@Slf4j
public class TripPricerController {

  private Logger logger = LoggerFactory.getLogger(TripPricerController.class);
  private final TripPricerService tripPricerService;

  public TripPricerController(TripPricerService tripPricerService) {

    this.tripPricerService = tripPricerService;
  }

  /**
   * getTripPrice. Methode that get provider
   * list.
   *
   * @param apiKey an apiKey
   * @param attractionId an attractionId
   * @param adults an adults
   * @param children a children
   * @param nightsStay a nightsStay
   * @param rewardsPoints a rewardsPoints
   * @return Provider
   */
  @GetMapping("/getTripPrice")
  public ResponseEntity<List<Provider>> getTripPrice(
          @RequestParam String apiKey,
          @RequestParam UUID attractionId,
          @RequestParam int adults,
          @RequestParam int children,
          @RequestParam int nightsStay,
          @RequestParam int rewardsPoints) {

    logger.info("getTripPrice request for attractionId: {} : ", attractionId);

    List<Provider> providers = tripPricerService.getPrice(
            apiKey, attractionId, adults, children, nightsStay, rewardsPoints);

    logger.info("getTripPrice successful : {} : ", providers);

    return ResponseEntity.ok(providers);
  }

}
