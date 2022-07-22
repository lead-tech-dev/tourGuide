package org.mjtech.tourguide.web.controller;

import com.jsoniter.output.JsonStream;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.mjtech.tourguide.model.user.UserReward;
import org.mjtech.tourguide.web.service.RewardsService;
import org.mjtech.tourguide.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * RewardsController. class that manage
 * request/response logic of rewards.
 */
@RestController
@Slf4j
public class RewardsController {

  private Logger logger = LoggerFactory.getLogger(RewardsController.class);
  private final RewardsService rewardsService;

  private final UserService userService;

  public RewardsController(RewardsService rewardsService, UserService userService) {
    this.rewardsService = rewardsService;
    this.userService = userService;
  }

  /**
   * getRewards. Method that get user
   * rewards.
   *
   * @param userName a user name
   * @return userRewards list
   */
  @RequestMapping("/getRewards")
  public String getRewards(@RequestParam String userName) {

    logger.info("getRewards request for user: {} : ", userName);

    List<UserReward> userRewards = rewardsService.getUserRewards(userService.getUser(userName));

    logger.info("getRewards response: {} for user: {} : ", userRewards, userName);

    return JsonStream.serialize(rewardsService.getUserRewards(userService.getUser(userName)));
  }
}
