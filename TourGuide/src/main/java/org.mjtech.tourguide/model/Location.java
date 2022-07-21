package org.mjtech.tourguide.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Location {
  public  double longitude;
  public  double latitude;

  public Location(final double latitude, final double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
