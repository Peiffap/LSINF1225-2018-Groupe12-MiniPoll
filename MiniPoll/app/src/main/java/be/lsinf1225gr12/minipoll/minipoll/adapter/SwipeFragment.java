package be.lsinf1225gr12.minipoll.minipoll.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeFragment extends android.support.v4.app.Fragment {
    TextView viewID;
    TextView viewName;
    TextView viewMail;
    TextView Hidden;
    ImageButton IBFav;
    ImageButton IBRem;
    ImageButton HiddenButton;
    static List<User> friendlist=User.getFriends(User.getConnectedUser());
    static int f=friendlist.size();

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
        Hidden = (TextView) v.findViewById(R.id.hidden);
        IBFav = (ImageButton) v.findViewById(R.id.imageButton1);
        IBRem = (ImageButton) v.findViewById(R.id.imageButton2);
        HiddenButton= (ImageButton) v.findViewById(R.id.HB);
        String m = ""; String n = ""; String o = "";
        for(int i=0; i< f;i++){
            m+=(friendlist.get(i).getLogin()+"-");
            n+=(friendlist.get(i).getFirstname()+" "+friendlist.get(i).getName()+"-");
            o+=(friendlist.get(i).getMail()+"-");
        }
        String[]Login = m.split("-");
        String[]Name = n.split("-");
        String[]Mail = o.split("-");
        if(f==0){
            Login = new String[1];
            Login[0]="Vous n'avez pas d'ami, il faudrait s'en inquiéter";
            IBFav.setVisibility(View.GONE);
            IBRem.setVisibility(View.GONE);
        }
        Bundle bdl = getArguments();

        //IBFav.setImageMatrix(HiddenButton.getImageMatrix());
        Hidden.setText(String.valueOf(bdl.getInt("count")-1));
        viewID.setText(Login[bdl.getInt("count") - 1]);
        viewName.setText(Name[bdl.getInt("count") - 1]);
        viewMail.setText(Mail[bdl.getInt("count") - 1]);

        return v;
    }
    public void remove (View v){
        Hidden = (TextView) v.findViewById(R.id.hidden);
        Hidden.getText();
        int i = Integer.parseInt(Hidden.toString());
        User.removeFriend(User.getConnectedUser(),friendlist.get(i));
    }
    public void addFav(View v){
        Hidden = (TextView) v.findViewById(R.id.hidden);
        Hidden.getText();
        int i = Integer.parseInt(Hidden.toString());
        User.getConnectedUser().setBestfriend(friendlist.get(i).getId());
    }
}
