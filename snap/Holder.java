package com.example.niel.snap;

public class Holder {

   protected Card[] cards;
   protected int numOfCards = 0;

   public Holder(){

   }

   public Card getTop(){
      if(cards.length == 0){
         return null;
      }
      return cards[numOfCards-1];
   }
   
   public Card getAt(int index){
      if(index >= 0 && index < numOfCards){
         return cards[index];
      }
      return null;
   }
   
   public int size(){
      return numOfCards;
   }
   
   public Card giveCard(){
      if(numOfCards == 0){
         return null;
      }
      return cards[--numOfCards];
   }

   public void takeCard(Card c){
       if(cards == null){
           cards = new Card[1];
       }
       if(numOfCards == cards.length){
         doubleAndCopy();
       }
       cards[numOfCards++]= c;
   }
   
   @Override
   public String toString(){
      String toReturn="";
      for(int c =0; c< numOfCards; c++){
         toReturn+= cards[c].toString();
         if(c< numOfCards-1){
            toReturn += "\n";
         }
      }
      return toReturn;
   }
   
   public void doubleAndCopy(){
      Card[] temp = new Card[numOfCards*2];
      for(int c=0; c< numOfCards; c++){
         temp[c] = cards[c];
      }
      cards = temp;
   }
   
  

}