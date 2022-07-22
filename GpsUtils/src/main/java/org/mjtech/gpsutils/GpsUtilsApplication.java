package org.mjtech.gpsutils;

import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * GpsUtilsApplication. Main application
 * class.
 *
 */
@SpringBootApplication
public class GpsUtilsApplication {
  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    SpringApplication.run(GpsUtilsApplication.class, args);
  }

}
