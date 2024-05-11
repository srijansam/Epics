package com.example.epics;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Find the button in the layout
        Button button = rootView.findViewById(R.id.button);

        // Set OnClickListener to the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of the LoginFragment
                LoginFragment loginFragment = new LoginFragment();

                // Begin the transaction
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                // Replace the HomeFragment with the LoginFragment
                transaction.replace(R.id.frame_layout, loginFragment);

                // Optional: Add the transaction to the back stack
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });

        return rootView;
    }
}
