package com.example.niel.snap;

import com.example.niel.snap.Suits;

public class Card {

   public Suits suit;
   public int number;

   public Card(Suits s, int num){
      suit = s;
      number = num;
   }
 
   @Override
   public String toString(){
      String toReturn ="";
      switch(number){
         case 0: toReturn += "Ace ";
            break;
         case 10: toReturn += "Jack ";
            break;
         case 11: toReturn += "Queen ";
            break;
         case 12: toReturn += "King ";
            break;
         case Integer.MAX_VALUE:
            break;
         default: toReturn += Integer.toString(number + 1)+ " ";
      }
      toReturn += suit.toString();
      return toReturn;
   }
}