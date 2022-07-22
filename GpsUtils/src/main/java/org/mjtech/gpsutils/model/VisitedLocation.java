package org.mjtech.gpsutils.model;


import java.util.Date;
import java.util.UUID;

/**
 * The VisitedLocation class implements a VisitedLocation
 * entity.
 */
public class VisitedLocation {
  public final UUID userId;
  public final Location location;
  public final Date timeVisited;

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
