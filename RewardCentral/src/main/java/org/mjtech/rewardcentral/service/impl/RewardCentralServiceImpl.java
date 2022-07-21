package org.mjtech.rewardcentral.service.impl;

import org.mjtech.rewardcentral.model.UserReward;
import org.mjtech.rewardcentral.service.RewardCentralService;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class RewardCentralServiceImpl implements RewardCentralService {
  @Override
  public int getAttractionRewardPoints(UUID attractionId, UUID userId) {
    try {
      TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 1000));
    }
    catch (InterruptedException ex) {}

    final int randomInt = ThreadLocalRandom.current().nextInt(1, 1000);

    return randomInt;
  }
}
