package org.mjtech.tourguide.web.service;

import java.util.List;
import java.util.UUID;
import org.mjtech.tourguide.dto.UserDto;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.tracker.Tracker;

/**
 * UserService interface structure the business logic
 * of user.
 */
public interface UserService {

  /**
   * a tracker object.
   */
  Tracker tracker = new Tracker();

  /**
   * getUser. Method that get a user
   * by username.
   *
   * @param userName a username
   * @return a user
   */
  User getUser(String userName);

  /**
   * getUser. Method that get a user
   * by userId.
   *
   * @param userId a user id
   * @return a user
   */
  User getUserById(UUID userId);

  /**
   * getAllUsers. Method that get all users from database.
   *
   * @return user list
   */
  List<User> getAllUsers();

  /**
   * addUser. Method that add new user.
   *
   * @param userDto a userDto
   * @return user
   */
  User addUser(UserDto userDto);

  /**
   * updateUser. Method that update exists user.
   *
   * @param userDto a userDto
   * @return user
   */
  User updateUser(UserDto userDto);

  /**
   * deleteUser. Method that delete exist user.
   *
   * @param userDto a userDto
   */
  void deleteUser(UserDto userDto);
}
