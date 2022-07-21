package org.mjtech.gpsutils.service;


import org.mjtech.gpsutils.model.Attraction;
import org.mjtech.gpsutils.model.VisitedLocation;

import java.util.List;
import java.util.UUID;

public interface GpsUtilsService {

  VisitedLocation getUserLocation(final UUID userId);

  List<Attraction> getAttractions();
}
