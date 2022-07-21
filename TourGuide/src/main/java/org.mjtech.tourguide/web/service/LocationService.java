package org.mjtech.tourguide.web.service;

import org.mjtech.tourguide.model.Attraction;
import org.mjtech.tourguide.model.Closest;
import org.mjtech.tourguide.model.VisitedLocation;
import org.mjtech.tourguide.model.user.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface LocationService {
  List<Attraction> getAttractions();

  //VisitedLocation getUserLocation(UUID userId);

  //CompletableFuture<?> addUserLocation(User user) throws ExecutionException, InterruptedException;

  List<Closest> getNearByAttractions(VisitedLocation visitedLocation) throws ExecutionException, InterruptedException;

  Map<String, Object> getAllCurrentLocation();

  CompletableFuture<VisitedLocation> getUserLocation(String userName) throws InterruptedException, ExecutionException;

  CompletableFuture<VisitedLocation> trackUserLocation(User user) throws ExecutionException, InterruptedException;
}
