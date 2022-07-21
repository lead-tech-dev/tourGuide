package org.mjtech.tourguide.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mjtech.tourguide.dto.UserDto;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserPreferences;
import org.mjtech.tourguide.utility.ConvertTo;
import org.mjtech.tourguide.web.exception.BadRequestException;
import org.mjtech.tourguide.web.exception.NotFoundException;
import org.mjtech.tourguide.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tag("UserServiceImplTest")
@DisplayName("User Service implement test logic")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

  @Autowired
  private UserService userService;

  @Test
  @DisplayName("getUser should throw notFoundException for given not exist user")
  void getUser_ShouldThrowNotFoundException_ForGivenNotExistUser() {
    // GIVEN
    User user = new User(UUID.randomUUID(), "corbiere", "000", "jon@tourGuide.com", new UserPreferences(2, 7));

    // WHEN
    // THEN
    assertThrows(NotFoundException.class, () -> userService.getUser(user.getUsername()));
  }

  @Test
  @DisplayName("getUser should return user for given username")
  void getUser_ShouldReturnUser_ForGivenUsername() {
    // GIVEN
    User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com", new UserPreferences(2, 7));
    userService.addUser(ConvertTo.convertToUserDto(user));

    // WHEN
    User result = userService.getUser(user.getUsername());

    // THEN
    assertThat(result.getUserId()).isEqualTo(user.getUserId());
  }

  @Test
  @DisplayName("getUserById should return user for given userId")
  void getUserById_ShouldReturnUser_ForGivenUserId() {
    // GIVEN
    User user = new User(UUID.randomUUID(), "alexander", "000", "jon@tourGuide.com", new UserPreferences(2, 7));
    userService.addUser(ConvertTo.convertToUserDto(user));

    // WHEN
    User result = userService.getUserById(user.getUserId());

    // THEN
    assertThat(result.getUsername()).isEqualTo(user.getUsername());
  }

  @Test
  @DisplayName("getAllUsers should return users list")
  void getAllUsers_ShouldReturnUserList() {
    // GIVEN
    User user = new User(UUID.randomUUID(), "jonny walker", "000", "jon@tourGuide.com", new UserPreferences(2, 7));
    userService.addUser(ConvertTo.convertToUserDto(user));

    // WHEN
    List<User> result = userService.getAllUsers();

    // THEN
    assertTrue(result.size() > 0);
  }

  @Test
  @DisplayName("addUser should throw badRequestException for given exist user")
  void addUser_ShouldThrowBadRequestException_ForGivenExistUser() {
    // GIVEN
    UserDto user = ConvertTo.convertToUserDto(new User(UUID.randomUUID(), "cartman", "000", "cartman@tourGuide.com", new UserPreferences(2, 7)));
    userService.addUser(user);

    // WHEN
    // THEN
    assertThrows(BadRequestException.class, () -> userService.addUser(user));
  }

  @Test
  @DisplayName("addUser should return added user for given userDto")
  void addUser_ShouldReturnAddedUser_ForGivenUser() {
    // GIVEN
    User user = new User(UUID.randomUUID(), "lamina", "000", "jon@tourGuide.com", new UserPreferences(2, 7));

    // WHEN
    User result =  userService.addUser(ConvertTo.convertToUserDto(user));

    // THEN
    assertThat(result.getUsername()).isEqualTo(user.getUsername());
  }

  @Test
  @DisplayName("updateUser should throw notFoundException for given not exist user")
  void updateUser_ShouldThrowNotFoundException_ForGivenNotExistUser() {
    // GIVEN
    UserDto user = ConvertTo.convertToUserDto(new User(UUID.randomUUID(), "maximus", "000", "cartman@tourGuide.com", new UserPreferences(2, 7)));


    // WHEN
    // THEN
    assertThrows(NotFoundException.class, () -> userService.updateUser(user));
  }

  @Test
  @DisplayName("updateUser should return updated user for given user")
  void updateUser_ShouldReturnUpdatedUser_ForGivenUser() {
    // GIVEN
    UserDto user = ConvertTo.convertToUserDto(new User(UUID.randomUUID(), "maximus", "000", "cartman@tourGuide.com", new UserPreferences(2, 7)));
    User added = userService.addUser(user);
    added.setPhoneNumber("666666");


    // WHEN
    User result = userService.updateUser(ConvertTo.convertToUserDto(added));

    // THEN
    assertThat(result.getPhoneNumber()).isEqualTo(added.getPhoneNumber());
  }

  @Test
  @DisplayName("deleteUser should throw notFoundException for given not exist user")
  void deleteUser_ShouldThrowNotFoundException_ForGivenNotExistUser() {
    // GIVEN
    UserDto user = ConvertTo.convertToUserDto(new User(UUID.randomUUID(), "patrick", "000", "cartman@tourGuide.com", new UserPreferences(2, 7)));

    // WHEN
    // THEN
    assertThrows(NotFoundException.class, () -> userService.deleteUser(user));
  }

  @Test
  @DisplayName("deleteUser should delete given user")
  void deleteUser_ShouldDeleteGivenUser() {
    // GIVEN
    User user = new User(UUID.randomUUID(), "laplume", "000", "cartman@tourGuide.com", new UserPreferences(2, 7));
    userService.addUser(ConvertTo.convertToUserDto(user));

    // WHEN
    userService.deleteUser(ConvertTo.convertToUserDto(user));

    // THEN
    assertThrows(NotFoundException.class, () -> userService.getUser(user.getUsername()));
  }
}