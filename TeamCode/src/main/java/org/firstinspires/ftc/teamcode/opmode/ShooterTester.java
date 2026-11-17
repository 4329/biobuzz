package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.command.ShooterCommand;
import org.firstinspires.ftc.teamcode.subsystem.BulkCacheSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;
import org.psilynx.psikit.core.Logger;

@Utility(name = "ST teste")
public class ShooterTester extends CommandOpMode {
  private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
  private GamepadEx operator;
  private ShooterSubsystem shooterSubsystem;
  private ShooterCommand shooterCommand;


  @Override
  public void initialize() {
    telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);
    shooterCommand = new ShooterCommand(shooterSubsystem);
    telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);

    operator = new GamepadEx(gamepad2);
    operator.getGamepadButton(GamepadKeys.Button.A).whileHeld(shooterCommand);

    Logger.start();


    register(new BulkCacheSubsystem(hardwareMap), telemetryUpdateSubsystem);
  }

  @Override
  public void run() {
    long beforeStart = System.nanoTime() / 1000L;
    Logger.periodicBeforeUser();
    long periodicBeforeLength = (System.nanoTime() / 1000L) - beforeStart;

    long userStart = System.nanoTime() / 1000L;
    super.run();
    long userCodeLength = (System.nanoTime() / 1000L) - userStart;

    Logger.periodicAfterUser(userCodeLength, periodicBeforeLength);
  }

  @Override
  public void end() {
    super.end();
    Logger.end();
  }
}
