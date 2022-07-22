package org.mjtech.tourguide;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mjtech.tourguide.helper.InternalTestHelper;
import org.mjtech.tourguide.model.Attraction;
import org.mjtech.tourguide.model.VisitedLocation;
import org.mjtech.tourguide.repository.UserRepository;
import org.mjtech.tourguide.web.service.LocationService;
import org.mjtech.tourguide.web.service.RewardsService;
import org.mjtech.tourguide.web.service.UserService;
import org.mjtech.tourguide.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPerformance {

	@Autowired
	private LocationService locationService;

	@Autowired
	private RewardsService rewardsService;

	@Autowired
	private UserService userService;

	/*
	 * A note on performance improvements:
	 *
	 *     The number of users generated for the high volume tests can be easily adjusted via this method:
	 *
	 *     		InternalTestHelper.setInternalUserNumber(100000);
	 *
	 *
	 *     These tests can be modified to suit new solutions, just as long as the performance metrics
	 *     at the end of the tests remains consistent.
	 *
	 *     These are performance metrics that we are trying to hit:
	 *
	 *     highVolumeTrackLocation: 100,000 users within 15 minutes:
	 *     		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
     *
     *     highVolumeGetRewards: 100,000 users within 20 minutes:
	 *          assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	 */

	//@Ignore
	@Test
	public void highVolumeTrackLocation() throws ExecutionException, InterruptedException {

		// Users should be incremented up to 100,000, and test finishes within 15 minutes
		InternalTestHelper.setInternalUserNumber(100);
		UserRepository userRepository = new UserRepository();

		List<User> allUsers = new ArrayList<>();
		allUsers = userRepository.findAll();
		List<CompletableFuture<VisitedLocation>> allFutures = new ArrayList<>();
	    StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		for (User user: allUsers) {
			//locationService.trackUserLocation(user);
			allFutures.add(locationService.trackUserLocation(user));
		}

		CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0])).join();

		for (int i = 0; i < allFutures.size(); i++){
			System.out.println(allFutures.get(i).get().location.latitude);
		}

		stopWatch.stop();
		userRepository.tracker.stopTracking();

		System.out.println("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
	    assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}

	//@Ignore
	@Test
	public void highVolumeGetRewards() throws InterruptedException, ExecutionException {
		//RewardsProxy rewardsService = new RewardsProxy();
		//LocationProxy gpsUtil = new LocationProxy();
		// Users should be incremented up to 100,000, and test finishes within 20 minutes

		InternalTestHelper.setInternalUserNumber(100);
		UserRepository userRepository = new UserRepository();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

	    Attraction attraction = locationService.getAttractions().get(0);
		List<User> allUsers = new ArrayList<>();
		allUsers = userRepository.findAll();
		System.out.println(allUsers.size());
		allUsers.forEach(u -> u.addToVisitedLocations(new VisitedLocation(u.getUserId(), attraction, new Date())));

		CompletableFuture<?>[] futures = allUsers.stream()
						.map(user -> rewardsService.calculateRewards(user))
						.toArray(CompletableFuture[]::new);
		CompletableFuture.allOf(futures).join();


		for(User user : allUsers) {
			assertTrue(user.getUserRewards().size() > 0);
		}

		stopWatch.stop();
		userRepository.tracker.stopTracking();

		System.out.println("highVolumeGetRewards: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds."); 
		assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}


	
}
