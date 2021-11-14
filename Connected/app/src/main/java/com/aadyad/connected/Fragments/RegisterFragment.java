package com.aadyad.connected.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.aadyad.connected.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterFragment extends Fragment {

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_register, container, false);
    }

    EditText email, password, confPassword, username;
    Button regBtn;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = getView().findViewById(R.id.registerEmail);
        password = getView().findViewById(R.id.registerPassword);
        confPassword = getView().findViewById(R.id.registerConfirmPassword);
        username = getView().findViewById(R.id.registerUsername);
        regBtn = getView().findViewById(R.id.Register);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1 = password.getText().toString();
                String pass2 = confPassword.getText().toString();
                String em = email.getText().toString();
                String user = username.getText().toString();
                if(pass1.isEmpty() || pass2.isEmpty() || em.isEmpty() || user.isEmpty()){
                    Toast("Please fill out all the fields");
                    return;
                }
                if(pass1.length() < 6){
                    Toast("Password is too short");
                    return;
                }
                if(!pass1.equals(pass2)){
                    Toast("Passwords don't match");
                    return;
                }
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(em, pass1)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "createUserWithEmail:success");
                                    FirebaseUser currUser = mAuth.getCurrentUser();
                                    UserProfileChangeRequest req = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(user)
                                            .build();
                                    currUser.updateProfile(req);
                                    Toast("Successfully signed up!");
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast(task.getException().getMessage());
                                }
                            }
                        });
            }
        });
    }

    void Toast(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}