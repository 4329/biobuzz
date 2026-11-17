package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import java.util.List;

public class BulkCacheSubsystem extends SubsystemBase {
  private final List<LynxModule> allHubs;

  public BulkCacheSubsystem(HardwareMap hardwareMap) {
    // Grab all Control and Expansion hubs and set them to MANUAL mode
    allHubs = hardwareMap.getAll(LynxModule.class);
/*    for (LynxModule hub : allHubs) {
      hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
    } */
  }

  @Override
  public void periodic() {
    // Automatically called once per loop by CommandScheduler before commands execute
    for (LynxModule hub : allHubs) {
      hub.clearBulkCache();
    }
  }
}