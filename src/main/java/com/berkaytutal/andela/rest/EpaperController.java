package com.berkaytutal.andela.rest;

import com.berkaytutal.andela.repository.entity.EpaperEntity;
import com.berkaytutal.andela.rest.dto.EpaperRequestXml;
import com.berkaytutal.andela.rest.dto.EpaperResponse;
import com.berkaytutal.andela.service.EpaperService;
import com.querydsl.core.types.Predicate;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("epaper")
public class EpaperController {

  private final EpaperService epaperService;

  @PostMapping
  public void uploadEpaperRequest(@RequestParam("file") MultipartFile file)
      throws IOException, JAXBException {
    try (InputStream is = file.getInputStream()) {
      JAXBContext jaxbContext = JAXBContext.newInstance(EpaperRequestXml.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      EpaperRequestXml epaperRequestXml = (EpaperRequestXml) jaxbUnmarshaller.unmarshal(is);

      epaperService.addEpaper(epaperRequestXml.toModel(file.getOriginalFilename(), new Date()));
    }

  }

  @GetMapping("/{id}")
  public EpaperResponse getEpaper(@PathVariable Long id) {
    return EpaperResponse.from(epaperService.getEpaperById(id));
  }

  @GetMapping
  public Page<EpaperResponse> getEpapers(
      @QuerydslPredicate(root = EpaperEntity.class) Predicate predicate,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {

    return epaperService.findEpapers(predicate, pageable).map(EpaperResponse::from);

  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    epaperService.deleteEpaper(id);
  }

}
