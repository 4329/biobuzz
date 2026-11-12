package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class RampDoorSubsystem extends SubsystemBase {
  public static Double DOOROPEN = .9;
  public static Double DOORCLOSED = 0d;
  private Servo kingBobvexethe5thsShooterDoor;
  private final Telemetry telemetry;

  public RampDoorSubsystem(HardwareMap hm, Telemetry telemetry) {
    this.kingBobvexethe5thsShooterDoor = hm.get(Servo.class, "sdoor");
    this.telemetry = telemetry;
    closed();
  }

  public void open() {
    kingBobvexethe5thsShooterDoor.setPosition(DOOROPEN);
    telemetry.addData("Opena", DOOROPEN);
  }

  public void closed() {
    kingBobvexethe5thsShooterDoor.setPosition(DOORCLOSED);
    telemetry.addData("Closa", DOORCLOSED);
  }
}
