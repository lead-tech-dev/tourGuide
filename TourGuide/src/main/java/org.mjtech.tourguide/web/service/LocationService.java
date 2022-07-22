package org.mjtech.tourguide.web.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.mjtech.tourguide.dto.Closest;
import org.mjtech.tourguide.model.Attraction;
import org.mjtech.tourguide.model.VisitedLocation;
import org.mjtech.tourguide.model.user.User;

/**
 * LocationService interface structure the business logic
 * of location.
 */
public interface LocationService {

  /**
   * getAttractions. Method that get all attractions from database.
   *
   * @return Attraction list
   */
  List<Attraction> getAttractions();

  /**
   * getNearByAttractions. Method that get
   * user's five nearby attraction.
   *
   * @param visitedLocation a visitedLocation
   * @return Closest a closest model
   * @throws ExecutionException   a ExecutionException
   * @throws InterruptedException a InterruptedException
   */
  List<Closest> getNearByAttractions(VisitedLocation visitedLocation)
          throws ExecutionException, InterruptedException;

  /**
   * getAllCurrentLocation. Method that get all
   * user's current location.
   *
   * @return map of key userId and value location
   */
  Map<String, Object> getAllCurrentLocation();

  /**
   * getUserLocation. Method that get user
   * location.
   *
   * @param userName a user name
   * @return visitedLocation
   * @throws InterruptedException a InterruptedException
   * @throws ExecutionException   a ExecutionException
   */
  CompletableFuture<VisitedLocation> getUserLocation(String userName)
          throws InterruptedException, ExecutionException;

  /**
   * trackUserLocation. Method that track
   * user location.
   *
   * @param user a user
   * @return last visitedLocation
   * @throws InterruptedException a InterruptedException
   * @throws ExecutionException   a ExecutionException
   */
  CompletableFuture<VisitedLocation> trackUserLocation(User user)
          throws ExecutionException, InterruptedException;
}
