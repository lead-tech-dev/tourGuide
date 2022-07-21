package org.mjtech.tourguide.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
public class VisitedLocation {

  public  UUID userId;
  public  Location location;
  public  Date timeVisited;

  public VisitedLocation(final UUID userId, final Location location, final Date timeVisited) {
    this.userId = userId;
    this.location = location;
    this.timeVisited = timeVisited;
  }


}
