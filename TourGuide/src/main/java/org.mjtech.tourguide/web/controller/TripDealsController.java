package org.mjtech.tourguide.web.controller;

import com.jsoniter.output.JsonStream;
import java.util.List;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.mjtech.tourguide.model.Provider;
import org.mjtech.tourguide.web.service.TripDealsService;
import org.mjtech.tourguide.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * TripDealsController. class that manage
 * request/response logic of tripDeals.
 */
@RestController
@Slf4j
public class TripDealsController {

  private Logger logger = LoggerFactory.getLogger(TripDealsController.class);
  private final TripDealsService tripDealsService;

  private final UserService userService;

  public TripDealsController(TripDealsService tripDealsService,
                             UserService tourGuideService) {
    this.tripDealsService = tripDealsService;
    this.userService = tourGuideService;
  }

  /**
   * getTripDeals. Method that get user
   * tripDeals.
   *
   * @param userName a user name
   * @return providers list
   */
  @RequestMapping("/getTripDeals")
  public String getTripDeals(@RequestParam String userName)
          throws ExecutionException, InterruptedException {

    logger.info("getTripDeals request for user: {} : ", userName);

    List<Provider> providers = tripDealsService.getTripDeals(
            userService.getUser(userName)).get();

    logger.info("getTripDeals response: {} for user: {} : ", providers, userName);

    return JsonStream.serialize(providers);
  }
}
