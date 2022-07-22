package org.mjtech.tourguide.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mjtech.tourguide.model.Location;

/**
 * The Closest class implements a Closest
 * model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Closest {
  private String attractionName;

  private Location touristAttraction;

  private Location userLocation;

  private double distance;

  private int rewardPoints;

}
