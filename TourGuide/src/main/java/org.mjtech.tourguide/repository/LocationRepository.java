package org.mjtech.tourguide.repository;

import lombok.extern.slf4j.Slf4j;
import org.mjtech.tourguide.model.Attraction;
import org.mjtech.tourguide.model.VisitedLocation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class LocationRepository {

  public List<Attraction> getAttractions() {

    String gpsUtilsUrl =  "http://localhost:8081/gpsUtils/attractions";

    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<List<Attraction>> response = restTemplate
            .exchange(
                    gpsUtilsUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Attraction>>() {
                    });

    log.debug("Get All Attractions " + response.getStatusCode().toString());

    return response.getBody();
  }

  public VisitedLocation getUserLocation(UUID userId) {

    String gpsUtilsUrl =  "http://localhost:8081/gpsUtils/userLocation/" + userId;

    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<VisitedLocation> response = restTemplate
            .exchange(
                    gpsUtilsUrl,
                    HttpMethod.GET,
                    null,
                    VisitedLocation.class);

    log.debug("Get visited location " + response.getStatusCode().toString());

    return response.getBody();
  }

}
