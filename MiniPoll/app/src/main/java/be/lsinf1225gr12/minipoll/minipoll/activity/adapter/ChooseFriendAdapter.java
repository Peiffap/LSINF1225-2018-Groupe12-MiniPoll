package be.lsinf1225gr12.minipoll.minipoll.activity.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class ChooseFriendAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] friends;

    public ChooseFriendAdapter(Context context, String[] f){
        super(context,-1,f);
        this.context=context;
        this.friends=f;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_choose_friend, parent, false);
        TextView textView = rowView.findViewById(R.id.name);
        textView.setText(friends[position]);

        return rowView;
    }
}
