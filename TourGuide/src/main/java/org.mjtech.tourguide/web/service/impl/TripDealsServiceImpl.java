package org.mjtech.tourguide.web.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.mjtech.tourguide.model.Provider;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.repository.TripDealsRepository;
import org.mjtech.tourguide.web.service.TripDealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * TripDealsServiceImpl. class that implement
 * tripDeals business logic
 */
@Service
public class TripDealsServiceImpl implements TripDealsService {

  @Autowired
  private TripDealsRepository tripDealsProxy;

  private static final String tripPricerApiKey = "test-server-api-key";

  /**
   * {@inheritDoc}
   */
  @Override
  @Async
  public CompletableFuture<List<Provider>> getTripDeals(User user) {
    System.out.println(user.getUserId());
    int cumulatativeRewardPoints = user.getUserRewards()
            .stream()
            .mapToInt(i -> i.getRewardPoints()).sum();
    System.out.println(cumulatativeRewardPoints);
    List<Provider> providers = tripDealsProxy.getPrice(tripPricerApiKey, user.getUserId(),
            user.getUserPreferences().getNumberOfAdults(),
            user.getUserPreferences().getNumberOfChildren(),
            user.getUserPreferences().getTripDuration(),
            cumulatativeRewardPoints);
    user.setTripDeals(providers);
    return CompletableFuture.completedFuture(providers);
  }
}
