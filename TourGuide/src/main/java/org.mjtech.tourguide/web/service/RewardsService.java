package org.mjtech.tourguide.web.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.mjtech.tourguide.model.Attraction;
import org.mjtech.tourguide.model.Location;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserReward;

/**
 * RewardsService interface structure the business logic
 * of rewards.
 */
public interface RewardsService {

  /**
   * getUserRewards. Method that get all user reward.
   *
   * @param user a user
   * @return userReward list
   */
  List<UserReward> getUserRewards(User user);

  /**
   * calculateRewards. Method that calculate
   * and add new userReward.
   *
   * @param user a user
   * @return void
   */
  CompletableFuture<?> calculateRewards(User user);

  /**
   * getAttractionDistance. Method that get
   * all attraction distance for given location.
   *
   * @param attractions an attraction
   * @param location    a location
   * @return map of key distance and value attraction
   */
  Map<Double, Object> getAttractionDistance(List<Attraction> attractions, Location location);

  /**
   * isWithinAttractionProximity. method that
   * check if given location is in attraction
   * proximity.
   *
   * @param attraction an attraction
   * @param location   a location
   * @return a boolean
   */
  boolean isWithinAttractionProximity(Attraction attraction, Location location);

  /**
   * getDistance. Methode that get distance
   * between two location.
   *
   * @param loc1 a location
   * @param loc2 a location
   * @return distance between to location
   */
  double getDistance(Location loc1, Location loc2);

  /**
   * getRewardPoints. Methode that get
   * a user reward point.
   *
   * @param attraction an attraction
   * @param user       a user
   * @return user reward point
   */
  CompletableFuture<Integer> getRewardPoints(Attraction attraction, User user);
}
