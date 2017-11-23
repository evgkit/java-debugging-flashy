package com.teamtreehouse.flashy.repositories;

import com.teamtreehouse.flashy.domain.FlashCard;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FlashCardRepository extends CrudRepository<FlashCard, Long> {
  List<FlashCard> findByIdNotIn(Collection<Long> ids);
  List<FlashCard> findAll();
}
