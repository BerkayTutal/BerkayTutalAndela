package com.berkaytutal.andela.repository;

import com.berkaytutal.andela.repository.entity.EpaperEntity;
import com.berkaytutal.andela.repository.entity.QEpaperEntity;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface EpaperRepository extends JpaRepository<EpaperEntity, Long>,
    QuerydslPredicateExecutor<EpaperEntity>,
    QuerydslBinderCustomizer<QEpaperEntity> {

  int MIN_VALUE_SIZE = 1;
  int INDEX_PARAM_FIRST = 0;
  int INDEX_PARAM_SECOND = 1;

  default void customize(final QuerydslBindings bindings, final QEpaperEntity foo) {
    /**
     *  Filter by dpi, width and height.
     *  If only one parameter is given, filter by "equals"
     *  If two parameters are given, look for "between"
     */
    bindings.bind(foo.dpi, foo.width, foo.height).all((path, value) -> {

      List<? extends Integer> values = new ArrayList<>(value);
      if (values.size() == MIN_VALUE_SIZE) {
        return Optional.of(path.eq(values.get(0)));
      } else if (values.size() > MIN_VALUE_SIZE) {
        Integer from = values.get(INDEX_PARAM_FIRST);
        Integer to = values.get(INDEX_PARAM_SECOND);
        return Optional.of(path.between(from, to));
      } else {
        return Optional.empty();
      }
    });

    /**
     *  Filter by fileName and newspaperName.
     *  If only one parameter is given, filter by "contains"
     */
    bindings.bind(foo.fileName, foo.newspaperName).all((path, value) -> {

      List<? extends String> values = new ArrayList<>(value);
      return Optional.of(path.contains(values.get(INDEX_PARAM_FIRST)));
    });

    /**
     *  Filter by uploadTime.
     *  If only one parameter is given, filter by "greater or equals"
     *  If two parameters are given, look for "between"
     */
    bindings.bind(foo.uploadTime).all((path, value) -> {

      List<? extends Timestamp> values = new ArrayList<>(value);
      if (values.size() == MIN_VALUE_SIZE) {
        return Optional.of(path.goe(values.get(0)));
      } else if (values.size() > MIN_VALUE_SIZE) {
        Timestamp from = values.get(INDEX_PARAM_FIRST);
        Timestamp to = values.get(INDEX_PARAM_SECOND);
        return Optional.of(path.between(from, to));
      } else {
        return Optional.empty();
      }
    });

  }
}
