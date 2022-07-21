package org.mjtech.tourguide.web.service;

import org.mjtech.tourguide.model.Attraction;
import org.mjtech.tourguide.model.Location;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserReward;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface RewardsService {

  List<UserReward> getUserRewards(User user);
  CompletableFuture<?>  calculateRewards(User user);

  Map<Double, Object> getAttractionDistance(List<Attraction> attractions, Location location);

  boolean isWithinAttractionProximity(Attraction attraction, Location location);

  double getDistance(Location loc1, Location loc2);

  CompletableFuture<Integer> getRewardPoints(Attraction attraction, User user);
}
