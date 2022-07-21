package org.mjtech.tourguide.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
public class Attraction extends Location{
  public  String attractionName;
  public  String city;
  public  String state;
  public  UUID attractionId;

  public Attraction(final String attractionName, final String city, final String state, final double latitude, final double longitude) {
    super(latitude, longitude);
    this.attractionName = attractionName;
    this.city = city;
    this.state = state;
    this.attractionId = UUID.randomUUID();
  }
}
