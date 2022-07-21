package org.mjtech.rewardcentral.service;

import org.mjtech.rewardcentral.model.UserReward;

import java.util.UUID;

public interface RewardCentralService {
  int getAttractionRewardPoints(final UUID attractionId, final UUID userId);
}
