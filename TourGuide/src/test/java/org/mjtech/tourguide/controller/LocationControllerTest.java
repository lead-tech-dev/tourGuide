package org.mjtech.tourguide.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mjtech.tourguide.model.Closest;
import org.mjtech.tourguide.model.Location;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Tag("LocationControllerTest")
@DisplayName("Location controller test logic")
class LocationControllerTest extends AbstractTest{

  @Test
  @DisplayName("getLocation should return user location data")
  void getLocation_ShouldReturnUserLocationData() throws Exception {
    // GIVEN
    String username = "internalUser78";
    String uri = "/getLocation?userName=" + username;

    // WHEN
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    int status = mvcResult.getResponse().getStatus();

    String content = mvcResult.getResponse().getContentAsString();
    Location location = super.mapFromJson(content, Location.class);

    // THEN
    assertEquals(200, status);
  }

  @Test
  @DisplayName("getNearbyAttractions should return user five close attraction for given username")
  void getNearbyAttractions_ShouldReturnFiveCloseAttraction_ForGivenUsername() throws Exception {
    // GIVEN
    String username = "internalUser78";
    String uri = "/getNearbyAttractions?userName=" + username;

    // WHEN
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    int status = mvcResult.getResponse().getStatus();

    String content = mvcResult.getResponse().getContentAsString();
    Closest[] attractions = super.mapFromJson(content, Closest[].class);

    // THEN
    assertEquals(200, status);
    assertTrue(attractions.length == 5);
  }

  @Test
  @DisplayName("getAllCurrentLocations should return users locations list")
  void getAllCurrentLocations_ShouldReturnUsersLocationsList() throws Exception {
    // GIVEN
    String uri = "/getAllCurrentLocations";

    // WHEN
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    int status = mvcResult.getResponse().getStatus();

    String content = mvcResult.getResponse().getContentAsString();
    Map attractions = super.mapFromJson(content, Map.class);

    // THEN
    assertEquals(200, status);
    assertTrue(attractions.size() > 0);
  }
}