package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.command.AutoCommandFactory;
import org.firstinspires.ftc.teamcode.command.IntakeCommand;
import org.firstinspires.ftc.teamcode.command.MecanumDpadCommand;
import org.firstinspires.ftc.teamcode.command.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.command.OuttakeCommand;
import org.firstinspires.ftc.teamcode.command.RampDoorCloseCommand;
import org.firstinspires.ftc.teamcode.command.RampDoorOpenCommand;
import org.firstinspires.ftc.teamcode.subsystem.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.RampDoorSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.VoltageSubsystem;
import org.firstinspires.ftc.teamcode.util.Alliance;


@Autonomous(name = "ShootNParkAuto")
public class ShootNParkAuto extends CommandOpMode {


  private MecanumDriveSubsystem mecanumDriveSubsystem;
  private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
  private ImuSubsystem imuSubsystem;
  private AutoCommandFactory autoCommandFactory;
  private VoltageSubsystem voltageSubsystem;
  private IntakeSubsystem intakeSubsystem;
  private RampDoorSubsystem rampDoorSubsystem;
  private ShooterSubsystem shooterSubsystem;

  @Override
  public void initialize() {
    telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
      //LoggingUtil.enableCommandLogging();
    mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap);
    telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
    imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
    voltageSubsystem = new VoltageSubsystem(hardwareMap);
    //intakeSubsystem = new IntakeSubsystem(hardwareMap);

   // rampDoorSubsystem = new RampDoorSubsystem(hardwareMap, telemetry);
   // shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);
    autoCommandFactory = new AutoCommandFactory(mecanumDriveSubsystem, imuSubsystem, telemetry, getAlliance(), voltageSubsystem, shooterSubsystem, rampDoorSubsystem);
    register(telemetryUpdateSubsystem, voltageSubsystem/*rampDoorSubsystem*/);
    Command Bob = autoCommandFactory.shootNPark();
    schedule(Bob);
  }

  public Alliance getAlliance(){
    return Alliance.RED;
  }
}