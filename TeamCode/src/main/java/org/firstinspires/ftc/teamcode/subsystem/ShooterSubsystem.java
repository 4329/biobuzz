package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.MathUtil;

@Config
public class ShooterSubsystem extends SubsystemBase {
  private final MotorEx fireThePollen;
  private final Telemetry telemetry;
  private SimpleMotorFeedforward feedForward;
  private PIDController sPID;

  public static Double N_FEET_FORWARD_S = 0.012;
  public static Double N_FEET_FORWARD_V = 0.0;
  public static Double N_P = 0.0;
  public static Double N_I = 0.0;
  public static Double N_D = 0.0;
  public static Double SETPOINT = 2000.0;

  private boolean running = false;

  public ShooterSubsystem(HardwareMap hm, Telemetry telemetry) {
    fireThePollen = new MotorEx(hm, "pshooter");
    this.telemetry = telemetry;
    feedForward = new SimpleMotorFeedforward(N_FEET_FORWARD_S, N_FEET_FORWARD_V);
    sPID = new PIDController(N_P, N_I, N_D);
    sPID.setSetPoint(SETPOINT);

    fireThePollen.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
    fireThePollen.setRunMode(Motor.RunMode.RawPower);
  }

  public void stop() {
    running = false;
    fireThePollen.stopMotor();
  }

  public void shoot() {
    running = true;
    sPID.setPID(N_P, N_I, N_D);
    sPID.setSetPoint(SETPOINT);
    feedForward = new SimpleMotorFeedforward(N_FEET_FORWARD_S, N_FEET_FORWARD_V);
  }

  @Override
  public void periodic() {
    if (running) {
      double currentVelocity = fireThePollen.getCorrectedVelocity();
      double ffOutput = feedForward.calculate(Math.max(currentVelocity, 0.01), fireThePollen.getAcceleration());
      double pidOutput = sPID.calculate(currentVelocity);
      double clamped = MathUtil.clamp(ffOutput + pidOutput, -1, 1);

      fireThePollen.set(clamped);

      telemetry.addData("clamped", clamped);
      telemetry.addData("velocity", currentVelocity);
      telemetry.addData("setpoints", sPID.getSetPoint());
      telemetry.addData("ffoutput", ffOutput);
      telemetry.addData("running", true);
    } else {
      telemetry.addData("running", false);
      telemetry.addData("clamped", 0);
      telemetry.addData("velocity", 0);
      telemetry.addData("setpoints", sPID.getSetPoint());
      telemetry.addData("ffoutput", 0);
    }
  }
}
