package org.mjtech.tourguide.model.user;

import lombok.Data;
import org.mjtech.tourguide.model.Attraction;
import org.mjtech.tourguide.model.VisitedLocation;

/**
 * The UserReward class implements a UserReward
 * entity.
 */
@Data
public class UserReward {

  public final VisitedLocation visitedLocation;
  public final Attraction attraction;
  private int rewardPoints;

  /**
   * UserReward. Constructor of userReward class.
   *
   * @param visitedLocation a visitedLocation
   * @param attraction an attraction
   * @param rewardPoints a rewardPoints
   */
  public UserReward(VisitedLocation visitedLocation, Attraction attraction, int rewardPoints) {
    this.visitedLocation = visitedLocation;
    this.attraction = attraction;
    this.rewardPoints = rewardPoints;
  }

  public UserReward(VisitedLocation visitedLocation, Attraction attraction) {
    this.visitedLocation = visitedLocation;
    this.attraction = attraction;
  }

  public void setRewardPoints(int rewardPoints) {
    this.rewardPoints = rewardPoints;
  }

  public int getRewardPoints() {
    return rewardPoints;
  }

}
