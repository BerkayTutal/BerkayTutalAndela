package com.berkaytutal.andela.rest.dto;

import com.berkaytutal.andela.service.model.Epaper;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class EpaperResponse {

  private Long id;

  private String newspaperName;

  private int width;

  private int height;

  private int dpi;

  private Date uploadTime;

  private String fileName;

  public static EpaperResponse from(Epaper epaper) {
    return EpaperResponse.builder()
        .id(epaper.getId())
        .newspaperName(epaper.getNewspaperName())
        .width(epaper.getWidth())
        .height(epaper.getHeight())
        .dpi(epaper.getDpi())
        .fileName(epaper.getFileName())
        .uploadTime(epaper.getUploadTime())
        .build();
  }
}
