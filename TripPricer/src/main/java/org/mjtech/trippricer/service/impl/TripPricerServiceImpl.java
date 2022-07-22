package org.mjtech.trippricer.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.mjtech.trippricer.model.Provider;
import org.mjtech.trippricer.service.TripPricerService;
import org.springframework.stereotype.Service;

/**
 * TripPricerServiceImpl. class that implement
 * tripPrice business logic
 */
@Service
public class TripPricerServiceImpl implements TripPricerService {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Provider> getPrice(String apiKey,
                                 UUID attractionId,
                                 int adults, int children, int nightsStay,
                                 int rewardsPoints) {

    final List<Provider> providers = new ArrayList<>();
    final Set<String> providersUsed = new HashSet<>();

    try {
      TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 50));
    } catch (InterruptedException ex) {
      System.out.println(ex.getMessage());
    }

    for (int i = 0; i < 10; ++i) {
      final int multiple = ThreadLocalRandom.current().nextInt(100, 700);
      final double childrenDiscount = children / 3;
      double price =
              multiple * adults + multiple * childrenDiscount * nightsStay + 0.99 - rewardsPoints;
      if (price < 0.0) {
        price = 0.0;
      }
      String provider = "";
      do {
        provider = this.getProviderName(apiKey, adults);
      } while (providersUsed.contains(provider));

      providersUsed.add(provider);
      providers.add(new Provider(attractionId, provider, price));

    }
    return providers;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getProviderName(String apiKey, int adults) {
    final int multiple = ThreadLocalRandom.current().nextInt(1, 11);
    switch (multiple) {
      case 1: {
        return "Holiday Travels";
      }
      case 2: {
        return "Enterprize Ventures Limited";
      }
      case 3: {
        return "Sunny Days";
      }
      case 4: {
        return "FlyAway Trips";
      }
      case 5: {
        return "United Partners Vacations";
      }
      case 6: {
        return "Dream Trips";
      }
      case 7: {
        return "Live Free";
      }
      case 8: {
        return "Dancing Waves Cruselines and Partners";
      }
      case 9: {
        return "AdventureCo";
      }
      case 10: {
        return "Holiday Trip";
      }
      case 11: {
        return "Money Trip";
      }
      default: {
        return "Cure-Your-Blues";
      }
    }
  }
}
