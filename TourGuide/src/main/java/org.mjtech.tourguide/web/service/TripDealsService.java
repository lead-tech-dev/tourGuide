package org.mjtech.tourguide.web.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.mjtech.tourguide.model.Provider;
import org.mjtech.tourguide.model.user.User;

/**
 * TripDealsService interface structure the business logic
 * of tripDeals.
 */
public interface TripDealsService {

  /**
   * getTripDeals. Method that get ten
   * user Provider.
   *
   * @param user a user
   * @return Provider list
   */
  CompletableFuture<List<Provider>> getTripDeals(User user);
}
