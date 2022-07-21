package org.mjtech.tourguide.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mjtech.tourguide.model.VisitedLocation;
import org.mjtech.tourguide.model.user.UserPreferences;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

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
    return "User{" +
            "userId=" + userId +
            ", username='" + username + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", emailAddress='" + emailAddress + '\'' +
            ", tripDuration=" + tripDuration +
            ", numberOfChildren=" + numberOfChildren +
            '}';
  }
}
