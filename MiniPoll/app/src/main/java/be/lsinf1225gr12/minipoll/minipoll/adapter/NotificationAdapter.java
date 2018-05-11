package be.lsinf1225gr12.minipoll.minipoll.adapter;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.activity.FriendMenuActivity;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class NotificationAdapter extends ArrayAdapter<String> {

    private final Context context;
    private String[] friends;
    ImageButton Accept;
    ImageButton Decline;

    public NotificationAdapter(Context context, String[] f){
        super(context,-1,f);
        this.context=context;
        this.friends=f;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_notifications, parent, false);
        final TextView textView = (TextView) rowView.findViewById(R.id.nameN);
        textView.setText(friends[position]);
        TextView hiddenN=(TextView) rowView.findViewById((R.id.hiddenN));
        hiddenN.setText(String.valueOf(position));
        Accept = (ImageButton) rowView.findViewById(R.id.accept);
        Decline = (ImageButton) rowView.findViewById(R.id.decline);
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getContext(), FriendMenuActivity.class);
                context.startActivity(back);
                User.acceptFriend(User.getConnectedUser(),User.getUserWithLogin(textView.getText().toString()));
            }
        });
        Decline.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getContext(), FriendMenuActivity.class);
                context.startActivity(back);
                User.removeFriend(User.getConnectedUser(),User.getUserWithLogin(textView.getText().toString()));
            }
        }));
        return rowView;
    }

    public void accept (View v){
        TextView hiddenN = (TextView) v.findViewById(R.id.hiddenN);
        hiddenN.getText();
        int i = Integer.parseInt(hiddenN.toString());
        User.acceptFriend(User.getConnectedUser(),User.getDemands(User.getConnectedUser()).get(i));
    }

    public void decline (View v){
        TextView hiddenN = (TextView) v.findViewById(R.id.hiddenN);
        hiddenN.getText();
        int i = Integer.parseInt(hiddenN.toString());
        User.removeFriend(User.getConnectedUser(),User.getDemands(User.getConnectedUser()).get(i));
    }
}
