package be.lsinf1225gr12.minipoll.minipoll.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFriendFragment extends Fragment {

    TextView viewLogin;
    TextView viewName;
    TextView viewMail;
    TextView hiddenText;
    ImageButton viewAdd;
    static List<User> friendlist=User.getFriends(User.getConnectedUser());
    static List<User> Nfriend=getNonFriend();
    static int f=friendlist.size();
    static int n=Nfriend.size();

    public AddFriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_friend, container, false);
        viewLogin= (TextView) v.findViewById(R.id.LoginAdd);
        viewName= (TextView) v.findViewById(R.id.NameAdd);
        viewMail= (TextView) v.findViewById(R.id.EmailAdd);
        viewAdd= (ImageButton) v.findViewById(R.id.addButton);
        hiddenText= (TextView) v.findViewById(R.id.hiddenAdd);
        String m = "";
        for(int i=0; i<n;i++){
            m+=(Nfriend.get(i).getLogin()+"-");
        }
        String[]Login = m.split("-");
        m = "";
        for(int i=0; i<n;i++){
            m+=(Nfriend.get(i).getFirstname()+" "+Nfriend.get(i).getName()+"-");
        }
        String[]Name = m.split("-");
        m = "";
        for(int i=0; i<n;i++){
            m+=(Nfriend.get(i).getMail()+"-");
        }
        String[]Mail = m.split("-");
        if(n==0){
            Login = new String[1];
            Login[0]="Vous êtes amis avec tout le monde :D";
            viewAdd.setVisibility(View.GONE);
        }
        Bundle bdl = getArguments();
        hiddenText.setText(String.valueOf(bdl.getInt("count")-1));
        viewLogin.setText(Login[bdl.getInt("count") - 1]);
        viewName.setText(Name[bdl.getInt("count") - 1]);
        viewMail.setText(Mail[bdl.getInt("count") - 1]);
        return v;
    }

    public void add(View v){
        hiddenText=(TextView) v.findViewById(R.id.hiddenAdd);
        hiddenText.getText();
        int i=Integer.parseInt(hiddenText.toString());
        User.addFriend(User.getConnectedUser(),Nfriend.get(i));
    }

    public static List<User> getNonFriend(){
        List<User> Appui = User.getUsers();
        Appui.remove(User.getConnectedUser());
        for(int i=0;i<f;i++){
            Appui.remove(friendlist.get(i));
        }
        return Appui;
    }

}
