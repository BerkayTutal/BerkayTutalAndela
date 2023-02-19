package com.berkaytutal.andela.rest;

import static org.assertj.core.api.Assertions.assertThat;

import com.berkaytutal.andela.Utils;
import com.berkaytutal.andela.rest.dto.EpaperResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EpaperControllerTest {

  private final Long EPAPER_ID = 2L;
  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  @Sql(value = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = "/truncate.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  public void shouldGetEpaperDetails() {
    ResponseEntity<EpaperResponse> responseEntity = testRestTemplate.exchange(
        "/epaper/" + EPAPER_ID,
        HttpMethod.GET, null, EpaperResponse.class);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody().getId()).isEqualTo(EPAPER_ID);
    assertThat(responseEntity.getBody().getDpi()).isEqualTo(Utils.DPI);
    assertThat(responseEntity.getBody().getWidth()).isEqualTo(Utils.WIDTH);
    assertThat(responseEntity.getBody().getHeight()).isEqualTo(Utils.HEIGHT);
    assertThat(responseEntity.getBody().getNewspaperName()).isEqualTo(Utils.NEWSPAPER_NAME_1);
    assertThat(responseEntity.getBody().getFileName()).isEqualTo(Utils.FILENAME_1_XML);
    assertThat(responseEntity.getBody().getUploadTime()).isEqualTo(Utils.UPLOAD_TIME);
  }

  @Test
  @Sql(value = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = "/truncate.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  public void shouldDeleteEpaper() {
    ResponseEntity<EpaperResponse> responseEntity = testRestTemplate.exchange(
        "/epaper/" + EPAPER_ID,
        HttpMethod.DELETE, null, EpaperResponse.class);
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  @Sql(value = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = "/truncate.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  public void shouldNotDeleteUserDoesNotExists() {
    ResponseEntity<EpaperResponse> responseEntity = testRestTemplate.exchange("/epaper/" + 999999,
        HttpMethod.DELETE, null, EpaperResponse.class);
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

}
