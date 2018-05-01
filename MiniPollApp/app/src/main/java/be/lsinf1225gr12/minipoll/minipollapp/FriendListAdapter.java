package be.lsinf1225gr12.minipoll.minipollapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class FriendListAdapter extends ArrayAdapter<User>
{
    private static final String TAG = "FriendListAdapter";
    private Context mContext;
    int mResource;

    public FriendListAdapter(Context context, int resource, ArrayList<User> objects)
    {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Make a string for every attribute in the layout.
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        // Make a TextView name = (TextView) convertView.findViewById(R.id.l'id); for every element of the layout.
        // Then do name.setText(attribut); for every element of the layout.
        return convertView;
    }
}
