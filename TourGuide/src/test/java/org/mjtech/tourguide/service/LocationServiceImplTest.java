package org.mjtech.tourguide.service;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mjtech.tourguide.helper.InternalTestHelper;
import org.mjtech.tourguide.model.Attraction;
import org.mjtech.tourguide.model.Closest;
import org.mjtech.tourguide.model.VisitedLocation;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserPreferences;
import org.mjtech.tourguide.repository.LocationRepository;
import org.mjtech.tourguide.repository.RewardsRepository;
import org.mjtech.tourguide.repository.UserRepository;
import org.mjtech.tourguide.utility.ConvertTo;
import org.mjtech.tourguide.web.service.LocationService;
import org.mjtech.tourguide.web.service.UserService;
import org.mjtech.tourguide.web.service.impl.LocationServiceImpl;
import org.mjtech.tourguide.web.service.impl.RewardsServiceImpl;
import org.mjtech.tourguide.web.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tag("LocationServiceImplTest")
@DisplayName("Location Service implement test logic")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class LocationServiceImplTest {

  @Autowired
  private LocationService locationService;

  @Autowired
  private UserService userService;

  @BeforeEach
  public void setUp() {
  }

  @Test
  @DisplayName("getAttractions should return attractions list")
  void getAttractions_ShouldReturnAttractionList() {
    // GIVEN
    // WHEN
    List<Attraction> results = locationService.getAttractions();

    // THEN
    assertTrue(results.size() > 0);
  }

  @Test
  @DisplayName("getNearByAttractions should return user five close attractions for given username")
  void getNearByAttractions_ShouldReturnUserFiveCloseAttractions_ForGivenUsername() throws ExecutionException,
          InterruptedException {
    // GIVEN
    InternalTestHelper.setInternalUserNumber(0);
   // UserServiceImpl userService = new UserServiceImpl();
    User user = userService.addUser(ConvertTo.convertToUserDto(new User(
            UUID.randomUUID(),
            "eric",
            "000",
            "eric@tourGuide.com",
            new UserPreferences(2, 5))));


    CompletableFuture<VisitedLocation> visitedLocation = locationService.trackUserLocation(user);

    // WHEN
    List<Closest> attractions = locationService.getNearByAttractions(visitedLocation.get());
    userService.tracker.stopTracking();

    // THEN
    assertEquals(5, attractions.size());
  }

  @Test
  @DisplayName("getAllCurrentLocation should return users location list")
  void getAllCurrentLocation_ShouldReturnUsersLocationList() throws ExecutionException, InterruptedException {
    // GIVEN
    UUID userId = UUID.randomUUID();
    User user = userService.addUser(ConvertTo.convertToUserDto(new User(
            userId,
            "jon",
            "000",
            "jon@tourGuide.com",
            new UserPreferences(2, 5))));

    locationService.trackUserLocation(user).get();

    // WHEN
    Map<String, Object> result = locationService.getAllCurrentLocation();
    userService.tracker.stopTracking();

    // THEN
    assertTrue(result.size() > 0);
    assertThat(result, IsMapContaining.hasEntry(String.valueOf(userId), user.getLastVisitedLocation().location));
  }

  @Test
  @DisplayName("getLocation should return user location data")
  void getUserLocation() throws ExecutionException, InterruptedException {
    // GIVEN
    User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com", new UserPreferences(2, 5));

    // WHEN
    CompletableFuture<VisitedLocation> visitedLocation = locationService.trackUserLocation(user);
    userService.tracker.stopTracking();

    // THEN
    assertTrue(visitedLocation.join().location.equals(user.getLastVisitedLocation().location));
  }

}