package be.lsinf1225gr12.minipoll.minipoll.adapter;

import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class NotificationAdapter extends ArrayAdapter<String> {

    private final Context context;
    private String[] friends;

    public NotificationAdapter(Context context, String[] f){
        super(context,-1,f);
        this.context=context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_notifications, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.nameN);
        friends=LoginToTab(User.getDemands(User.getConnectedUser()));
        textView.setText(friends[position]);
        TextView hiddenN=(TextView) rowView.findViewById((R.id.hiddenN));
        hiddenN.setText(String.valueOf(position));
        return rowView;
    }

    public void accept (View v){
        TextView hiddenN = (TextView) v.findViewById(R.id.hiddenN);
        hiddenN.getText();
        int i = Integer.parseInt(hiddenN.toString());
        User.acceptFriend(User.getConnectedUser(),User.getDemands(User.getConnectedUser()).get(i));
    }

    public void decline (View v){
       /* TextView hiddenN = (TextView) v.findViewById(R.id.hiddenN);
        hiddenN.getText();
        int i = Integer.parseInt(hiddenN.toString());
        User.declineFriend(User.getConnectedUser(),User.getDemands(User.getConnectedUser()).get(i));
        */
    }

    public static String[] LoginToTab(ArrayList<User> U){
        String[] Appui = new String[U.size()];
        for(int i =0; i< Appui.length;i++){
            Appui[i]=U.get(i).getLogin();
        }
        return Appui;
    }
}
