package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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

@TeleOp(name = "COOLEST teleop")
public abstract class Teleop extends CommandOpMode {
  // FtcDashboard dashboard = FtcDashboard.getInstance();

  private GamepadEx driver, operator;
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
//        LoggingUtil.enableCommandLogging();

    driver = new GamepadEx(gamepad1);
    operator = new GamepadEx(gamepad2);
    //mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap);
    telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
    /*imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);*/
    voltageSubsystem = new VoltageSubsystem(hardwareMap);
   // intakeSubsystem = new IntakeSubsystem(hardwareMap);

    rampDoorSubsystem = new RampDoorSubsystem(hardwareMap, telemetry);
    shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);
    autoCommandFactory = new AutoCommandFactory(mecanumDriveSubsystem, imuSubsystem, telemetry, getAlliance(), voltageSubsystem, shooterSubsystem, rampDoorSubsystem);
    MecanumDriveCommand mecanumDriveCommand = new MecanumDriveCommand(
        mecanumDriveSubsystem,
        () -> driver.getLeftX(),
        () -> driver.getLeftY(),
        () -> driver.getRightX(),
        () -> driver.getButton(GamepadKeys.Button.LEFT_BUMPER),
        () -> driver.getButton(GamepadKeys.Button.A),
        telemetry
    );
    driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem, () -> driver.getButton(GamepadKeys.Button.B), 1, 0, telemetry));
    driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem, () -> driver.getButton(GamepadKeys.Button.B), 0, -1, telemetry));
    driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem, () -> driver.getButton(GamepadKeys.Button.B), 0, 1, telemetry));
    driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whileHeld(new MecanumDpadCommand(mecanumDriveSubsystem, () -> driver.getButton(GamepadKeys.Button.B), -1, 0, telemetry));

   // mecanumDriveSubsystem.setDefaultCommand(mecanumDriveCommand);
    //operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whileHeld(new IntakeCommand(intakeSubsystem));
    //operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileHeld(new OuttakeCommand(intakeSubsystem));
    operator.getGamepadButton(GamepadKeys.Button.A).whenPressed(new RampDoorOpenCommand(rampDoorSubsystem, telemetry));
    operator.getGamepadButton(GamepadKeys.Button.B).whenPressed(new RampDoorCloseCommand(rampDoorSubsystem, telemetry));
    operator.getGamepadButton(GamepadKeys.Button.Y).whileHeld(autoCommandFactory.shootPollen());
    register(telemetryUpdateSubsystem, voltageSubsystem);
  }

  public abstract Alliance getAlliance();
}