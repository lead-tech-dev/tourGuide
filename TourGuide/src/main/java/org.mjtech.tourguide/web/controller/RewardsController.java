package org.mjtech.tourguide.web.controller;

import com.jsoniter.output.JsonStream;
import lombok.extern.slf4j.Slf4j;
import org.mjtech.tourguide.web.service.RewardsService;
import org.mjtech.tourguide.web.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RewardsController {

  private final RewardsService rewardsService;

  private final UserService userService;

  public RewardsController(RewardsService rewardsService, UserService userService) {
    this.rewardsService = rewardsService;
    this.userService = userService;
  }

  @RequestMapping("/getRewards")
  public String getRewards(@RequestParam String userName) {
    return JsonStream.serialize(rewardsService.getUserRewards(userService.getUser(userName)));
  }
}
