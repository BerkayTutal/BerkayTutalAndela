package com.berkaytutal.andela.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.berkaytutal.andela.Utils;
import com.berkaytutal.andela.repository.entity.EpaperEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EpaperRepositoryTest {

  @Autowired
  private EpaperRepository epaperRepository;

  @Test
  public void shouldAddTimezone() {
    EpaperEntity expected = Utils.prepareEpaperEntity().build();

    EpaperEntity actual = epaperRepository.save(expected);

    assertThat(actual).usingRecursiveComparison().ignoringFields(Utils.FIELD_ID).isEqualTo(expected);
  }

  @Test
  public void shouldFindById() {
    EpaperEntity expectedUser = Utils.prepareEpaperEntity().build();
    EpaperEntity actualUser = epaperRepository.save(expectedUser);
    Assertions.assertTrue(epaperRepository.existsById(actualUser.getId()));
  }
}
