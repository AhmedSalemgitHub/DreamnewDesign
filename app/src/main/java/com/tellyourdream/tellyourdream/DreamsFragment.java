package com.tellyourdream.tellyourdream;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tellyourdream.tellyourdream.DreamsRecyclerAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class DreamsFragment extends Fragment {

    private RecyclerView mDreamsListView;

    private List<Dreams> dreamsList;
    private DreamsRecyclerAdapter dreamsRecyclerAdapter;

    private FirebaseFirestore mFirestore;

    public DreamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dreams, container, false);

        mFirestore = FirebaseFirestore.getInstance();

        mDreamsListView = (RecyclerView) view.findViewById(R.id.Dreams);

        dreamsList = new ArrayList<>();
        dreamsRecyclerAdapter = new DreamsRecyclerAdapter(container.getContext(),dreamsList);

        mDreamsListView.setHasFixedSize(true);
        mDreamsListView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mDreamsListView.setAdapter(dreamsRecyclerAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        mFirestore.collection("Users").addSnapshotListener(getActivity(),new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){
                    if (doc.getType() == DocumentChange.Type.ADDED){
                        Dreams dreams = doc.getDocument().toObject(Dreams.class);
                        dreamsList.add(dreams);
                        dreamsRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
