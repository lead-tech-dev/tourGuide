package org.mjtech.tourguide.web.controller;

import com.jsoniter.output.JsonStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mjtech.tourguide.model.VisitedLocation;
import org.mjtech.tourguide.web.service.LocationService;
import org.mjtech.tourguide.web.service.RewardsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class LocationController {
  private final LocationService locationService;

  private final RewardsService rewardsService;

  public LocationController(LocationService locationService, RewardsService rewardsService) {
    this.locationService = locationService;
    this.rewardsService = rewardsService;
  }

  @SneakyThrows
  @RequestMapping("/getLocation")
  public String getLocation(@RequestParam String userName) throws InterruptedException {
    CompletableFuture<VisitedLocation> visitedLocation = locationService.getUserLocation(userName);
    return JsonStream.serialize(visitedLocation.get().location);
  }

  @RequestMapping("/getNearbyAttractions")
  public String getNearbyAttractions(@RequestParam String userName) throws InterruptedException, ExecutionException {
    CompletableFuture<VisitedLocation> visitedLocation = locationService.getUserLocation(userName);
    return JsonStream.serialize(locationService.getNearByAttractions(visitedLocation.get()));
  }


  @RequestMapping("/getAllCurrentLocations")
  public String getAllCurrentLocations() {
    return JsonStream.serialize(locationService.getAllCurrentLocation());
  }


}
