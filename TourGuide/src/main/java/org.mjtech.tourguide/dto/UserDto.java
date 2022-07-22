package org.mjtech.tourguide.dto;

import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The UserDto class implements a variation of user
 * entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private UUID userId;

  @NotBlank
  private String username;

  @NotBlank
  private String phoneNumber;

  @NotBlank
  @Email
  private String emailAddress;

  @NotNull
  private int tripDuration;

  @NotNull
  private int numberOfChildren;

  @Override
  public String toString() {
    return "User{"
            +
            "userId=" + userId
            +
            ", username='" + username + '\''
            +
            ", phoneNumber='" + phoneNumber + '\''
            +
            ", emailAddress='" + emailAddress + '\''
            +
            ", tripDuration=" + tripDuration
            +
            ", numberOfChildren=" + numberOfChildren
            +
            '}';
  }
}
