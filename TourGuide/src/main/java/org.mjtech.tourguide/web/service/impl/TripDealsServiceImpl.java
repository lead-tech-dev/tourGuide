package org.mjtech.tourguide.web.service.impl;

import org.mjtech.tourguide.model.Provider;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.repository.TripDealsRepository;
import org.mjtech.tourguide.web.service.TripDealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripDealsServiceImpl implements TripDealsService {

  @Autowired
  private TripDealsRepository tripDealsProxy;

  private static final String tripPricerApiKey = "test-server-api-key";


  @Override
  public List<Provider> getTripDeals(User user) {
    int cumulatativeRewardPoints = user.getUserRewards().stream().mapToInt(i -> i.getRewardPoints()).sum();
    List<Provider> providers = tripDealsProxy.getPrice(tripPricerApiKey, user.getUserId(), user.getUserPreferences().getNumberOfAdults(),
            user.getUserPreferences().getNumberOfChildren(), user.getUserPreferences().getTripDuration(), cumulatativeRewardPoints);
    user.setTripDeals(providers);
    return providers;
  }
}
