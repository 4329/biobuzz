package org.firstinspires.ftc.teamcode.opmode;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.command.AutoCommandFactory;
import org.firstinspires.ftc.teamcode.command.MecanumDpadCommand;
import org.firstinspires.ftc.teamcode.command.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.subsystem.ImuSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.VoltageSubsystem;
import org.firstinspires.ftc.teamcode.util.Alliance;

public abstract class Teleop extends CommandOpMode {
  // FtcDashboard dashboard = FtcDashboard.getInstance();

  private GamepadEx driver, operator;
  private MecanumDriveSubsystem mecanumDriveSubsystem;
  private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
  private ImuSubsystem imuSubsystem;
  private AutoCommandFactory autoCommandFactory;
  private VoltageSubsystem voltageSubsystem;


  @Override
  public void initialize() {
    telemetry = new JoinedTelemetry(telemetry, PanelsTelemetry.INSTANCE.getFtcTelemetry());
//        LoggingUtil.enableCommandLogging();

    driver = new GamepadEx(gamepad1);
    operator = new GamepadEx(gamepad2);
    mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap);
    telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
    imuSubsystem = new ImuSubsystem(hardwareMap, telemetry);
    voltageSubsystem = new VoltageSubsystem(hardwareMap);
    autoCommandFactory = new AutoCommandFactory(mecanumDriveSubsystem, imuSubsystem, telemetry, getAlliance(), voltageSubsystem);

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

    mecanumDriveSubsystem.setDefaultCommand(mecanumDriveCommand);
    register(telemetryUpdateSubsystem, voltageSubsystem);
  }

  public abstract Alliance getAlliance();
}