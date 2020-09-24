package com.cars24.assignment.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageHelper {

  /**
   * AcutionSortingFieldMapper.
   *
   * @param pageable {Pageable}
   * @return pageable {Pageable}
   */
  public static Pageable acutionSortingFieldMapper(Pageable pageable) {

    Sort sort = pageable.getSort();
    sort = Sort.by(
        sort.map(order -> order.withProperty(AuctionFieldMapping.valueOf(order.getProperty()).getValue())).toList());

    return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
  }

}
