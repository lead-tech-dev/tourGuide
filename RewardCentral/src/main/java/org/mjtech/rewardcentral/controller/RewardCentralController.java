package org.mjtech.rewardcentral.controller;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.mjtech.rewardcentral.service.RewardCentralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



/**
 * RewardCentralController. class that manage
 * request/response logic of rewardCentral.
 */
@RestController
@Slf4j
public class RewardCentralController {

  private Logger logger = LoggerFactory.getLogger(RewardCentralController.class);
  private final RewardCentralService rewardCentralService;

  public RewardCentralController(RewardCentralService rewardCentralService) {
    this.rewardCentralService = rewardCentralService;
  }

  /**
   * getRewardPoints. Method that get
   * reward points.
   *
   * @param attractionId an attractionId
   * @param userId a userId
   * @return rewardPoint
   */
  @GetMapping("/rewardCentral/getRewardPoint/{attractionId}/{userId}")
  public ResponseEntity<Integer> getRewardPoints(
          @PathVariable UUID attractionId, @PathVariable UUID userId) {

    logger.info("getRewardPoints request for user: {} : ", userId);

    int rewardPoint = rewardCentralService.getAttractionRewardPoints(
            attractionId, userId);

    logger.info("getRewardPoints response:  {} for user:  {} : ", rewardPoint, userId);

    return ResponseEntity.ok(rewardPoint);
  }
}
