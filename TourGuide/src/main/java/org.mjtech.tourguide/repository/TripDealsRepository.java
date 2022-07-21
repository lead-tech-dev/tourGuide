package org.mjtech.tourguide.repository;

import lombok.extern.slf4j.Slf4j;
import org.mjtech.tourguide.model.Provider;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class TripDealsRepository {

  public List<Provider> getPrice(String apiKey, UUID attractionId, int adults, int children, int nightsStay, int rewardsPoints) {

    String tripPriceUrl =  "http://localhost:8083/getTripPrice?apiKey=" + apiKey + "&attractionId=" + attractionId + "&adults=" + adults + "&children=" + children + "&nightsStay=" + nightsStay + "&rewardsPoints=" + rewardsPoints;

    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<List<Provider>> response = restTemplate
            .exchange(
                    tripPriceUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Provider>>() {
                    });

    log.debug("Get all trip provider " + response.getStatusCode().toString());

    return response.getBody();
  }


}
