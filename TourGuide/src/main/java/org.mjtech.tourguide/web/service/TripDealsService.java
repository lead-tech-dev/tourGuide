package org.mjtech.tourguide.web.service;

import org.mjtech.tourguide.model.Provider;
import org.mjtech.tourguide.model.user.User;

import java.util.List;

public interface TripDealsService {
  List<Provider> getTripDeals(User user);
}
