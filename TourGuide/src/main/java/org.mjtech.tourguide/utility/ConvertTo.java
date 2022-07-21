package org.mjtech.tourguide.utility;

import org.mjtech.tourguide.dto.UserDto;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserPreferences;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertTo {

  public static User convertToUser(UserDto userDto) {
    User user = new User();

    user.setUserId(userDto.getUserId());
    user.setUsername(userDto.getUsername());
    user.setPhoneNumber(userDto.getPhoneNumber());
    user.setEmailAddress(userDto.getEmailAddress());
    user.setUserPreferences(new UserPreferences(userDto.getTripDuration(), userDto.getNumberOfChildren()));

    System.out.println(user);
    return user;
  }

  public static UserDto convertToUserDto(User user) {
    return new UserDto(
            user.getUserId(),
            user.getUsername(),
            user.getPhoneNumber(),
            user.getEmailAddress(),
            user.getUserPreferences().getTripDuration(),
            user.getUserPreferences().getNumberOfChildren());
  }

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
