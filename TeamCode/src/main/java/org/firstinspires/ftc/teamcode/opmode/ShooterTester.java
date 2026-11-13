package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.command.RampDoorCloseCommand;
import org.firstinspires.ftc.teamcode.command.RampDoorOpenCommand;
import org.firstinspires.ftc.teamcode.command.ShooterCommand;
import org.firstinspires.ftc.teamcode.subsystem.RampDoorSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;

@Utility(name = "ST teste")
public class ShooterTester extends CommandOpMode {
    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private GamepadEx operator;
    private ShooterSubsystem shooterSubsystem;


    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        shooterSubsystem = new ShooterSubsystem(hardwareMap,telemetry);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);

        operator = new GamepadEx(gamepad2);
        operator.getGamepadButton(GamepadKeys.Button.A).whileHeld(new ShooterCommand(shooterSubsystem));
        register(telemetryUpdateSubsystem);
    }
}
