package org.mjtech.gpsutils.controller;

import lombok.extern.slf4j.Slf4j;
import org.mjtech.gpsutils.model.Attraction;
import org.mjtech.gpsutils.model.VisitedLocation;
import org.mjtech.gpsutils.service.GpsUtilsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class GpsUtilsController {
  private final GpsUtilsService gpsUtilsService;

  public GpsUtilsController(GpsUtilsService gpsUtilsService) {
    this.gpsUtilsService = gpsUtilsService;
  }

  @GetMapping("/gpsUtils/userLocation/{userId}")
  public ResponseEntity<VisitedLocation> getUserLocation(@PathVariable UUID userId) {
    log.debug("user location {}", gpsUtilsService.getUserLocation(userId));
    return ResponseEntity.ok(gpsUtilsService.getUserLocation(userId));
  }

  @GetMapping("/gpsUtils/attractions")
  public List<Attraction> getAttractions() {
    return gpsUtilsService.getAttractions();
  }
}
