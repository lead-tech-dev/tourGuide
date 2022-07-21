package org.mjtech.tourguide.web.controller;

import com.jsoniter.output.JsonStream;
import lombok.extern.slf4j.Slf4j;
import org.mjtech.tourguide.model.Provider;
import org.mjtech.tourguide.web.service.UserService;
import org.mjtech.tourguide.web.service.TripDealsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TripDealsController {

  private final TripDealsService tripDealsService;

  private final UserService userService;

  public TripDealsController(TripDealsService tripDealsService, UserService tourGuideService) {
    this.tripDealsService = tripDealsService;
    this.userService = tourGuideService;
  }

  @RequestMapping("/getTripDeals")
  public String getTripDeals(@RequestParam String userName) {
    List<Provider> providers = tripDealsService.getTripDeals(userService.getUser(userName));
    return JsonStream.serialize(providers);
  }
}
