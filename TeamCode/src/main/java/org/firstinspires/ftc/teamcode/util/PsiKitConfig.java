package org.firstinspires.ftc.teamcode.util;

import android.content.Context;

import com.qualcomm.ftccommon.FtcEventLoop;

import org.psilynx.psikit.ftc.autolog.PsiKitAutoLogSettings;

public final class PsiKitConfig {
  // disable for now... @OnCreateEventLoop
  public static void configure(Context context, FtcEventLoop eventLoop) {
    PsiKitAutoLogSettings.enabledByDefault = true;
    PsiKitAutoLogSettings.enableLinearByDefault = true;
  }
}
