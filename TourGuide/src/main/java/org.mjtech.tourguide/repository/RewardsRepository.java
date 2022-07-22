package org.mjtech.tourguide.repository;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * RewardsRepository. class that manage
 * rewards data
 */
@Component
@Slf4j
public class RewardsRepository {

  /**
   * getAttractionRewardPoints. Method that get reward
   * point.
   *
   * @param attractionId an attractionId
   * @param userId a userId
   * @return RewardPoints
   */
  public int getAttractionRewardPoints(UUID attractionId, UUID userId) {

    String rewardPointUrl = "http://localhost:8082/rewardCentral/getRewardPoint/" + attractionId + "/" + userId;

    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<Integer> response = restTemplate
            .exchange(
                    rewardPointUrl,
                    HttpMethod.GET,
                    null,
                    Integer.class);

    log.debug("Get reward point " + response.getStatusCode().toString());
    return response.getBody();
  }

}
