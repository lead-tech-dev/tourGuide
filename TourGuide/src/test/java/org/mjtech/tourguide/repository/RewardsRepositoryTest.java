package org.mjtech.tourguide.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tag("RewardsRepositoryTest")
@DisplayName("Rewards repository test logic")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RewardsRepositoryTest {

  private RestTemplate restTemplate;

  @BeforeEach
  public void setup() {
    restTemplate = new RestTemplate();
  }

  @Test
  @DisplayName("getAttractionRewardPoints, should return rewards point for given attractionId and userId")
  void getAttractionRewardPoints_ShouldRewardsPoint_ForGivenAttractionIdAndUserId() {
    // GIVEN
    UUID attractionId = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    String rewardPointUrl =  "http://localhost:8082/rewardCentral/getRewardPoint/" + attractionId + "/" + userId;

    // WHEN
    ResponseEntity<Integer> response = restTemplate
            .exchange(
                    rewardPointUrl,
                    HttpMethod.GET,
                    null,
                    Integer.class);
    // THEN
    assertThat(response.getStatusCode().toString()).isEqualTo("200 OK");
    assertTrue(Objects.requireNonNull(response.getBody()) > 0);
  }
}