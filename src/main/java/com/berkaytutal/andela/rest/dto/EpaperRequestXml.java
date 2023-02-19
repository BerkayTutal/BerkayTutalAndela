package com.berkaytutal.andela.rest.dto;

import com.berkaytutal.andela.service.model.Epaper;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "epaperRequest")
@Getter
public class EpaperRequestXml {

  @XmlElement
  private DeviceInfo deviceInfo;

  @XmlElement
  private GetPages getPages;

  public Epaper toModel(String fileName, Date uploadTime) {
    return toModel(null, fileName, uploadTime);
  }

  public Epaper toModel(Long id, String fileName, Date uploadTime) {
    return Epaper.builder()
        .id(id)
        .width(deviceInfo.getScreenInfo().getWidth())
        .height(deviceInfo.getScreenInfo().getHeight())
        .dpi(deviceInfo.getScreenInfo().getDpi())
        .newspaperName(getDeviceInfo().getAppInfo().getNewspaperName())
        .fileName(fileName)
        .uploadTime(uploadTime)
        .build();
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @Getter
  public static class GetPages {

    @XmlAttribute
    private int editionDefId;

    @XmlAttribute
    private Date publicationDate;

  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @Getter
  public static class OsInfo {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String version;

  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @Getter
  public static class DeviceInfo {

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String name;

    @XmlElement
    private ScreenInfo screenInfo;

    @XmlElement
    private OsInfo osInfo;

    @XmlElement
    private AppInfo appInfo;

    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    public static class AppInfo {

      @XmlElement
      private String newspaperName;

      @XmlElement
      private String version;

      // Getters and Setters
    }


  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @Getter
  public static class ScreenInfo {

    @XmlAttribute
    private int width;

    @XmlAttribute
    private int height;

    @XmlAttribute
    private int dpi;
  }

}
