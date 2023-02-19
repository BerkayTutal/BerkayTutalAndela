package com.berkaytutal.andela.service;

import com.berkaytutal.andela.service.model.Epaper;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EpaperService {

  Epaper getEpaperById(Long id);

  Epaper addEpaper(Epaper epaper);

  void deleteEpaper(Long id);

  Page<Epaper> findEpapers(Predicate predicate, Pageable pageable);
}
