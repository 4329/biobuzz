package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.command.RampDoorCloseCommand;
import org.firstinspires.ftc.teamcode.command.RampDoorOpenCommand;
import org.firstinspires.ftc.teamcode.subsystem.RampDoorSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;

@Utility(name = "Rd teste")
public class RampDoorTest extends CommandOpMode {
  private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
  private GamepadEx operator;
  private RampDoorSubsystem rampDoorSubsystem;
  private RampDoorOpenCommand rampDoorOpenCommand;
  private RampDoorCloseCommand rampDoorClosedCommand;

  @Override
  public void initialize() {
    telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    rampDoorSubsystem = new RampDoorSubsystem(hardwareMap);
    telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
    rampDoorOpenCommand = new RampDoorOpenCommand(rampDoorSubsystem, telemetry);
    rampDoorClosedCommand = new RampDoorCloseCommand(rampDoorSubsystem, telemetry);


    operator = new GamepadEx(gamepad2);
    operator.getGamepadButton(GamepadKeys.Button.A).whenPressed(rampDoorOpenCommand);
    operator.getGamepadButton(GamepadKeys.Button.B).whenPressed(rampDoorClosedCommand);
    register(telemetryUpdateSubsystem);
  }
}
