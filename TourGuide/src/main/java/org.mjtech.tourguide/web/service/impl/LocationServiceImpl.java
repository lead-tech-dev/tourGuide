package org.mjtech.tourguide.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.mjtech.tourguide.dto.Closest;
import org.mjtech.tourguide.model.Attraction;
import org.mjtech.tourguide.model.Location;
import org.mjtech.tourguide.model.VisitedLocation;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.repository.LocationRepository;
import org.mjtech.tourguide.repository.UserRepository;
import org.mjtech.tourguide.web.service.LocationService;
import org.mjtech.tourguide.web.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * LocationServiceImpl. class that implement
 * location business logic
 */
@Service
@Slf4j
public class LocationServiceImpl implements LocationService {
  @Autowired
  private RewardsService rewardsService;

  @Autowired
  private LocationRepository locationProxy;

  @Autowired
  private UserRepository userRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Attraction> getAttractions() {
    return locationProxy.getAttractions();
  }

  /*@Override
  public CompletableFuture<?> addUserLocation(User user) {
    ExecutorService executorService = Executors.newFixedThreadPool(100);
    return CompletableFuture.supplyAsync(() -> getUserLocation(user.getUserId()), executorService)
            .thenAccept(user::addToVisitedLocations)
            .thenRun(() -> rewardsService.calculateRewards(user));

  }*/

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Closest> getNearByAttractions(VisitedLocation visitedLocation)
          throws ExecutionException,
          InterruptedException {
    List<Closest> nearbyAttractions = new ArrayList<>();
    List<Attraction> attractions = locationProxy.getAttractions();
    Map<Double, Object> distances = rewardsService.getAttractionDistance(
            attractions, visitedLocation.location);

    for (Map.Entry<Double, Object> entry : distances.entrySet()) {
      Attraction attraction = (Attraction) entry.getValue();
      int rewardPoint =
              rewardsService.getRewardPoints(attraction,
                      userRepository.findById(visitedLocation.userId)).get();

      nearbyAttractions.add(
              new Closest(attraction.attractionName,
                      new Location(attraction.latitude, attraction.longitude),
                      visitedLocation.location,
                      entry.getKey(),
                      rewardPoint
              )
      );
    }

    return nearbyAttractions;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<String, Object> getAllCurrentLocation() {
    Map<String, Object> data = new HashMap<>();
    for (User user : userRepository.findAll()) {
      data.put(String.valueOf(user.getUserId()),
              user.getLastVisitedLocation().location);
    }
    return data;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CompletableFuture<VisitedLocation> getUserLocation(String userName)
          throws ExecutionException,
          InterruptedException {

    User user = userRepository.findByUsername(userName);

    return (user.getVisitedLocations().size() > 0)
            ?
            CompletableFuture.completedFuture(user.getLastVisitedLocation())
            :
            trackUserLocation(user);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Async
  public CompletableFuture<VisitedLocation> trackUserLocation(User user) {

    VisitedLocation location = locationProxy.getUserLocation(user.getUserId());

    user.addToVisitedLocations(location);

    rewardsService.calculateRewards(user);

    return CompletableFuture.completedFuture(location);
  }


}

