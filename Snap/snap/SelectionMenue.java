package com.example.niel.snap;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectionMenue.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectionMenue#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectionMenue extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selection_menue, container, false);
        Button btn = (Button) view.findViewById(R.id.btnSnap);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameArea ga = new GameArea();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.selectionMenue,ga);
                ft.addToBackStack("Selection_Fragment");
                ft.commit();
            }
        });
        return view;
    }




}
