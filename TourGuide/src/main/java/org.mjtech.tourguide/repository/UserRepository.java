package org.mjtech.tourguide.repository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;
import org.mjtech.tourguide.helper.InternalTestHelper;
import org.mjtech.tourguide.model.Location;
import org.mjtech.tourguide.model.VisitedLocation;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserPreferences;
import org.mjtech.tourguide.tracker.Tracker;
import org.mjtech.tourguide.web.exception.NotFoundException;
import org.mjtech.tourguide.web.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * UserRepository. class that manage
 * user data.
 */
@Component
public class UserRepository {
  private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  public final Tracker tracker;


  boolean testMode = true;

  /**
   * UserRepository. Constructor of UserRepository
   * class.
   */
  public UserRepository() {
    if (testMode) {
      logger.info("TestMode enabled");
      logger.debug("Initializing users");
      initializeInternalUsers();
      logger.debug("Finished initializing users");
    }
    tracker = new Tracker();

    addShutDownHook();
  }

  private void addShutDownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        tracker.stopTracking();
      }
    });
  }

  /**
   * findByUsername. method that get user
   * by username.
   *
   * @param username a username
   * @return User
   */
  public User findByUsername(String username) {
    return internalUserMap.get(username);
  }

  /**
   * findByUserId. method that get user
   * by userId.
   *
   * @param userId a userId
   * @return User
   */
  public User findById(UUID userId) {
    User exists = null;
    for (User user : internalUserMap.values()) {
      if (Objects.equals(user.getUserId(), userId)) {
        exists = user;
      }
    }

    if (exists == null) {

      logger.error("NotFoundException: User doesn't exist");

      throw new NotFoundException("User doesn't exists!");
    }

    return exists;
  }

  /**
   * findAll. Method that get user list.
   *
   * @return user list
   */
  public List<User> findAll() {
    return new ArrayList<>(internalUserMap.values());
  }

  /**
   * save. Method that save new user.
   *
   * @param user a user
   * @return saved user
   */
  public User save(User user) {

    internalUserMap.put(user.getUsername(), user);

    return internalUserMap.get(user.getUsername());
  }

  /**
   * remove. Method that remove exists user.
   *
   * @param user a user
   */
  public void remove(User user) {
    internalUserMap.remove(user.getUsername());
  }


  /**********************************************************************************
   *
   * Methods Below: For Internal Testing.
   *
   **********************************************************************************/

  // Database connection will be used for external users,
  // but for testing purposes internal users are provided and
  // stored in memory
  private final Map<String, User> internalUserMap = new HashMap<>();

  private void initializeInternalUsers() {
    IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {
      String userName = "internalUser" + i;
      String phone = "000";
      String email = userName + "@tourGuide.com";
      User user = new User(UUID.randomUUID(),
              userName, phone, email, new UserPreferences(getRandomNumber(1, 10),
              getRandomNumber(1, 10)));
      generateUserLocationHistory(user);

      internalUserMap.put(userName, user);
    });
    logger.debug("Created " + InternalTestHelper.getInternalUserNumber()
            +
            " internal test users.");
  }

  private void generateUserLocationHistory(User user) {
    IntStream.range(0, 3).forEach(i -> {
      user.addToVisitedLocations(
              new VisitedLocation(user.getUserId(),
                      new Location(generateRandomLatitude(),
              generateRandomLongitude()), getRandomTime()));
    });
  }

  private double generateRandomLongitude() {
    double leftLimit = -180;
    double rightLimit = 180;
    return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
  }

  private double generateRandomLatitude() {
    double leftLimit = -85.05112878;
    double rightLimit = 85.05112878;
    return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
  }

  private Date getRandomTime() {
    LocalDateTime localDateTime = LocalDateTime.now().minusDays(
            new Random().nextInt(30));
    return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
  }

  private int getRandomNumber(int min, int max) {

    if (min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
    }

    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
  }

  /**
   * checkExistUser. Method that check if user
   * exists or not.
   *
   * @param username a username
   * @return boolean
   */
  public boolean checkExistUser(String username) {
    boolean exist = false;
    User user = internalUserMap.get(username);

    if (user != null) {
      exist = true;
    }

    return exist;
  }

}
