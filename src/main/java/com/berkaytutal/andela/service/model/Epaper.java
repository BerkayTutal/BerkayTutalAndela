package com.berkaytutal.andela.service.model;

import com.berkaytutal.andela.repository.entity.EpaperEntity;
import java.sql.Timestamp;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Epaper {

  private Long id;

  private String newspaperName;

  private int width;

  private int height;

  private int dpi;

  private Date uploadTime;

  private String fileName;

  public static Epaper from(EpaperEntity epaperEntity) {
    return Epaper.builder()
        .id(epaperEntity.getId())
        .newspaperName(epaperEntity.getNewspaperName())
        .width(epaperEntity.getWidth())
        .height(epaperEntity.getHeight())
        .dpi(epaperEntity.getDpi())
        .uploadTime(epaperEntity.getUploadTime())
        .fileName(epaperEntity.getFileName())
        .build();
  }

  public EpaperEntity toEntity() {
    return EpaperEntity.builder()
        .id(id)
        .newspaperName(newspaperName)
        .width(width)
        .height(height)
        .dpi(dpi)
        .uploadTime(Timestamp.from(uploadTime.toInstant()))
        .fileName(fileName)
        .build();

  }

}
