package com.sp.rest;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sp.dto.DTO_Card;
import com.sp.model.Card;
import com.sp.service.CardService;

@RestController
public class CardRestCrt {
	@Autowired
	CardService hService;

	@RequestMapping(method = RequestMethod.POST, value = "/hero")
	public void addCard(@RequestBody Card card) {
		hService.addCard(card);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/card/{id}")
	public Card getCard(@PathVariable String id) {
		Card h = hService.getCard(Integer.valueOf(id));
		return h;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/setOwner/{id}")
	public void setOwner(@PathVariable String id) {
		Card h = hService.getCard(Integer.valueOf(id));
		h.setId_user(Integer.valueOf(id));
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/buy/{id_user}")
      public DTO_Card[] getBuyList(@PathVariable String id_user) {
    	  List<Card> list = hService.getBuyList(Integer.valueOf(id_user));
    	  ListIterator<Card> it = list.listIterator();
    	  DTO_Card[] result = new DTO_Card[list.size()];
    	  int i = 0;
    	  while(it.hasNext()){
    		  Card current=(Card) it.next();
    		  result[i]=new DTO_Card(current.getId(),current.getPrice(),current.getFamily_name(),current.getImg_src(),current.getName(),current.getDescription(),current.getHp(),current.getEnergy(),current.getAttack(),current.getDefense(),current.getAffinity());
    		  i++;
    	  }
    	  return(result);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sell/{idUser}")
	public DTO_Card[] getSellList(@PathVariable String idUser) {
		  List<Card> list = hService.getSellList(Integer.valueOf(idUser));
	  	  ListIterator<Card> it = list.listIterator();
	  	  DTO_Card[] result = new DTO_Card[list.size()];
	  	  int i = 0;
	  	  while(it.hasNext()){
	  		  Card current=(Card) it.next();
	  		  result[i]=new DTO_Card(current.getId(),current.getPrice(),current.getFamily_name(),current.getImg_src(),current.getName(),current.getDescription(),current.getHp(),current.getEnergy(),current.getAttack(),current.getDefense(),current.getAffinity());
	  		  i++;
	  	  }
	  	  return(result);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/buy/{idUser}/{idCard}")
	public void buy(@PathVariable String idUser,@PathVariable String idCard) {
		hService.buy(Integer.valueOf(idUser), Integer.valueOf(idCard));
		System.out.println(idUser);
		System.out.println(idCard);

	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/sell/{idUser}/{idCard}")
	public void sell(@PathVariable String idUser,@PathVariable String idCard) {
		hService.sell(Integer.valueOf(idUser), Integer.valueOf(idCard));
	}

}
