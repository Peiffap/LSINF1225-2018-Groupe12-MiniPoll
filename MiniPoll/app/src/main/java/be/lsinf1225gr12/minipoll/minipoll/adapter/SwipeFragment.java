package be.lsinf1225gr12.minipoll.minipoll.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeFragment extends android.support.v4.app.Fragment {
    TextView viewID;
    TextView viewName;
    TextView viewMail;
    ImageButton IBFav;
    ImageButton IBRem;

    public SwipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_swipe, container, false);
        viewID = (TextView) v.findViewById(R.id.textView2);
        viewName = (TextView) v.findViewById(R.id.textView3);
        viewMail = (TextView) v.findViewById(R.id.textView4);
        IBFav = (ImageButton) v.findViewById(R.id.imageButton1);
        IBRem = (ImageButton) v.findViewById(R.id.imageButton2);
        String m = "";
        for(int i=0; i< User.getFriends(User.getConnectedUser()).size();i++){
            m+=(User.getFriends(User.getConnectedUser()).get(i).getLogin()+"-");
        }
        String[] Login = m.split("-");
        m = "";
        for(int i=0; i< User.getFriends(User.getConnectedUser()).size();i++){
            m+=(User.getFriends(User.getConnectedUser()).get(i).getFirstname()+" "+User.getFriends(User.getConnectedUser()).get(i).getName()+"-");
        }
        String[]Name = m.split("-");
        m = "";
        for(int i=0; i< User.getFriends(User.getConnectedUser()).size();i++){
            m+=(User.getFriends(User.getConnectedUser()).get(i).getMail()+"-");
        }
        String[]Mail = m.split("-");
        if(User.getFriends(User.getConnectedUser()).size()==0){
            Login = new String[1];
            Login[0]="Vous n'avez pas d'ami, il faudrait s'en inquiéter";
            IBFav.setVisibility(View.GONE);
            IBRem.setVisibility(View.GONE);
        }
        Bundle bdl = getArguments();
        viewID.setText(Login[bdl.getInt("count") - 1]);
        viewName.setText(Name[bdl.getInt("count") - 1]);
        viewMail.setText(Mail[bdl.getInt("count") - 1]);
        return v;
    }

    public void remove (View v){

    }

}
