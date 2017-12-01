package com.example.niel.snap;

public class Hand extends Holder {

   /**
   * 
   * @param location
   *    Location referes to a holder where a hand can be picked.
   * @param numberToPick
   *    number of cards to pick from the location.
   * @return 
   */
   public boolean pickUp(Holder location, int numberToPick) {
      if(cards == null){
         cards = new Card[4];
      }
      int curr = numOfCards;
      if(location.numOfCards == 0){
         return false;
      }
      for(int c = 0; c< numberToPick; c++){
         if(numOfCards == cards.length){
            doubleAndCopy();
         }
         Card temp = location.giveCard();
         if(temp == null){
            return true;
         }
         cards[curr + c] = temp; 
         numOfCards++;
      }
      return true;
   }

}