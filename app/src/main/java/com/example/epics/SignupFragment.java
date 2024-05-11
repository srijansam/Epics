package com.example.epics;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentTransaction;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.util.Base64;




import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupFragment extends Fragment {

    EditText signupName, signupUsername, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);

        signupName = rootView.findViewById(R.id.signup_name);
        signupEmail = rootView.findViewById(R.id.signup_email);
        signupUsername = rootView.findViewById(R.id.signup_username);
        signupPassword = rootView.findViewById(R.id.signup_password);
        loginRedirectText = rootView.findViewById(R.id.loginRedirectText);
        signupButton = rootView.findViewById(R.id.signup_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();
//              hashing the password
                String hashedPassword = hashPassword(password);
                HelperClass helperClass = new HelperClass(name, email, username, hashedPassword);
                reference.child(username).setValue(helperClass);

                Toast.makeText(getContext(), "You have signed up successfully!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getContext(), LoginFragment.class);
//                startActivity(intent);
                // Replace SignupFragment with LoginFragment
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new LoginFragment());
                transaction.addToBackStack(null); // Add to back stack if needed
                transaction.commit();
            }
            private String hashPassword(String password) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    byte[] hashBytes = md.digest(password.getBytes());
                    return Base64.encodeToString(hashBytes, Base64.DEFAULT);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    return null;
                }
            }


        });


        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace getActivity() with requireActivity() if using requireActivity() is necessary
//                Intent intent = new Intent(getActivity(), LoginFragment.class);
//                startActivity(intent);
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, new LoginFragment());
                transaction.addToBackStack(null); // Add to back stack if needed
                transaction.commit();
            }
        });

        return rootView;
    }
}