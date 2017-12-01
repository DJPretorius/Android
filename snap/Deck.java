package com.example.niel.snap;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends Holder {

	/**
	 * 
	 * @param withJoker
	 */
	public Deck(boolean withJoker) {
         if(withJoker){
            numOfCards = 54;
         }else{
            numOfCards = 52;
         }
         cards = new Card[54];
         for(int c = 0; c< 52; c++){
            if(c< 13){
              cards[c] = new Card(Suits.Hearts, c % 13);
              continue;
            }
            if(c< 26){
               cards[c] = new Card(Suits.Diamonds, c % 13);
               continue;
            }
            if(c< 39){
               cards[c] = new Card(Suits.Clovers, c % 13);
               continue;
            }
            if(c < 52){
               cards[c] = new Card(Suits.Spades, c % 13);
               continue;
            }
         }
         if(withJoker){
            cards[52] = new Card(Suits.Joker,Integer.MAX_VALUE);
            cards[53] = new Card(Suits.Joker,Integer.MAX_VALUE);
         }
	}
      
	public void shuffle() {
         if(cards != null){
            ArrayList<Integer> list = new ArrayList<>();
            for(int c=0; c<numOfCards; c++){
               list.add(c);
            }
            Collections.shuffle(list);
            Card[] temp = new Card[numOfCards];
            for(int c =0; c< numOfCards; c++){
               temp[list.get(c)] = cards[c];
            }
            cards = temp;
         }
	}


}