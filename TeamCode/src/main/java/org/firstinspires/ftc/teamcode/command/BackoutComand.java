package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.util.Timing;

import org.firstinspires.ftc.teamcode.subsystem.ShooterSubsystem;

import java.util.concurrent.TimeUnit;

public class BackoutComand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private Timing.Timer timey = new Timing.Timer(200, TimeUnit.MILLISECONDS);

    public BackoutComand(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
        timey.start();
    }

    @Override
    public void execute() {
        shooterSubsystem.backout();
    }

    @Override
    public boolean isFinished() {
        return timey.remainingTime() > 0;
    }
}
