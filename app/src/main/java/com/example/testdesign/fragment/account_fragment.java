package com.example.testdesign.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testdesign.utilities.MyPreferences;
import com.example.testdesign.R;

public class account_fragment extends Fragment {
private MyPreferences myPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myPreferences = new MyPreferences(getActivity());
        TextView logOut;

        View view = inflater.inflate(R.layout.fragment_account_fragment, container, false);

        logOut = view.findViewById(R.id.textView7);

//        logOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myPreferences.writeLoginStatus(false);
//                myPreferences.writeUserId("");
//                myPreferences.writeUsername("");
//
//                Intent i = new Intent(getActivity(), Login.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
//            }
//        });
        return view;
    }
}