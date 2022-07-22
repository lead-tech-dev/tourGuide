package org.mjtech.tourguide.model;

import java.util.UUID;
import lombok.NoArgsConstructor;

/**
 * The Provider class implements a Provider
 * entity.
 */
@NoArgsConstructor
public class Provider {
  public String name;
  public double price;
  public UUID tripId;

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