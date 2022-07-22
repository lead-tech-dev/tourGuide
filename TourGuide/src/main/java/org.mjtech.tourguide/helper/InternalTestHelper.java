package org.mjtech.tourguide.helper;

/**
 * InternalTestHelper. class that handle
 * internal test helper
 */
public class InternalTestHelper {

  // Set this default up to 100,000 for testing
  private static int internalUserNumber = 100;

  /**
   * setInternalUserNumber. Method that set internal
   * user's number.
   *
   * @param internalUserNumber internal user's number
   */
  public static void setInternalUserNumber(int internalUserNumber) {

    InternalTestHelper.internalUserNumber = internalUserNumber;
  }

  /**
   * getInternalUserNumber. Method that get
   * internal user's number.
   *
   * @return internalUserNumber
   */
  public static int getInternalUserNumber() {
    return internalUserNumber;
  }
}
