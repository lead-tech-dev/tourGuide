package org.mjtech.trippricer.service;

import java.util.List;
import java.util.UUID;
import org.mjtech.trippricer.model.Provider;

/**
 * TripPricerService interface structure the business logic
 * of tripPrice.
 */
public interface TripPricerService {

  /**
   * getPrice. Methode that get provider
   * List.
   *
   * @param apiKey an apiKey
   * @param attractionId an attractionId
   * @param adults an adults
   * @param children a children
   * @param nightsStay a nightsStay
   * @param rewardsPoints a rewardsPoints
   * @return provider list
   */
  List<Provider> getPrice(final String apiKey,
                          final UUID attractionId,
                          final int adults,
                          final int children,
                          final int nightsStay, final int rewardsPoints);

  /**
   * getProviderName. Method that get a provider
   * name.
   *
   * @param apiKey an apiKey
   * @param adults an adults
   * @return provider name.
   */
  String getProviderName(final String apiKey, final int adults);
}
