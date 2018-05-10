package be.lsinf1225gr12.minipoll.minipoll.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class ChooseFriendAdapter extends BaseAdapter {
    /**
     * Permet d'instancier un fichier xml de layout dans une vue.
     */
    private final LayoutInflater mInflater;

    /**
     * Liste des éléments de collection à mettre dans la liste.
     */
    private ArrayList<User> user;

    public ChooseFriendAdapter(Context context, ArrayList<User> user) {
        mInflater = LayoutInflater.from(context);
        this.user = user;
    }

    @Override
    public int getCount() {
        return user.size();
    }

    @Override
    public Object getItem(int i) {
        return user.get(i);
    }

    @Override
    public long getItemId(int i) {
        return user.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Si la vue n'a pas encore été créé (typiquement lors du première affichage de la liste).
        // Android recycle en effet les layout déjà chargés des éléments de la liste (par exemple
        // lors du changement de l'ordre dans la liste.)

        if (convertView == null) {
            // Création d'un nouvelle vue avec le layout correspondant au fichier xml
            convertView = mInflater.inflate(R.layout.adapter_choose_friend, parent, false);
        }

        // Récupération des deux éléments de notre vue dans le but d'y placer les données.
        TextView nameTextView = convertView.findViewById(R.id.show_row_name);

        // Récupération et placement des données.
        User myUser = user.get(position);
        nameTextView.setText(myUser.getLogin());

        return convertView;
    }

    /**
     * Change la liste des éléments de collection affichée.
     * <p>
     * Permet de changer complètement la liste des éléments affichés dans la liste.
     *
     * @param newUser La nouvelle liste des éléments de collection à afficher.
     */
    public void setUser(ArrayList<User> newUser) {
        this.user = user;
        notifyDataSetChanged();
    }
}
