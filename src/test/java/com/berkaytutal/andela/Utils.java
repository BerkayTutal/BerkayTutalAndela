package com.berkaytutal.andela;

import com.berkaytutal.andela.repository.entity.EpaperEntity;
import com.berkaytutal.andela.service.model.Epaper;
import java.sql.Timestamp;
import java.time.Instant;

public class Utils {

  public static final String FILENAME_1_XML = "filename_2.xml";
  public static final String NEWSPAPER_NAME_1 = "newspapername_2";
  public static final long EPOCH_MILLI = 1676340978773L;
  public static final Timestamp UPLOAD_TIME = Timestamp.from(Instant.ofEpochMilli(EPOCH_MILLI));
  public static final int WIDTH = 1300;
  public static final int HEIGHT = 1100;
  public static final int DPI = 200;
  public static final Long ID = 1L;

  public static String FIELD_ID = "id";

  public static EpaperEntity.EpaperEntityBuilder prepareEpaperEntity() {
    return EpaperEntity.builder()
        .id(ID)
        .dpi(DPI)
        .fileName(FILENAME_1_XML)
        .newspaperName(NEWSPAPER_NAME_1)
        .uploadTime(UPLOAD_TIME)
        .width(WIDTH)
        .height(HEIGHT);
  }

  public static Epaper.EpaperBuilder prepareTestEpaper() {
    return Epaper.builder()
        .id(ID)
        .dpi(DPI)
        .fileName(FILENAME_1_XML)
        .newspaperName(NEWSPAPER_NAME_1)
        .uploadTime(UPLOAD_TIME)
        .width(WIDTH)
        .height(HEIGHT);
  }

}
