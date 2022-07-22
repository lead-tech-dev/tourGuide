package org.mjtech.gpsutils.service;

import java.util.List;
import java.util.UUID;
import org.mjtech.gpsutils.model.Attraction;
import org.mjtech.gpsutils.model.VisitedLocation;

/**
 * GpsUtilsService interface structure the business logic
 * of gpsUtils.
 */
public interface GpsUtilsService {

  /**
   * getUserLocation. Method that get a user
   * visited location.
   *
   * @param userId a userId
   * @return visitedLocation
   */
  VisitedLocation getUserLocation(final UUID userId);

  /**
   * getAttractions. method that get an attraction
   * list.
   *
   * @return attraction list
   */
  List<Attraction> getAttractions();
}
