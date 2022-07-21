package org.mjtech.gpsutils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class GpsUtilsApplication {
  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    SpringApplication.run(GpsUtilsApplication.class, args);
  }

}
