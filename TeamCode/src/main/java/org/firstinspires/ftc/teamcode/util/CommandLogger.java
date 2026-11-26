package org.firstinspires.ftc.teamcode.util;

import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.psilynx.psikit.core.Logger;

public class CommandLogger {
  public static void init() {
    final String label = "Command/";
    CommandScheduler.getInstance().onCommandInitialize(command -> {
          Logger.recordOutput(label + command.getName(), "init");
        }
    );
    CommandScheduler.getInstance().onCommandExecute(command -> {
      Logger.recordOutput(label + command.getName(), "execute");
    });
    CommandScheduler.getInstance().onCommandFinish(command -> {
      Logger.recordOutput(label + command.getName(), "end(false)");
    });
    CommandScheduler.getInstance().onCommandInterrupt(command -> {
      Logger.recordOutput(label + command.getName(), "end(true)");
    });
  }
}
