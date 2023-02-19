package com.berkaytutal.andela.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.berkaytutal.andela.Utils;
import com.berkaytutal.andela.common.exception.NotFoundException;
import com.berkaytutal.andela.repository.EpaperRepository;
import com.berkaytutal.andela.repository.entity.EpaperEntity;
import com.berkaytutal.andela.service.impl.EpaperServiceImpl;
import com.berkaytutal.andela.service.model.Epaper;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class EpaperServiceTest {

  @Mock
  private EpaperRepository epaperRepository;

  private EpaperService epaperService;

  private Long ID = 1L;

  @BeforeEach
  public void setup() {
    epaperService = new EpaperServiceImpl(epaperRepository);
  }

  @Test
  public void shouldGetEpaperById() {

    Epaper expected = Utils.prepareTestEpaper().build();
    Mockito.when(epaperRepository.findById(expected.getId())).thenReturn(Optional.of(
        expected.toEntity()));

    Epaper actual = epaperService.getEpaperById(expected.getId());
    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  public void shouldThrowResponseStatusException() {
    Epaper expected = Utils.prepareTestEpaper().build();
    Mockito.when(epaperRepository.findById(expected.getId())).thenReturn(Optional.empty());
    Assertions.assertThrows(NotFoundException.class,
        () -> epaperService.getEpaperById(expected.getId()));
  }

  @Test
  public void shouldAddEpaper() {
    Epaper expected = Utils.prepareTestEpaper().build();
    expected.setId(null);
    EpaperEntity te = expected.toEntity();
    Mockito.when(epaperRepository.save(te)).thenReturn(te);

    Epaper actual = epaperService.addEpaper(expected);
    assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  public void shouldDeleteEpaperThrowsException() {
    Mockito.when(epaperRepository.existsById(ID)).thenReturn(false);
    Assertions.assertThrows(NotFoundException.class, () -> epaperService.deleteEpaper(ID));
  }

  @Test
  public void shouldDeleteEpaper() {
    Mockito.when(epaperRepository.existsById(ID)).thenReturn(true);
    Assertions.assertDoesNotThrow(() -> epaperService.deleteEpaper(ID));
  }

  @Test
  void shouldFindEpapersReturnsPage() {
    Predicate predicate = Mockito.mock(Predicate.class);
    Pageable pageable = Mockito.mock(Pageable.class);
    Page<EpaperEntity> expected = Page.empty();
    Mockito.when(epaperRepository.findAll(predicate, pageable)).thenReturn(expected);
    Page<Epaper> actual = epaperService.findEpapers(predicate, pageable);
    assertThat(actual).usingRecursiveComparison().isEqualTo(expected.map(Epaper::from));
  }

}
