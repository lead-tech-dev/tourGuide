package org.mjtech.tourguide.model;

import java.util.Date;
import java.util.UUID;
import lombok.NoArgsConstructor;

/**
 * The VisitedLocation class implements a VisitedLocation
 * entity.
 */
@NoArgsConstructor
public class VisitedLocation {

  public UUID userId;

  public Location location;
  public Date timeVisited;

  /**
   * VisitedLocation. Constructor of VisitedLocation
   * class.
   *
   * @param userId a userId
   * @param location a location
   * @param timeVisited a timeVisited
   */
  public VisitedLocation(final UUID userId, final Location location, final Date timeVisited) {
    this.userId = userId;
    this.location = location;
    this.timeVisited = timeVisited;
  }


}
