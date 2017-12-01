package com.example.niel.snap;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.Assert;

import static android.R.attr.id;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameArea OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameArea newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameArea extends Fragment {

    Button btnOpponent;
    Button btnPlayer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game_area, container, false);
        final ImageView ivPlay = (ImageView) view.findViewById(R.id.playArea);
        ivPlay.setImageResource(R.drawable.back);
        btnOpponent = (Button) view.findViewById(R.id.playOpponent);
        btnPlayer = (Button) view.findViewById(R.id.playPlayer);

        final Player[] players = new Player[2];
        players[0] = new Player();
        players[1] = new Player();
        Pool pool = new Pool();
        final Snap snap = new Snap(players,2, pool);

        final TextView tvOpponent = (TextView) view.findViewById(R.id.numCardsOpponent);
        tvOpponent.setRotation(180);
        tvOpponent.setText(Integer.toString(players[1].hand.numOfCards));

        final TextView tvPlayer = (TextView) view.findViewById(R.id.numCardsPlayer);
        tvPlayer.setText(Integer.toString(players[0].hand.numOfCards));

        btnOpponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                snap.play(players[1]);
                Card temp = snap.pool.getTop();
                if(temp == null){
                    btnOpponent.setEnabled(false);
                }

                String cardName = parseCard(temp);
                String uri = "@drawable/"+cardName;
                int imageResource = getResources().getIdentifier(uri,null,getContext().getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                ivPlay.setImageDrawable(res);
                tvOpponent.setText(Integer.toString(players[1].hand.numOfCards));

            }
        });

        btnPlayer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                snap.play(players[0]);
                Card temp = snap.pool.getTop();
                if(temp == null){
                    btnPlayer.setEnabled(false);
                }

                String cardName = parseCard(temp);
                String uri = "@drawable/"+cardName;
                int imageResource = getResources().getIdentifier(uri,null,getContext().getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                ivPlay.setImageDrawable(res);

                tvPlayer.setText(Integer.toString(players[0].hand.numOfCards));
            }
        });

        ImageView ivPlayer = (ImageView) view.findViewById(R.id.callSnapFirst);
        ivPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean gotit = snap.callSnap(players[0]);
                if(gotit) {
                    ivPlay.setImageResource(R.drawable.back);
                    tvPlayer.setText(Integer.toString(players[0].hand.numOfCards));
                }
            }
        });

        ImageView ivOpponent = (ImageView) view.findViewById(R.id.snapCallSecond);
        ivOpponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean gotit = snap.callSnap(players[1]);
                if(gotit){
                    ivPlay.setImageResource(R.drawable.back);
                    tvOpponent.setText(Integer.toString(players[1].hand.numOfCards));

                }
            }
        });
        return view;
    }

    int numJoker =0;
    public String parseCard(Card c){
        String temp = c.toString();
        if(temp.compareTo("Joker") == 0){
            if(numJoker++ == 0){
                return "black_joker";
            }else{
                return "red_joker";
            }
        }
        String toReturn ="";
        String[] parse = temp.split(" ");
        switch (parse[0]){
            case "Ace": toReturn += "ace"; break;
            case "King": toReturn += "king"; break;
            case "Queen": toReturn += "queen"; break;
            case "Jack": toReturn += "jack"; break;
            default: toReturn += "a"+ Integer.toString(c.number+1);
        }
        toReturn +="_of_";
        switch (parse[1]){
            case "Hearts": toReturn += "hearts"; break;
            case "Diamonds": toReturn += "diamonds"; break;
            case "Clovers": toReturn += "clubs"; break;
            case "Spades": toReturn += "spades"; break;
        }
//        toReturn += ".png";
        return toReturn;
    }
}
