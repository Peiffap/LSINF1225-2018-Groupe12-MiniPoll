package be.lsinf1225gr12.minipoll.minipoll.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.Poll;


public class MyListSondageAdaptator extends BaseAdapter {
    /**
     * Permet d'instancier un fichier xml de layout dans une vue.
     */
    private final LayoutInflater mInflater;

    /**
     * Liste des éléments de collection à mettre dans la liste.
     */
    private ArrayList<Poll> polls;

    /**
     * Constructeur.
     *
     * @param context        Contexte de l'application.
     * @param polls Liste des éléments de collection à placer dans la liste.
     */
    public MyListSondageAdaptator(Context context, ArrayList<Poll> polls) {
        mInflater = LayoutInflater.from(context);
        this.polls = polls;
    }


    @Override
    public int getCount() {
        return polls.size();
    }

    @Override
    public Object getItem(int position) {
        return polls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return polls.get(position).getAuthor().getId();
    }

    /**
     * Remplit chaque ligne de la liste avec un layout particulier.
     * <p>
     * Cette méthode est appelée par Android pour construire la vue de la liste (lors de la
     * construction de listview).
     */

    public View getView(int position, View convertView, ViewGroup parent) {
        // Si la vue n'a pas encore été créé (typiquement lors du première affichage de la liste).
        // Android recycle en effet les layout déjà chargés des éléments de la liste (par exemple
        // lors du changement de l'ordre dans la liste.)

        if (convertView == null) {
            // Création d'un nouvelle vue avec le layout correspondant au fichier xml
            convertView = mInflater.inflate(R.layout.collected_item_row, parent, false);
        }

        // Récupération des deux éléments de notre vue dans le but d'y placer les données.
        TextView nameTextView = convertView.findViewById(R.id.show_row_name);
        TextView authorTextView= convertView.findViewById(R.id.show_row_author);

        // Récupération et placement des données.
        Poll poll= polls.get(position);
        nameTextView.setText(poll.getName());
        authorTextView.setText(poll.getAuthor().getName());

        return convertView;
    }

    /**
     * Change la liste des éléments de collection affichée.
     * <p>
     * Permet de changer complètement la liste des éléments affichés dans la liste.
     *
     * @param newPoll La nouvelle liste des éléments de collection à afficher.
     */
    public void setPolls(ArrayList<Poll> newPoll) {
        this.polls = newPoll;

    }
}
