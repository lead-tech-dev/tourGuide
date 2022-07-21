package org.mjtech.tourguide.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.model.user.UserPreferences;
import org.mjtech.tourguide.web.exception.NotFoundException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tag("UserRepositoryTest")
@DisplayName("User repository test logic")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {


  private UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    userRepository = new UserRepository();
  }

  @Test
  @DisplayName("findByUsername, should return user for given username")
  void findByUsername_ShouldReturnUser_ForGivenUsername() {
    // GIVEN
    String username = "internalUser1";

    // WHEN
    User user = userRepository.findByUsername(username);

    // THEN
    assertThat(user.getUsername()).isEqualTo(username);
  }

  @Test
  @DisplayName("findById, should throw notFoundException for given wrong userId")
  void findById_ShouldThrowNotFoundException_ForGivenWrongUserId() {
    // GIVEN
    UUID userId = UUID.randomUUID();

    // WHEN
    // THEN
    assertThrows(NotFoundException.class, () -> userRepository.findById(userId));
  }

  @Test
  @DisplayName("findById, should return user for given userId")
  void findById_ShouldReturnUser_ForGivenUserId() {
    // GIVEN
    UUID userId = UUID.randomUUID();
    User newUser = new User(
            userId,
            "internalUser100",
            "00",
            "internalUser100@yahoo.fr",
            new UserPreferences(1, 4));
    User user = userRepository.save(newUser);

    // WHEN
    UUID result = userRepository.findById(userId).getUserId();

    // THEN
    assertThat(result).isEqualTo(userId);
  }

  @Test
  @DisplayName("findAll, should return all user")
  void findAll_ShouldReturnAllUser() {
    // GIVEN
    // WHEN
    List<User> users = userRepository.findAll();

    // THEN
    assertTrue(users.size() > 0);
    assertThat(users.get(0).getUsername()).isEqualTo("internalUser78");
  }

  @Test
  @DisplayName("save, should return saved user for given user")
  void save_ShouldReturnSavedUser_ForGivenUser() {
    // GIVEN
    UUID userId = UUID.randomUUID();
    User user = new User(
            userId,
            "internalUser124",
            "00",
            "internalUser124@yahoo.fr",
            new UserPreferences(1, 4));

    // WHEN
    User result = userRepository.save(user);

    // THEN
    assertThat(result.getUsername()).isEqualTo("internalUser124");
  }

  @Test
  @DisplayName("remove, should delete user for given user")
  void remove_ShouldDeleteUser_ForGivenUser() {
    // GIVEN
    UUID userId = UUID.randomUUID();
    User user = new User(
            userId,
            "internalUser114",
            "00",
            "internalUser114@yahoo.fr",
            new UserPreferences(1, 4));
    User savedUser = userRepository.save(user);

    // WHEN
    userRepository.remove(savedUser);

    // THEN
    assertThrows(NotFoundException.class, () -> userRepository.findById(userId));
  }

  @Test
  @DisplayName("checkExistUser, should return false for given not exist username")
  void checkExistUser_ShouldReturnFalse_ForGivenNotExistUsername() {
    // GIVEN
    String username = "internalUser11415678";

    // WHEN
    boolean result = userRepository.checkExistUser(username);

    // THEN
    assertThat(result).isEqualTo(false);
  }

  @Test
  @DisplayName("checkExistUser, should return  true for given exist username")
  void checkExistUser_ShouldReturnTrue_ForGivenExistUsername() {
    // GIVEN
    String username = "internalUser78";

    // WHEN
    boolean result = userRepository.checkExistUser(username);

    // THEN
    assertThat(result).isEqualTo(true);
  }
}