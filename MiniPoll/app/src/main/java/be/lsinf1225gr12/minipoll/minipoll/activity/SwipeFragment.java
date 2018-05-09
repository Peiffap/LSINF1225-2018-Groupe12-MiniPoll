package be.lsinf1225gr12.minipoll.minipoll.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeFragment extends android.support.v4.app.Fragment {
    TextView viewID;
    TextView viewName;
    TextView viewMail;


    public SwipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_swipe, container, false);
        viewID = v.findViewById(R.id.textView2);
        viewName = v.findViewById(R.id.textView3);
        viewMail = v.findViewById(R.id.textView4);
        String[] list={"Donald Trump","Barack Obama", "George Bush","Bill Clinton", "Ronald Reagan"}; //examples, to be removed TODO
        Bundle bdl = getArguments();
        String text = Integer.toString(bdl.getInt("count"));
        viewID.setText(list[bdl.getInt("count") - 1]); //example, to be removed TODO
        viewName.setText("This is the first name #"+text);
        viewMail.setText("This is the mail adress #"+text);
        return v;
    }

}
