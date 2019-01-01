package com.tellyourdream.tellyourdream;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class profileFragment extends Fragment {

    private Button mLogoutButton;
    private FirebaseAuth mAuth;
    private EditText profileName, profileEmail, profileAge, profileMarital, profileGender;


    public profileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();

        mLogoutButton = view.findViewById(R.id.profile_logout_button);

        profileName = view.findViewById(R.id.profileName_editText);
        profileEmail = view.findViewById(R.id.profileEmail_editText);
        profileAge = view.findViewById(R.id.profileAge_editText);
        profileMarital = view.findViewById(R.id.profileMarital_editText);
        profileGender = view.findViewById(R.id.profileGender_editText);


        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mAuth.signOut();
                Intent intent = new Intent(container.getContext(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

}
