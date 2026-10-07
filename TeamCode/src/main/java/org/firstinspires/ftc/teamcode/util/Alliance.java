package org.firstinspires.ftc.teamcode.util;

public enum Alliance {
  RED(-1),
  BLUE(1);

  public final int value;

  private Alliance(int val) {
    this.value = val;
  }
}
