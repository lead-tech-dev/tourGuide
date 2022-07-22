package org.mjtech.tourguide.model.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.NoArgsConstructor;
import org.mjtech.tourguide.model.Provider;
import org.mjtech.tourguide.model.VisitedLocation;

/**
 * The User class implements a user
 * entity.
 */
@NoArgsConstructor
public class User {
  private UUID userId;
  private String username;
  private String phoneNumber;
  private String emailAddress;
  private Date latestLocationTimestamp;
  private List<VisitedLocation> visitedLocations = new ArrayList<>();
  private List<UserReward> userRewards = new ArrayList<>();
  private UserPreferences userPreferences;
  private List<Provider> tripDeals = new ArrayList<>();

  /**
   * User. Constructor of user class.
   *
   * @param userId a userId
   * @param userName a userName
   * @param phoneNumber a phoneNumber
   * @param emailAddress an emailAddress
   * @param userPreferences a userPreferences
   */
  public User(UUID userId, String userName,
              String phoneNumber, String emailAddress,
              UserPreferences userPreferences) {
    this.userId = userId;
    this.username = userName;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    this.userPreferences = userPreferences;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setLatestLocationTimestamp(Date latestLocationTimestamp) {
    this.latestLocationTimestamp = latestLocationTimestamp;
  }

  public Date getLatestLocationTimestamp() {
    return latestLocationTimestamp;
  }

  public void addToVisitedLocations(VisitedLocation visitedLocation) {
    visitedLocations.add(visitedLocation);
  }

  public List<VisitedLocation> getVisitedLocations() {
    return visitedLocations;
  }

  public void clearVisitedLocations() {
    visitedLocations.clear();
  }

  /**
   * addUserReward. Method that add userReward.
   *
   * @param userReward a userReward
   */
  public void addUserReward(UserReward userReward) {
    if (userRewards.stream().filter(r ->
            r.attraction.attractionName.equals(
                    userReward.attraction.attractionName)).count() == 0) {
      userRewards.add(userReward);
    }
  }

  public List<UserReward> getUserRewards() {
    return this.userRewards;
  }

  public UserPreferences getUserPreferences() {
    return userPreferences;
  }

  public void setUserPreferences(UserPreferences userPreferences) {
    this.userPreferences = userPreferences;
  }

  public VisitedLocation getLastVisitedLocation() {
    return visitedLocations.get(visitedLocations.size() - 1);
  }

  public void setTripDeals(List<Provider> tripDeals) {
    this.tripDeals = tripDeals;
  }

  public List<Provider> getTripDeals() {
    return tripDeals;
  }

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
            ", userPreferences=" + userPreferences.toString()
            +
            '}';
  }
}
