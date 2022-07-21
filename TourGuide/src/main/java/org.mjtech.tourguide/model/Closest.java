package org.mjtech.tourguide.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
