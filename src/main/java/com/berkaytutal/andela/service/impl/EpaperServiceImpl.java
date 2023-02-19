package com.berkaytutal.andela.service.impl;

import com.berkaytutal.andela.common.exception.Errors;
import com.berkaytutal.andela.common.exception.NotFoundException;
import com.berkaytutal.andela.repository.EpaperRepository;
import com.berkaytutal.andela.repository.entity.EpaperEntity;
import com.berkaytutal.andela.service.EpaperService;
import com.berkaytutal.andela.service.model.Epaper;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EpaperServiceImpl implements EpaperService {

  private final EpaperRepository epaperRepository;

  @Override
  public Epaper getEpaperById(Long id) {
    return epaperRepository.findById(id)
        .map(Epaper::from)
        .orElseThrow(() -> new NotFoundException(Errors.EPAPER_NOT_FOUND));
  }

  @Override
  public Epaper addEpaper(Epaper epaper) {
    EpaperEntity epaperEntity = epaper.toEntity();

    epaperEntity = epaperRepository.save(epaperEntity);

    return Epaper.from(epaperEntity);
  }


  @Override
  public Page<Epaper> findEpapers(Predicate predicate, Pageable pageable) {
    return epaperRepository.findAll(predicate, pageable).map(Epaper::from);
  }

  @Override
  public void deleteEpaper(Long id) {
    if (!epaperRepository.existsById(id)) {
      throw new NotFoundException(Errors.EPAPER_NOT_FOUND);
    }
    epaperRepository.deleteById(id);
  }

}
