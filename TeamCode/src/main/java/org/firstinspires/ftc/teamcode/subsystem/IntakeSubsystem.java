package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final DcMotorEx intakeTheRickrollMotor;

    public IntakeSubsystem(HardwareMap hm) {
        this.intakeTheRickrollMotor=hm.get(DcMotorEx.class,"intake");
    }
}
