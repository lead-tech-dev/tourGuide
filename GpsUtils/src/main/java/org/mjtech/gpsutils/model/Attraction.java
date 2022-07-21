package org.mjtech.gpsutils.model;

import java.util.UUID;

public class Attraction extends Location {
  public final String attractionName;
  public final String city;
  public final String state;
  public final UUID attractionId;

  public Attraction(final String attractionName, final String city, final String state, final double latitude, final double longitude) {
    super(latitude, longitude);
    this.attractionName = attractionName;
    this.city = city;
    this.state = state;
    this.attractionId = UUID.randomUUID();
  }
}
