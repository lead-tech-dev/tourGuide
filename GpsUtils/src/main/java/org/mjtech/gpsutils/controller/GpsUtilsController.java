package org.mjtech.gpsutils.controller;

import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.mjtech.gpsutils.model.Attraction;
import org.mjtech.gpsutils.model.VisitedLocation;
import org.mjtech.gpsutils.service.GpsUtilsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * GpsUtilsController. class that manage
 * request/response logic of gpsUtils.
 */
@RestController
@Slf4j
public class GpsUtilsController {

  private Logger logger = LoggerFactory.getLogger(GpsUtilsController.class);
  private final GpsUtilsService gpsUtilsService;

  public GpsUtilsController(GpsUtilsService gpsUtilsService) {
    this.gpsUtilsService = gpsUtilsService;
  }

  /**
   * getUserLocation. Methode that get user
   * visitedLocation.
   *
   * @param userId a userId
   * @return visitedLocation
   */
  @GetMapping("/gpsUtils/userLocation/{userId}")
  public ResponseEntity<VisitedLocation> getUserLocation(@PathVariable UUID userId) {

    logger.info("getUserLocation request for user: {} : ", userId);

    VisitedLocation visitedLocation = gpsUtilsService.getUserLocation(userId);

    logger.info("getUserLocation successful: {} : ", visitedLocation);

    return ResponseEntity.ok(visitedLocation);
  }

  /**
   * getAttractions. Method that get request
   * for attraction list.
   *
   * @return attraction list
   */
  @GetMapping("/gpsUtils/attractions")
  public List<Attraction> getAttractions() {

    logger.info("getAttractions request");

    List<Attraction> attractions = gpsUtilsService.getAttractions();

    logger.info("getAttractions successful: {} : ", attractions);

    return attractions;
  }
}
