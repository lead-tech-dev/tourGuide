package org.mjtech.tourguide.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mjtech.tourguide.dto.UserDto;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserPreferences;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tag("ConvertToTest")
@DisplayName("ConvertTo test logic")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConvertToTest {

  @Test
  @DisplayName("convertToUser should return user for given userDto")
  void convertToUser_ShouldReturnUser_ForGivenUserDto() {
    // GIVEN
    UserDto userDto = new UserDto(UUID.randomUUID(), "corbiere", "000", "jon@tourGuide.com", 2, 6);

    // WHEN
    User user = ConvertTo.convertToUser(userDto);
    // THEN
    User expected = new User(userDto.getUserId(), "corbiere", "000", "jon@tourGuide.com", new UserPreferences(2, 6));
    assertThat(user.toString()).isEqualTo(expected.toString());
  }

  @Test
  @DisplayName("convertToUserDto should return userDto for given User")
  void convertToUserDto_ShouldReturnUserDto_ForGivenUser() {
    // GIVEN
    User user = new User(UUID.randomUUID(), "corbiere", "000", "jon@tourGuide.com", new UserPreferences(2, 7));

    // WHEN
    UserDto userDto = ConvertTo.convertToUserDto(user);

    // THEN
    UserDto expected = new UserDto(user.getUserId(), "corbiere", "000", "jon@tourGuide.com", 2, 7);
    assertThat(userDto.toString()).isEqualTo(expected.toString());
  }

  @Test
  @DisplayName("convertToUserListDto should return userDto list for given user list")
  void convertToUserListDto_ShouldReturnUserDtoList_ForGivenUserList() {
    // GIVEN
    UUID userId = UUID.randomUUID();
    List<User> users = List.of(new User(userId, "corbiere", "000", "jon@tourGuide.com", new UserPreferences(2, 7)));

    // WHEN
    List<UserDto> result = ConvertTo.convertToUserListDto(users);

    // THEN
    List<UserDto> expected = List.of( new UserDto(userId, "corbiere", "000", "jon@tourGuide.com", 2, 7));
    assertThat(result.get(0).toString()).isEqualTo(expected.get(0).toString());
  }
}