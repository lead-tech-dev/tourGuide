package org.mjtech.tourguide.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mjtech.tourguide.model.Attraction;
import org.mjtech.tourguide.model.VisitedLocation;
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

@Tag("LocationRepositoryTest")
@DisplayName("Location repository test logic")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class LocationRepositoryTest {

  private RestTemplate restTemplate;

  @BeforeEach
  public void setup() {
    restTemplate = new RestTemplate();
  }

  @Test
  @DisplayName("getAttractions, should return attractions list")
  void getAttractions_ShouldReturnAttractionList() {
    // GIVEN
    String gpsUtilsUrl =  "http://localhost:8081/gpsUtils/attractions";

    // WHEN
    ResponseEntity<List<Attraction>> response = restTemplate
            .exchange(
                    gpsUtilsUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Attraction>>() {
                    });

    // THEN
    assertThat(response.getStatusCode().toString()).isEqualTo("200 OK");
    assertTrue(Objects.requireNonNull(response.getBody()).size() > 0);
  }

  @Test
  @DisplayName("getUserLocation, should return user visited location for given userId")
  void getUserLocation_ShouldReturnUserVisitedLocation_ForGivenUserId() {
    // GIVEN
    UUID userId = UUID.randomUUID();
    String gpsUtilsUrl =  "http://localhost:8081/gpsUtils/userLocation/" + userId;

    // WHEN
    ResponseEntity<VisitedLocation> response = restTemplate
            .exchange(
                    gpsUtilsUrl,
                    HttpMethod.GET,
                    null,
                    VisitedLocation.class);
    // THEN
    assertThat(response.getStatusCode().toString()).isEqualTo("200 OK");
    assertThat(Objects.requireNonNull(response.getBody()).userId).isEqualTo(userId);
  }
}