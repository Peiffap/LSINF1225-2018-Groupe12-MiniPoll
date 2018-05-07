package be.lsinf1225gr12.minipoll.minipollapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeFragment extends android.support.v4.app.Fragment {
    TextView viewID;



    public SwipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_swipe, container, false);
        viewID = (TextView) v.findViewById(R.id.textView2);
        Bundle bdl = getArguments();
        String text = Integer.toString(bdl.getInt("count"));
        viewID.setText("Here's the test "+text);
        return v;
    }

}
