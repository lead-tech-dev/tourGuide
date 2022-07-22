package org.mjtech.tourguide.web.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.mjtech.tourguide.dto.UserDto;
import org.mjtech.tourguide.utility.ConvertTo;
import org.mjtech.tourguide.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController. class that manage
 * request/response logic of user.
 */
@RestController
@Slf4j
public class UserController {

  private Logger logger = LoggerFactory.getLogger(LocationController.class);
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * index. Method that get welcome
   * message.
   *
   * @return String
   */
  @RequestMapping("/")
  public String index() {

    logger.info("home request");

    return "Greetings from TourGuide!";
  }

  /**
   * getUser. Method that get user.
   *
   * @param username a username
   * @return UserDto
   */
  @GetMapping("/users/{username}")
  public UserDto getUser(@PathVariable String username) {

    logger.info("getUser request for user: {} : ", username);

    UserDto userDto = ConvertTo.convertToUserDto(userService.getUser(username));

    logger.info("getUser successful: {} for user: {} : ", userDto.toString(), username);

    return userDto;
  }

  /**
   * getUsers. Method that get user list.
   *
   * @return UserDto list
   */
  @GetMapping("/users")
  public Iterable<UserDto> getUsers() {

    logger.info("getUsers request");

    List<UserDto> users = ConvertTo.convertToUserListDto(userService.getAllUsers());

    logger.info("getUsers successful: {}", users);

    return users;
  }

  /**
   * addUser. Method that add new user.
   *
   * @param userDto a userDto
   * @return UserDto
   */
  @PostMapping("/users")
  public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {

    logger.info("addUser request for user: {} : ", userDto.toString());

    UserDto added = ConvertTo.convertToUserDto(userService.addUser(userDto));

    logger.info("addUser successful for user: {} : ", added.toString());

    return new ResponseEntity<UserDto>(added, HttpStatus.CREATED);
  }

  /**
   * updateUser. Method that update exists user.
   *
   * @param userDto a userDto
   * @return updated UserDto
   */
  @PutMapping("/users")
  public UserDto updateUser(@Valid @RequestBody UserDto userDto) {

    logger.info("updateUser request for user: {} : ", userDto.toString());

    UserDto updated = ConvertTo.convertToUserDto(userService.updateUser(userDto));

    logger.info("updateUser successful for user: {} : ", updated.toString());

    return updated;
  }

  /**
   * deleteUser. Method that delete exists user.
   *
   * @param userDto a userDto
   * @return void
   */
  @DeleteMapping("/users")
  public ResponseEntity<Void> deleteUser(@RequestBody UserDto userDto) {

    logger.info("deleteUser request for user: {} : ", userDto.toString());

    userService.deleteUser(userDto);

    logger.info("deleteUser successful for user: {} : ", userDto.toString());

    return ResponseEntity.noContent().build();
  }

}