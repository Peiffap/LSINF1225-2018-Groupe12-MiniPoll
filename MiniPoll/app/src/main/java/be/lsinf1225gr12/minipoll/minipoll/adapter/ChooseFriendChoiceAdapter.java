package be.lsinf1225gr12.minipoll.minipoll.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.activity.ChoiceEditActivity;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class ChooseFriendChoiceAdapter extends BaseAdapter {
    /**
     * Permet d'instancier un fichier xml de layout dans une vue.
     */
    private final LayoutInflater mInflater;
    public static final String ChosenMemory = "be.lsinf1225gr12.minipoll.minipoll.activity.ChosenMemory";
    Context context;
    Button btn;
    static List<User> friendlist=User.getFriends(User.getConnectedUser());

    /**
     * Liste des éléments de collection à mettre dans la liste.
     */
    private List<User> user;

    public ChooseFriendChoiceAdapter(Context context, List<User> user) {
        mInflater = LayoutInflater.from(context);
        this.user = user;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_choose_friend_choice, parent, false);
        btn=(Button) rowView.findViewById(R.id.chooseButton);
        btn.setText(user.get(position).getLogin());
        return rowView;
    }

    public void Choose(View v){
        String s = btn.getText().toString();
        Intent intent = new Intent(context,ChoiceEditActivity.class);
        intent.putExtra(ChosenMemory,s);
        context.startActivity(intent);
    }
}
