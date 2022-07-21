package org.mjtech.rewardcentral.controller;

import lombok.extern.slf4j.Slf4j;
import org.mjtech.rewardcentral.model.UserReward;
import org.mjtech.rewardcentral.service.RewardCentralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class RewardCentralController {
  private final RewardCentralService rewardCentralService;

  public RewardCentralController(RewardCentralService rewardCentralService) {
    this.rewardCentralService = rewardCentralService;
  }

  @GetMapping("/rewardCentral/getRewardPoint/{attractionId}/{userId}")
  public ResponseEntity<Integer> getRewardPoints(@PathVariable UUID attractionId, @PathVariable UUID userId) {
    int rewardPoint = rewardCentralService.getAttractionRewardPoints(attractionId, userId);

    return ResponseEntity.ok(rewardPoint);
  }
}
