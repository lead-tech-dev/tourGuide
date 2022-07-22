package org.mjtech.rewardcentral.service;

import java.util.UUID;

/**
 * RewardCentralService interface structure the business logic
 * of rewardCentral.
 */
public interface RewardCentralService {

  /**
   * getAttractionRewardPoints. Method that get
   * attraction reward points.
   *
   * @param attractionId an attractionId
   * @param userId a userId
   * @return RewardPoints
   */
  int getAttractionRewardPoints(final UUID attractionId, final UUID userId);
}
