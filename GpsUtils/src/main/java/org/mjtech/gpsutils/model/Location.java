package org.mjtech.gpsutils.model;

public class Location {
  public final double longitude;
  public final double latitude;

  public Location(final double latitude, final double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
