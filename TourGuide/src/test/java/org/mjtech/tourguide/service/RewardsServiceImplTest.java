package org.mjtech.tourguide.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mjtech.tourguide.model.Attraction;
import org.mjtech.tourguide.model.VisitedLocation;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserPreferences;
import org.mjtech.tourguide.model.user.UserReward;
import org.mjtech.tourguide.web.service.LocationService;
import org.mjtech.tourguide.web.service.RewardsService;
import org.mjtech.tourguide.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.*;

@Tag("RewardsServiceImplTest")
@DisplayName("Rewards Service implement test logic")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RewardsServiceImplTest {

  @Autowired
  private RewardsService rewardsService;

  @Autowired
  private LocationService locationService;

  @Autowired
  private UserService userService;

  @Test
  @DisplayName("getUserRewards should return user rewards for given user")
  void getUserRewards_ShouldReturnUserReward_ForGivenUser() throws ExecutionException, InterruptedException {
    // GIVEN
    User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com", new UserPreferences(2, 5));
    Attraction attraction = locationService.getAttractions().get(0);
    int rewardPoints = rewardsService.getRewardPoints(attraction, user).get();
    VisitedLocation visitedLocation = new VisitedLocation(user.getUserId(), attraction, new Date());

    // WHEN
    user.addUserReward(new UserReward(visitedLocation, attraction, rewardPoints));

    // THEN
    assertTrue(rewardsService.getUserRewards(user).get(0).getRewardPoints() == rewardPoints);
    assertTrue(rewardsService.getUserRewards(user).get(0).getAttraction() == attraction);
    assertTrue(rewardsService.getUserRewards(user).get(0).getVisitedLocation() == visitedLocation);
  }

  @Test
  @DisplayName("calculateRewards should add new user reward for given user")
  void calculateRewards_ShouldAddNewUserReward_ForGivenUser() throws ExecutionException, InterruptedException {
    // GIVEN
    User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com", new UserPreferences(2, 5));
    Attraction attraction = locationService.getAttractions().get(0);

    user.addToVisitedLocations(new VisitedLocation(user.getUserId(), attraction, new Date()));

    // WHEN
    rewardsService.calculateRewards(user).get();

    // THEN
    assertTrue(user.getUserRewards().size() == 1);
  }

  @Test
  @DisplayName("getAttractionDistance should return  user attractions distances")
  void getAttractionDistance_ShouldReturnUserAttractionsDistances() throws ExecutionException, InterruptedException {
    // GIVEN
    User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com", new UserPreferences(2, 5));
    List<Attraction> attractions = locationService.getAttractions();
    VisitedLocation visitedLocation = new VisitedLocation(user.getUserId(), attractions.get(0), new Date());

    // WHEN
    Map<Double, Object> attractionsDistance = rewardsService.getAttractionDistance(attractions, visitedLocation.location);

    // THEN
    assertTrue(attractionsDistance.size() == 5);
  }

  @Test
  @DisplayName("isWithinAttractionProximity should return whether the user is nearby or not")
  void isWithinAttractionProximity_ShouldReturnWhetherTheUserIsNearbyOrNot() {
    // GIVEN
    Attraction attraction = locationService.getAttractions().get(0);

    // WHEN
    boolean result = rewardsService.isWithinAttractionProximity(attraction, attraction);

    // THEN
    assertTrue(result);
  }

  @Test
  @DisplayName("getRewardPoints should return user rewards points for given attraction and user")
  void getRewardPoints_ShouldReturnUserRewardsPoints_ForGivenAttractionAndUser() throws ExecutionException,
          InterruptedException {
    // GIVEN
    User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com", new UserPreferences(2, 5));
    Attraction attraction = locationService.getAttractions().get(0);

    // WHEN
    CompletableFuture<Integer> rewardPoints = rewardsService.getRewardPoints(attraction, user);

    // THEN
    assertTrue(rewardPoints.get() > 0);
  }

}