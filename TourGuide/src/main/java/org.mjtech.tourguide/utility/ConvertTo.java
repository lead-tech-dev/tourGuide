package org.mjtech.tourguide.utility;

import java.util.List;
import java.util.stream.Collectors;
import org.mjtech.tourguide.dto.UserDto;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserPreferences;


/**
 * ConvertTo. a class that implement
 * entity and dto conversion logic.
 */
public class ConvertTo {

  /**
   * convertToUser. Method that convert userDto
   * to user.
   *
   * @param userDto a userDto
   * @return User
   */
  public static User convertToUser(UserDto userDto) {
    User user = new User();

    user.setUserId(userDto.getUserId());
    user.setUsername(userDto.getUsername());
    user.setPhoneNumber(userDto.getPhoneNumber());
    user.setEmailAddress(userDto.getEmailAddress());
    user.setUserPreferences(new UserPreferences(
            userDto.getTripDuration(),
            userDto.getNumberOfChildren())
    );

    System.out.println(user);
    return user;
  }

  /**
   * convertToUserDto. Method that convert user
   * to userDto.
   *
   * @param user a user
   * @return UserDto
   */
  public static UserDto convertToUserDto(User user) {
    return new UserDto(
            user.getUserId(),
            user.getUsername(),
            user.getPhoneNumber(),
            user.getEmailAddress(),
            user.getUserPreferences().getTripDuration(),
            user.getUserPreferences().getNumberOfChildren());
  }

  /**
   * convertToUserListDto. Method that convert user list
   * to userDto list.
   *
   * @param users a users
   * @return UserDto list
   */
  public static List<UserDto> convertToUserListDto(List<User> users) {
    return users.stream().map(user -> new UserDto(
            user.getUserId(),
            user.getUsername(),
            user.getPhoneNumber(),
            user.getEmailAddress(),
            user.getUserPreferences().getTripDuration(),
            user.getUserPreferences().getNumberOfChildren())).collect(Collectors.toList());
  }

}
