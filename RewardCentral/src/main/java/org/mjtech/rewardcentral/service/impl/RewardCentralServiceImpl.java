package org.mjtech.rewardcentral.service.impl;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.mjtech.rewardcentral.service.RewardCentralService;
import org.springframework.stereotype.Service;

/**
 * RewardCentralServiceImpl. class that implement
 * rewardCentral business logic
 */
@Service
public class RewardCentralServiceImpl implements RewardCentralService {

  /**
   * {@inheritDoc}
   */
  @Override
  public int getAttractionRewardPoints(UUID attractionId, UUID userId) {
    try {
      TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(
              1, 1000));
    } catch (InterruptedException ex) {
      System.out.println(ex.getMessage());
    }

    final int randomInt = ThreadLocalRandom.current().nextInt(1, 1000);

    return randomInt;
  }
}
