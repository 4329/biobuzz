package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.command.ShooterCommand;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;
import org.firstinspires.ftc.teamcode.util.CommandLogger;
import org.firstinspires.ftc.teamcode.util.TelemetryLogger;
import org.psilynx.psikit.core.Logger;
import org.psilynx.psikit.ftc.FtcLoggingSession;
import org.psilynx.psikit.ftc.autolog.PsiKitAutoLogSettings;

@Utility(name = "ST teste")
public class ShooterTester extends CommandOpMode {
  private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
  private GamepadEx operator;
  private ShooterSubsystem shooterSubsystem;
  private final FtcLoggingSession psiKitSession = new FtcLoggingSession();


  @Override
  public void initialize() {
    PsiKitAutoLogSettings.enabledByDefault = true;
    psiKitSession.start(this, 5000);
    CommandLogger.init();

    telemetry = new MultipleTelemetry(telemetry, new TelemetryLogger(), FtcDashboard.getInstance().getTelemetry());
    shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);
    telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);

    operator = new GamepadEx(gamepad2);
    operator.getGamepadButton(GamepadKeys.Button.A).whileHeld(new ShooterCommand(shooterSubsystem));

    register(telemetryUpdateSubsystem, shooterSubsystem);
  }

  @Override
  public void run() {
    double beforeStart = Logger.getRealTimestamp();
    Logger.periodicBeforeUser();
    psiKitSession.logOncePerLoop(this);

    double beforeUserEnd = Logger.getRealTimestamp();

    super.run();

    double afterUserStart = Logger.getRealTimestamp();

    Logger.periodicAfterUser(afterUserStart - beforeUserEnd, beforeUserEnd - beforeStart);
  }

  @Override
  public void reset() {
    try {
      super.reset();
    } finally {
      psiKitSession.end();
    }
  }

  @Override
  public void end() {
    super.end();
    psiKitSession.end();
  }
}
