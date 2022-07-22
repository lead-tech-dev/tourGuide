package org.mjtech.tourguide.model;

import lombok.NoArgsConstructor;

/**
 * The UserReward class implements a UserReward
 * entity.
 */
@NoArgsConstructor
public class UserReward {
  private int rewardPoints;

  public UserReward(int rewardPoints) {
    this.rewardPoints = rewardPoints;
  }

  public void setRewardPoints(int rewardPoints) {
    this.rewardPoints = rewardPoints;
  }

  public int getRewardPoints() {
    return rewardPoints;
  }

}
