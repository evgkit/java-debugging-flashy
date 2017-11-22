package com.teamtreehouse.flashy.controllers;

import com.teamtreehouse.flashy.domain.FlashCard;
import com.teamtreehouse.flashy.services.FlashCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.StringJoiner;

@Controller
public class IndexController {
  public static final int AMOUNT_TO_SHOW = 3;
  private FlashCardService flashCardService;

  @Autowired
  public void setFlashCardService(FlashCardService flashCardService) {
    this.flashCardService = flashCardService;
  }

  @RequestMapping("/")
  public String index(Model model) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Refresh your memory about ");

    List<FlashCard> cards = flashCardService.getRandomFlashCards(AMOUNT_TO_SHOW);
    StringJoiner stringJoiner = new StringJoiner(", ");
    for (FlashCard card : cards) {
      stringJoiner.add(card.getTerm());
    }
    stringBuilder.append(stringJoiner.toString());

    Long totalCount = flashCardService.getCurrentCount();
    if (totalCount > AMOUNT_TO_SHOW) {
      stringBuilder.append(" and ");
      stringBuilder.append(totalCount - AMOUNT_TO_SHOW);
      stringBuilder.append(" more");
    }

    model.addAttribute("cta", stringBuilder.toString());
    model.addAttribute("flashCardCount", totalCount);
    return "index";
  }

}
