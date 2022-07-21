package org.mjtech.tourguide.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mjtech.tourguide.model.Location;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("TripDealsControllerTest")
@DisplayName("TripDeals controller test logic")
class TripDealsControllerTest extends AbstractTest {

  @Test
  @DisplayName("getTripDeals should return user provider list for given username")
  void getTripDeals_ShouldReturnUserProviderList_ForGivenUsername() throws Exception {
    // GIVEN
    String username = "internalUser78";
    String uri = "/getTripDeals?userName=" + username;

    // WHEN
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    int status = mvcResult.getResponse().getStatus();

    String content = mvcResult.getResponse().getContentAsString();
    List tripDeals = super.mapFromJson(content, List.class);

    // THEN
    assertEquals(200, status);
    assertTrue(tripDeals.size() == 10);
  }
}