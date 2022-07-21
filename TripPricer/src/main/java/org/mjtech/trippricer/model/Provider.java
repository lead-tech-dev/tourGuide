package org.mjtech.trippricer.model;

import java.util.UUID;

public class Provider
{
  public final String name;
  public final double price;
  public final UUID tripId;

  public Provider(final UUID tripId, final String name, final double price) {
    this.name = name;
    this.tripId = tripId;
    this.price = price;
  }
}