package org.mjtech.tourguide.web.service.impl;


import org.mjtech.tourguide.model.Attraction;
import org.mjtech.tourguide.model.Location;
import org.mjtech.tourguide.model.VisitedLocation;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserReward;
import org.mjtech.tourguide.repository.LocationRepository;
import org.mjtech.tourguide.repository.RewardsRepository;
import org.mjtech.tourguide.web.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class RewardsServiceImpl  implements RewardsService {
    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

	// proximity in miles
    private int defaultProximityBuffer = 10;
	private int proximityBuffer = defaultProximityBuffer;
	private int attractionProximityRange = 200;
	//private final GpsUtil gpsUtil;

	@Autowired
	private  RewardsRepository rewardsRepository;

	@Autowired
	private  LocationRepository locationRepository;



	public void setProximityBuffer(int proximityBuffer) {
		this.proximityBuffer = proximityBuffer;
	}
	
	public void setDefaultProximityBuffer() {
		proximityBuffer = defaultProximityBuffer;
	}

	@Override
	public List<UserReward> getUserRewards(User user) {
		return user.getUserRewards();
	}

	@Override
	public CompletableFuture<?> calculateRewards(User user) {
		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		return CompletableFuture.runAsync(() -> {
			List<VisitedLocation> userLocations = user.getVisitedLocations();

			List<Attraction> attractions = locationRepository.getAttractions();

			for(int i = 0; i < userLocations.size(); i++ ) {
				for(Attraction attraction : attractions) {

					if(user.getUserRewards().stream().noneMatch(r -> r.getAttraction().attractionName.equals(attraction.attractionName))) {

						if(nearAttraction(userLocations.get(i), attraction)) {

							int rewardPoint = 0;
							try {
								rewardPoint = getRewardPoints(attraction, user).get();
							} catch (InterruptedException | ExecutionException e) {
								throw new RuntimeException(e);
							}

							user.addUserReward(new UserReward(userLocations.get(i), attraction, rewardPoint));
						}
					}
				}
			}
		}, executorService);
	}


	@Override
	public Map<Double, Object> getAttractionDistance(List<Attraction> attractions, Location location) {
		Map<Double, Object> distances = new HashMap<>();

		for (Attraction attraction : attractions) {
			distances.put(getDistance(attraction, location), attraction);

		}

		return distances.entrySet().stream()
						.sorted((o1, o2) -> (int) (o1.getKey() - o2.getKey() ))
						.limit(5)
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@Override
	public boolean isWithinAttractionProximity(Attraction attraction, Location location) {
		return getDistance(attraction, location) > attractionProximityRange ? false : true;
	}


	private boolean nearAttraction(VisitedLocation visitedLocation, Attraction attraction) {

		return getDistance(attraction, visitedLocation.location) > proximityBuffer ? false : true;
	}

	@Override
	@Async
	public CompletableFuture<Integer> getRewardPoints(Attraction attraction, User user) {
		return CompletableFuture.completedFuture(rewardsRepository.getAttractionRewardPoints(attraction.attractionId,
						user.getUserId()));
	}

	@Override
	public double getDistance(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.latitude);
        double lon1 = Math.toRadians(loc1.longitude);
        double lat2 = Math.toRadians(loc2.latitude);
        double lon2 = Math.toRadians(loc2.longitude);

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                               + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
	}

}
