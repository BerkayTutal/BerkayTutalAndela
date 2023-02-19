package com.berkaytutal.andela.repository.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
// Indexes are added for each column to allow user to search for all the columns.
// In bigger scale systems indexes should be selected carefully to improve insert and delete performance!
@Table(name = "epaper",
    indexes = {
        @Index(columnList = EpaperEntity.COLUMN_NEWSPAPER_NAME),
        @Index(columnList = EpaperEntity.COLUMN_WIDTH),
        @Index(columnList = EpaperEntity.COLUMN_HEIGHT),
        @Index(columnList = EpaperEntity.COLUMN_DPI),
        @Index(columnList = EpaperEntity.COLUMN_UPLOAD_TIME),
        @Index(columnList = EpaperEntity.COLUMN_FILE_NAME)
    }
)
@Entity
public class EpaperEntity {

  public static final String COLUMN_NEWSPAPER_NAME = "newspaperName";
  public static final String COLUMN_WIDTH = "width";
  public static final String COLUMN_HEIGHT = "height";
  public static final String COLUMN_DPI = "dpi";
  public static final String COLUMN_UPLOAD_TIME = "uploadTime";
  public static final String COLUMN_FILE_NAME = "fileName";
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = COLUMN_NEWSPAPER_NAME, nullable = false)
  private String newspaperName;

  @Column(name = COLUMN_WIDTH, nullable = false)
  private int width;

  @Column(name = COLUMN_HEIGHT, nullable = false)
  private int height;

  @Column(name = COLUMN_DPI, nullable = false)
  private int dpi;

  @Column(name = COLUMN_UPLOAD_TIME, nullable = false)
  private Timestamp uploadTime;

  @Column(name = COLUMN_FILE_NAME, nullable = false)
  private String fileName;
}
