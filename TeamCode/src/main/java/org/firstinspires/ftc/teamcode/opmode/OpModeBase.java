package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.CommandLogger;
import org.firstinspires.ftc.teamcode.util.TelemetryLogger;
import org.psilynx.psikit.core.Logger;
import org.psilynx.psikit.ftc.FtcLoggingSession;
import org.psilynx.psikit.ftc.autolog.PsiKitAutoLogSettings;

public abstract class OpModeBase extends CommandOpMode {
  TelemetryUpdateSubsystem telemetryUpdateSubsystem;
  private final FtcLoggingSession logginSession = new FtcLoggingSession();

  @Override
  public void initialize() {
    PsiKitAutoLogSettings.enabledByDefault = true;
    logginSession.start(this, 5000);
    CommandLogger.init();

    telemetry = new MultipleTelemetry(telemetry, new TelemetryLogger(), FtcDashboard.getInstance().getTelemetry());
    telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);

    register(telemetryUpdateSubsystem);

    setupOpMode();
  }

  @Override
  public void run() {
    double beforeStart = Logger.getRealTimestamp();
    Logger.periodicBeforeUser();
    logginSession.logOncePerLoop(this);

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
      logginSession.end();
    }
  }

  @Override
  public void end() {
    super.end();
    logginSession.end();
  }

  public abstract void setupOpMode();

  public abstract Alliance getAlliance();
}
