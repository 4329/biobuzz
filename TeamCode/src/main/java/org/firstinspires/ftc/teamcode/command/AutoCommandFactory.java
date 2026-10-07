package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystem.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.VoltageSubsystem;
import org.firstinspires.ftc.teamcode.util.Alliance;

public class AutoCommandFactory {
  private final MecanumDriveSubsystem mecanumDriveSubsystem;
  private final Telemetry telemetry;
  private final ImuSubsystem imuSubsystem;
  private final Alliance alliance;
  private final VoltageSubsystem voltageSubsystem;

  public AutoCommandFactory(MecanumDriveSubsystem mecanumDriveSubsystem, ImuSubsystem imuSubsystem, Telemetry telemetry, Alliance alliance, VoltageSubsystem voltageSubsystem) {
    this.mecanumDriveSubsystem = mecanumDriveSubsystem;
    this.imuSubsystem = imuSubsystem;
    this.telemetry = telemetry;
    this.alliance = alliance;
    this.voltageSubsystem = voltageSubsystem;
  }

  public Command exampleCommand() {
    return new SequentialCommandGroup(
        forward(11, 0),
        new TurnToHeadingCommand(mecanumDriveSubsystem, imuSubsystem, telemetry, 45),
        forward(5, 45)
    );
  }

  private Command backUp(double inches, double heading) {
    return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, -0.35, 0, 0, inches);
  }

  public Command forward(double inches, double heading) {
    return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0.35, 0, 0, inches);
  }

  public Command allianceForward(double inches, double heading) {
    return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0.35 * alliance.value, 0, 0, inches);
  }

  private Command slowForward(double inches, double heading) {
    return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, -0.2, 0, 0, inches);
  }

  public Command lMove(double inches, double heading) {
    return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0, 0, -.21, inches);
  }

  public Command lFastMove(double inches, double heading) {
    return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0, 0, -.7, inches);
  }

  public Command rMove(double inches, double heading) {
    return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0, 0, .21, inches);
  }

  private Command rFastMove(double inches, double heading) {
    return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, heading, 0, 0, .7, inches);
  }

  public Command strafeToYourLou() {
    return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, 0, 0, 0, .3, 26);
  }

  public Command moveAwayFromYourLou() {
    return new EncoderDriveCommand(mecanumDriveSubsystem, imuSubsystem, 0, .3 * alliance.value, 0, 0, 40);
  }

}