package org.mjtech.gpsutils.model;

import java.util.UUID;

/**
 * The Attraction class implements an Attraction
 * entity.
 */
public class Attraction extends Location {
  public final String attractionName;
  public final String city;
  public final String state;
  public final UUID attractionId;

  /**
   * Attraction. constructor of Attraction class
   *
   * @param attractionName a attractionName
   * @param city           a city
   * @param state          a state
   * @param latitude       a latitude
   * @param longitude      a longitude
   */
  public Attraction(final String attractionName,
                    final String city, final String state,
                    final double latitude, final double longitude) {
    super(latitude, longitude);
    this.attractionName = attractionName;
    this.city = city;
    this.state = state;
    this.attractionId = UUID.randomUUID();
  }
}
