package com.aadyad.connected;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerifyDialog extends AppCompatDialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Verify your email.")
                .setMessage("Check your email for a verification.")
                .setCancelable(false)
                .setPositiveButton("Verified", null);
        AlertDialog d = dialog.create();
        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button b = ((AlertDialog) d).getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
                        u.reload();
                        if(u.isEmailVerified()){
                            // Start app
                            Toast.makeText(getActivity(), "Successfully verified.", Toast.LENGTH_SHORT).show();
                            d.dismiss();
                        } else{
                            Toast.makeText(getActivity(), "Your email is not verified.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return d;
    }
}
