package org.mjtech.tourguide.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mjtech.tourguide.dto.UserDto;
import org.mjtech.tourguide.model.Location;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserPreferences;
import org.mjtech.tourguide.utility.ConvertTo;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tag("UserControllerTest")
@DisplayName("User controller test logic")
class UserControllerTest extends AbstractTest {

  @Test
  @DisplayName("getUser should return user data")
  void getUser_ShouldReturnUser() throws Exception {
    // GIVEN
    String username = "internalUser78";
    String uri = "/users/" + username;

    // WHEN
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    int status = mvcResult.getResponse().getStatus();

    String content = mvcResult.getResponse().getContentAsString();
    UserDto user = super.mapFromJson(content, UserDto.class);

    // THEN
    assertEquals(200, status);
    assertThat(user.getUsername()).isEqualTo(username);
  }

  @Test
  @DisplayName("getUsers should return user list data")
  void getUsers_ShouldReturnUserList() throws Exception {
    // GIVEN
    String uri = "/users";

    // WHEN
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    int status = mvcResult.getResponse().getStatus();

    String content = mvcResult.getResponse().getContentAsString();
    List users = super.mapFromJson(content, List.class);

    // THEN
    assertEquals(200, status);
    assertTrue(users.size() > 0);
  }

  @Test
  @DisplayName("addUser should return added user for given user")
  void addUser_ShouldReturnAddedUser_ForGivenUser() throws Exception {
    // GIVEN
    String uri = "/users";
    User user = new User(UUID.randomUUID(), "la corbiere", "000", "corbiere@tourGuide.com", new UserPreferences(2, 7));

    // WHEN
    String inputJson = super.mapToJson(ConvertTo.convertToUserDto(user));
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    String content = mvcResult.getResponse().getContentAsString();
    UserDto result = super.mapFromJson(content, UserDto.class);

    // THEN
    assertEquals(201, status);
    assertThat(result.getUsername()).isEqualTo(user.getUsername());
  }

  @Test
  @DisplayName("updateUser should return updated user for given user")
  void updateUser_ShouldReturnUpdatedUser_ForGivenUser() throws Exception {
    // GIVEN
    String uri = "/users";
    User user = new User(UUID.randomUUID(), "internalUser7", "111111", "internalUser7@tourGuide.com", new UserPreferences(2, 7));

    // WHEN
    String inputJson = super.mapToJson(ConvertTo.convertToUserDto(user));
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    String content = mvcResult.getResponse().getContentAsString();
    UserDto result = super.mapFromJson(content, UserDto.class);

    // THEN
    assertEquals(200, status);
    assertThat(result.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
  }

  @Test
  @DisplayName("deleteUser should delete user ")
  void deleteUser_ShouldDelete_GivenUser() throws Exception {
    // GIVEN
    String uri = "/users";
    User user = new User(UUID.randomUUID(), "internalUser50", "000", "internalUser50@tourGuide.com", new UserPreferences(2, 7));

    // WHEN
    String inputJson = super.mapToJson(ConvertTo.convertToUserDto(user));
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    int status = mvcResult.getResponse().getStatus();

    // THEN
    assertEquals(204, status);
  }
}