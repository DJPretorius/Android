package com.example.niel.snap;

import java.util.Scanner;

public class Snap extends Game {

   public Snap(Player[] p, int numP, Pool po) {
      deck = new Deck(true);
      deck.shuffle();
      players = p;
      numPlayers = numP;
      players[0].hand.pickUp(deck, 27);
      players[1].hand.pickUp(deck, 27);
      players[0].playerNumber = 1;
      players[1].playerNumber= 2;
      pool = po;
   }

   public boolean play(Player p){
      if(p.hand.size() == 0){
         return false;
      }
      Card temp = p.hand.giveCard();
      pool.takeCard(temp);
      return true;
   }
   
   public boolean callSnap(Player p){
      if(pool.cards == null){
         return false;
      }
      if(pool.numOfCards < 1){
         return false;
      }

      Card first = pool.getAt(pool.numOfCards-1);
      Card second = pool.getAt(pool.numOfCards -2);
      if(first.suit == Suits.Joker){
         p.hand.pickUp(pool, pool.numOfCards);
         return true;
      }
      if(second == null){
         return false;
      }
      if(first.number == second.number){
         p.hand.pickUp(pool, pool.numOfCards);
         return true;
      }
      return false;
   }
   
   @Override
   public void start(){
      System.out.println("Welcome to Snap.\nWhen two cards have the same rank, call snap to win the pool.");
      int c =0;
      Scanner reader = new Scanner(System.in);
      
      while(true){
         System.out.println("'p' to place card, 'q' to quit, 'f' for player 1 'snap' 'j' for player 2 'snap'");
         System.out.println("Player "+ players[c].playerNumber+ "'s turn");
         char play = (char) reader.nextByte();
         if(play == 'q'){
            break;
         }
         boolean snapCalled;
         switch(play){
            case 'p': pool.takeCard(players[c].hand.giveCard());
            break;
            case 'f':   snapCalled = callSnap(players[0]);
                        if(snapCalled){
                           System.out.println("Player 1 won the pool");
                        }else{
                           System.out.println("Nope. Sorry. False calls will be penilized");
                        }
            break;
            case 'j':   snapCalled = callSnap(players[1]);
                        if(snapCalled){
                           System.out.println("Player 2 won the pool");
                        }else{
                           System.out.println("Nope. Sorry. False calls will be penilized");
                        }
         }
         System.out.println(pool.getAt(pool.numOfCards-1).toString()+"\t"+ pool.getAt(pool.numOfCards-2).toString());
         
         
      }
   }
}