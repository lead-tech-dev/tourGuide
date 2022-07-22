package org.mjtech.tourguide.web.service.impl;

import java.util.List;
import java.util.UUID;
import org.mjtech.tourguide.dto.UserDto;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserPreferences;
import org.mjtech.tourguide.repository.UserRepository;
import org.mjtech.tourguide.web.exception.BadRequestException;
import org.mjtech.tourguide.web.exception.NotFoundException;
import org.mjtech.tourguide.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl. class that implement
 * user business logic
 */
@Service
public class UserServiceImpl implements UserService {
  private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserRepository userRepository;


  /**
   * {@inheritDoc}
   */
  public User getUser(String userName) {

    if (!userRepository.checkExistUser(userName)) {

      logger.error("NotFoundException: User doesn't exist");

      throw new NotFoundException("User doesn't exist");
    }
    return userRepository.findByUsername(userName);
  }

  /**
   * {@inheritDoc}
   */
  public User getUserById(UUID userId) {
    return userRepository.findById(userId);
  }

  /**
   * {@inheritDoc}
   */
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public User addUser(UserDto userDto) {

    if (userRepository.checkExistUser(userDto.getUsername())) {
      logger.error("BadRequestException: User already exists");
      throw new BadRequestException("User already exists");
    }

    User user = new User(
            userDto.getUserId(),
            userDto.getUsername(),
            userDto.getPhoneNumber(),
            userDto.getEmailAddress(),
            new UserPreferences(userDto.getTripDuration(),
                    userDto.getNumberOfChildren()));

    return userRepository.save(user);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public User updateUser(UserDto userDto) {

    User user = getUser(userDto.getUsername());

    user.setEmailAddress(userDto.getEmailAddress());
    user.setPhoneNumber(userDto.getPhoneNumber());
    user.setUserPreferences(new UserPreferences(userDto.getTripDuration(),
            userDto.getNumberOfChildren()));

    return userRepository.save(user);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteUser(UserDto userDto) {

    User user = getUser(userDto.getUsername());

    userRepository.remove(user);

  }

}
