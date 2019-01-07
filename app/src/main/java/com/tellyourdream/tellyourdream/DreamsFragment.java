package com.tellyourdream.tellyourdream;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tellyourdream.tellyourdream.DreamsRecyclerAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class DreamsFragment extends Fragment {

    private RecyclerView mDreamsListView;

    private List<Dreams> dreamsList;
    private DreamsRecyclerAdapter dreamsRecyclerAdapter;

    private FirebaseFirestore mFirestore;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private Query firebaseQuery;
    private com.google.firebase.firestore.Query mfireStoreQuery;

    public DreamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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

        firebaseQuery = mFirebaseDatabase.getReference().child("dream").orderByChild("ownerEmail").equalTo(MainActivity.prefemail);
        firebaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot queryDream : dataSnapshot.getChildren()) {
                    oldDreams oldDream = queryDream.getValue(oldDreams.class);
                    oldDream.setParentKey(queryDream.getRef().getKey());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;


    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        dreamsList.clear();
//
//        mFirestore.collection("Users").addSnapshotListener(Objects.requireNonNull(getActivity()), new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                if (queryDocumentSnapshots != null) {
//                    for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
//                        if (doc.getType() == DocumentChange.Type.ADDED) {
//                            Dreams dreams = doc.getDocument().toObject(Dreams.class);
//                            dreamsList.add(dreams);
//                            dreamsRecyclerAdapter.notifyDataSetChanged();
//                        }
//                    }
//                }
//            }
//        });
//    }
}
