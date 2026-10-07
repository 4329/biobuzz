package org.firstinspires.ftc.teamcode.util;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class RobotConfig {
  public static double REGULAR_DRIVE_DIVISOR = 2.0;
  public static double SLOW_DRIVE_DIVISOR = 5.0;

  // maybe increase
  public static double EXPECTED_BATTERY_VOLTAGE = 12.5;
}