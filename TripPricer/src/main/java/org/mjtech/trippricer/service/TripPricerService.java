package org.mjtech.trippricer.service;

import org.mjtech.trippricer.model.Provider;

import java.util.List;
import java.util.UUID;

public interface TripPricerService {
  List<Provider> getPrice(final String apiKey, final UUID attractionId, final int adults, final int children, final int nightsStay, final int rewardsPoints);

  String getProviderName(final String apiKey, final int adults);
}
