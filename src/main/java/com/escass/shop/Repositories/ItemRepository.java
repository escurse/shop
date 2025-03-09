package com.escass.shop.Repositories;

import com.escass.shop.Entities.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Page<ItemEntity> findPageBy(Pageable page);

    List<ItemEntity> findAllByTitleContains(String title);

    @Query(value = "select * from itemEntity where id = ?1 ?2", nativeQuery = true)
    ItemEntity rawQuery1(Long num, Long num2);
}
