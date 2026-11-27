package org.firstinspires.ftc.teamcode.util;

import androidx.annotation.Nullable;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.psilynx.psikit.core.Logger;

public class TelemetryLogger implements Telemetry {
  private final String prefix;

  public TelemetryLogger() {
    this("Telemetry/");
  }


  public TelemetryLogger(String prefix) {
    this.prefix = prefix;
  }

  private void recordValue(String caption, Object value) {
    String key = prefix + caption;
    if (value instanceof Number) {
      Logger.recordOutput(key, ((Number) value).doubleValue());
    } else if (value instanceof Boolean) {
      Logger.recordOutput(key, (Boolean) value);
    } else {
      Logger.recordOutput(key, String.valueOf(value));
    }
  }

  private class LoggyItem implements Item {
    private String caption;
    private boolean retained = false;

    public LoggyItem(String caption) {
      this.caption = caption;
    }

    @Override
    public String getCaption() {
      return caption;
    }

    @Override
    public Item setCaption(String caption) {
      this.caption = caption;
      return this;
    }

    @Override
    public Item setValue(String format, Object... args) {
      recordValue(caption, String.format(format, args));
      return this;
    }

    @Override
    public Item setValue(Object value) {
      recordValue(caption, value);
      return this;
    }

    @Override
    public <T> Item setValue(Func<T> valueProducer) {
      recordValue(caption, valueProducer.value());
      return this;
    }

    @Override
    public <T> Item setValue(String format, Func<T> valueProducer) {
      recordValue(caption, String.format(format, valueProducer.value()));
      return this;
    }

    @Override
    public Item setRetained(@Nullable Boolean retained) {
      this.retained = retained != null && retained;
      return this;
    }

    @Override
    public boolean isRetained() {
      return retained;
    }

    @Override
    public Item addData(String caption, String format, Object... args) {
      return TelemetryLogger.this.addData(caption, format, args);
    }

    @Override
    public Item addData(String caption, Object value) {
      return TelemetryLogger.this.addData(caption, value);
    }

    @Override
    public <T> Item addData(String caption, Func<T> valueProducer) {
      return TelemetryLogger.this.addData(caption, valueProducer);
    }

    @Override
    public <T> Item addData(String caption, String format, Func<T> valueProducer) {
      return TelemetryLogger.this.addData(caption, format, valueProducer);
    }
  }

  @Override
  public Item addData(String caption, String format, Object... args) {
    Logger.recordOutput(prefix + caption, String.format(format, args));
    return new LoggyItem(caption).setValue(format, args);
  }

  @Override
  public Item addData(String caption, Object value) {
    recordValue(caption, value);
    return new LoggyItem(caption).setValue(value);
  }

  @Override
  public <T> Item addData(String caption, Func<T> valueProducer) {
    recordValue(caption, valueProducer.value());
    return new LoggyItem(caption).setValue(valueProducer);
  }

  @Override
  public <T> Item addData(String caption, String format, Func<T> valueProducer) {
    Logger.recordOutput(prefix + caption, String.format(format, valueProducer.value()));
    return new LoggyItem(caption).setValue(format, valueProducer);
  }

  @Override
  public boolean removeItem(Item item) {
    return false;
  }

  @Override
  public void clear() {

  }

  @Override
  public void clearAll() {

  }

  @Override
  public Object addAction(Runnable action) {
    return null;
  }

  @Override
  public boolean removeAction(Object token) {
    return false;
  }

  @Override
  public void speak(String text) {

  }

  @Override
  public void speak(String text, String languageCode, String countryCode) {

  }

  @Override
  public boolean update() {
    return true;
  }

  @Override
  public Line addLine() {
    return null;
  }

  @Override
  public Line addLine(String lineCaption) {
    Logger.recordOutput(prefix + "Lines", lineCaption);
    return null;
  }

  @Override
  public boolean removeLine(Line line) {
    return false;
  }

  @Override
  public boolean isAutoClear() {
    return false;
  }

  @Override
  public void setAutoClear(boolean autoClear) {

  }

  @Override
  public int getMsTransmissionInterval() {
    return 0;
  }

  @Override
  public void setMsTransmissionInterval(int msTransmissionInterval) {

  }

  @Override
  public String getItemSeparator() {
    return " : ";
  }

  @Override
  public void setItemSeparator(String itemSeparator) {

  }

  @Override
  public String getCaptionValueSeparator() {
    return " | ";
  }

  @Override
  public void setCaptionValueSeparator(String captionValueSeparator) {

  }

  @Override
  public void setDisplayFormat(DisplayFormat displayFormat) {

  }

  @Override
  public Log log() {
    return null;
  }
}
