package org.mjtech.gpsutils.model;


import java.util.Date;
import java.util.UUID;

public class VisitedLocation {
  public final UUID userId;
  public final Location location;
  public final Date timeVisited;

  public VisitedLocation(final UUID userId, final Location location, final Date timeVisited) {
    this.userId = userId;
    this.location = location;
    this.timeVisited = timeVisited;
  }



}
