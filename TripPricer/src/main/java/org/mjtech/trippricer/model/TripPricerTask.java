package org.mjtech.trippricer.model;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.mjtech.trippricer.service.impl.TripPricerServiceImpl;

/**
 * The TripPricerTask class implements a tripPricerTask
 * entity.
 */
public class TripPricerTask implements Callable<List<Provider>> {
  private final UUID attractionId;
  private final String apiKey;
  private final int adults;
  private final int children;
  private final int nightsStay;

  /**
   * TripPricerTask. constructor of TripPricerTask
   * class.
   *
   * @param apiKey an apiKey
   * @param attractionId an attractionId
   * @param adults an adults
   * @param children a children
   * @param nightsStay a nightsStay
   */
  public TripPricerTask(final String apiKey,
                        final UUID attractionId,
                        final int adults,
                        final int children, final int nightsStay) {
    this.apiKey = apiKey;
    this.attractionId = attractionId;
    this.adults = adults;
    this.children = children;
    this.nightsStay = nightsStay;
  }

  @Override
  public List<Provider> call() throws Exception {
    return new TripPricerServiceImpl().getPrice(this.apiKey,
            this.attractionId, this.adults,
            this.children, this.nightsStay, 5);
  }
}
