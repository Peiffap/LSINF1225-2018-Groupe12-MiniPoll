package be.lsinf1225gr12.minipoll.minipoll.adapter;


import android.content.Intent;
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
import be.lsinf1225gr12.minipoll.minipoll.activity.FriendMenuActivity;
import be.lsinf1225gr12.minipoll.minipoll.activity.Friend_List_Activity;
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
    ImageButton HiddenButton;
    List<User> friendlist=User.getFriends(User.getConnectedUser());
    int f=friendlist.size();
    public static final String RemoveMemory = "be.lsinf1225gr12.minipoll.minipoll.activity.RemoveMemory";

    public SwipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_swipe, container, false);
        viewID = (TextView) v.findViewById(R.id.textView2);
        viewName = (TextView) v.findViewById(R.id.textView3);
        viewMail = (TextView) v.findViewById(R.id.textView4);
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
        if(User.getConnectedUser().getBestFriend()==friendlist.get(bdl.getInt("count")-1).getId()){
            IBFav.setVisibility(View.GONE);
            HiddenButton.setVisibility(View.VISIBLE);
        }
        viewID.setText(Login[bdl.getInt("count") - 1]);
        viewName.setText(Name[bdl.getInt("count") - 1]);
        viewMail.setText(Mail[bdl.getInt("count") - 1]);
        IBRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getContext(), FriendMenuActivity.class);
                startActivity(back);
                User.removeFriend(User.getConnectedUser(),User.getUserWithLogin(viewID.getText().toString()));
            }
        });
        IBFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.getConnectedUser().setBestfriend(User.getUserWithLogin(viewID.getText().toString()).getId());
                Intent back = new Intent(getContext(), FriendMenuActivity.class);
                startActivity(back);
            }
        });

        return v;
    }
}
