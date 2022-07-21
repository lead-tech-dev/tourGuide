package org.mjtech.tourguide.web.service;

import org.mjtech.tourguide.dto.UserDto;
import org.mjtech.tourguide.model.Closest;
import org.mjtech.tourguide.model.VisitedLocation;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.tracker.Tracker;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

  Tracker tracker = new Tracker();
  User getUser(String userName);

  User getUserById(UUID userId);

  List<User> getAllUsers();

  User addUser(UserDto userDto);

  User updateUser(UserDto userDto);

  void deleteUser(UserDto userDto);

}
