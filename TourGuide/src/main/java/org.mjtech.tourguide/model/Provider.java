package org.mjtech.tourguide.model;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class Provider
{
  public  String name;
  public  double price;
  public  UUID tripId;

  public Provider(final UUID tripId, final String name, final double price) {
    this.name = name;
    this.tripId = tripId;
    this.price = price;
  }
}