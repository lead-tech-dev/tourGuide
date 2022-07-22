package org.mjtech.tourguide.model;

import lombok.NoArgsConstructor;

/**
 * The Location class implements a Location
 * entity.
 */
@NoArgsConstructor
public class Location {
  public double longitude;
  public double latitude;

  public Location(final double latitude, final double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
