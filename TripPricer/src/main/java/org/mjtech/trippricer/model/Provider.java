package org.mjtech.trippricer.model;

import java.util.UUID;

/**
 * The Provider class implements a Provider
 * entity.
 */
public class Provider {
  public final String name;
  public final double price;
  public final UUID tripId;

  /**
   * Provider. constructor of Provider class.
   *
   * @param tripId a tripId
   * @param name a name
   * @param price a price
   */
  public Provider(final UUID tripId, final String name, final double price) {
    this.name = name;
    this.tripId = tripId;
    this.price = price;
  }
}