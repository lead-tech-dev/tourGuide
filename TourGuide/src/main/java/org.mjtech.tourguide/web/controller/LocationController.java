package org.mjtech.tourguide.web.controller;

import com.jsoniter.output.JsonStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mjtech.tourguide.dto.Closest;
import org.mjtech.tourguide.model.VisitedLocation;
import org.mjtech.tourguide.web.service.LocationService;
import org.mjtech.tourguide.web.service.RewardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * LocationController. class that manage
 * request/response logic of location.
 */
@RestController
@Slf4j
public class LocationController {

  private Logger logger = LoggerFactory.getLogger(LocationController.class);
  private final LocationService locationService;

  private final RewardsService rewardsService;

  public LocationController(LocationService locationService,
                            RewardsService rewardsService) {
    this.locationService = locationService;
    this.rewardsService = rewardsService;
  }

  /**
   * getLocation. Method that get a user
   * location.
   *
   * @param userName a userName
   * @return user location
   */

  @SneakyThrows
  @RequestMapping("/getLocation")
  public String getLocation(@RequestParam String userName) throws InterruptedException {

    logger.info("getLocation request for user: {} : ", userName);

    VisitedLocation visitedLocation = locationService.getUserLocation(userName).get();

    logger.info("getLocation response:  {} for user:  {} : ", visitedLocation, userName);

    return JsonStream.serialize(visitedLocation.location);
  }

  /**
   * getNearbyAttractions. Method that get a user
   * five nearby attractions.
   *
   * @param userName a userName
   * @return five attractions
   */
  @RequestMapping("/getNearbyAttractions")
  public String getNearbyAttractions(@RequestParam String userName)
          throws InterruptedException, ExecutionException {

    logger.info("getNearbyAttractions request for user: {} : ", userName);

    VisitedLocation visitedLocation = locationService.getUserLocation(userName).get();

    List<Closest> nearByAttraction = locationService.getNearByAttractions(visitedLocation);

    logger.info("getNearbyAttractions response : {}, for user: {}  ",
            nearByAttraction, userName);

    return JsonStream.serialize(nearByAttraction);
  }

  /**
   * getAllCurrentLocations. Method that get all user
   * last visited location.
   *
   * @return map of user's last visited location
   */
  @RequestMapping("/getAllCurrentLocations")
  public String getAllCurrentLocations() {

    logger.info("getAllCurrentLocations request");

    Map<String, Object> allCurrentLocation = locationService.getAllCurrentLocation();

    logger.info("getAllCurrentLocations response: {}", allCurrentLocation);

    return JsonStream.serialize(locationService.getAllCurrentLocation());
  }


}
