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

@Tag("RewardsControllerTest")
@DisplayName("Rewards controller test logic")
class RewardsControllerTest extends AbstractTest {

  @Test
  @DisplayName("getRewards should return user reward for given username")
  void getRewards_ShouldReturnUserReward_ForGivenUsername() throws Exception {
    // GIVEN
    String username = "internalUser78";
    String uri = "/getRewards?userName=" + username;

    // WHEN
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    int status = mvcResult.getResponse().getStatus();

    String content = mvcResult.getResponse().getContentAsString();
    List userRewards = super.mapFromJson(content, List.class);

    // THEN
    assertEquals(200, status);
  }
}