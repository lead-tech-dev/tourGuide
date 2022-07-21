package org.mjtech.tourguide.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mjtech.tourguide.model.Provider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tag("TripDealsRepositoryTest")
@DisplayName("Trip Deals repository test logic")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class TripDealsRepositoryTest {

  private RestTemplate restTemplate;

  @BeforeEach
  public void setup() {
    restTemplate = new RestTemplate();
  }

  @Test
  @DisplayName("getPrice, should return provider list for given apiKey, " +
          "attractionId, adults, children, nightsStay, rewardsPoints")
  void getPrice_ShouldReturnProviderList() {
    // GIVEN
    String apiKey = "test-server-api-key";
    UUID attractionId = UUID.randomUUID();
    int adults = 1;
    int children = 3;
    int nightsStay = 5;
    int rewardsPoints = 456;
    String tripPriceUrl =  "http://localhost:8083/getTripPrice?apiKey=" + apiKey + "&attractionId=" + attractionId + "&adults=" + adults + "&children=" + children + "&nightsStay=" + nightsStay + "&rewardsPoints=" + rewardsPoints;

    // WHEN
    ResponseEntity<List<Provider>> response = restTemplate
            .exchange(
                    tripPriceUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Provider>>() {
                    });
    // THEN
    assertThat(response.getStatusCode().toString()).isEqualTo("200 OK");
    assertTrue(Objects.requireNonNull(response.getBody()).size() > 0);
  }

}