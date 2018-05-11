package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.adapter.MyListViewAdapter;
import be.lsinf1225gr12.minipoll.minipoll.model.Poll;

/**
 * Gère l'affichage sous forme de liste des éléments de la collection de l'utilisateur en cours. Si
 * une requête de recherche est passée dans l'Intent, la recherche est effectuée et la liste des
 * éléments affichés sera la liste des résultats.
 *
 * @author Damien Mercier
 * @version 1
 */
public class ShowListActivity extends Activity implements OnItemClickListener {

    private ArrayList<Poll> polls;
    private MyListViewAdapter myListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_list);

        // Chargement des éléments à afficher dans la variable de classe songs
        loadPolls();


        ListView myListView = findViewById(R.id.show_listView);

        // Création de l'adapter pour faire la liaison entre les données (polls) et
        // l'affichage de chaque ligne de la liste.
        myListViewAdapter = new MyListViewAdapter(this, polls);
        myListView.setAdapter(myListViewAdapter);

        // Indique que le clic d'un élément de la liste doit appeler la méthode onItemClick d
        // cette classe (this).
        myListView.setOnItemClickListener(this);
    }

    /**
     * Charge la liste des éléments de collection dans la variables de classe polls.
     * <p>
     * Charge la liste des éléments de la collection de l'utilisateur connecté et si une requête de
     * recherche est passée lors du lancement de l'activité, effectue la recherche et charge la
     * liste des résultats.
     */
    private void loadPolls() {

        // Récupération de la requête de recherche.
        // Si aucune requête n'a été passée lors de la création de l'activité, searchQuery sera null.
        String searchQuery = getIntent().getStringExtra("searchQuery");

        if (searchQuery == null) {
            polls = Poll.getPolls();
        } else {
            // polls = Poll.searchPolls(searchQuery);
        }

        // S'il n'y a aucun éléments dans la liste, il faut afficher un message. Ce message est différent
        // s'il y avait une requête de recherche (message du type "Aucun résultat trouvé") ou si
        // l'utilisateur vient directement du menu principal et veut tout afficher (message du type
        // "Aucun élément n'est présent dans votre collection).
        if (polls.isEmpty()) {
            if (searchQuery == null) {
                MiniPollApp.notifyShort(R.string.show_list_error_no_item);
            } else {
                MiniPollApp.notifyShort(R.string.show_list_no_result);
            }
            // Cloture de l'activité d'affichage de la liste (car liste vide). Retour à l'écran
            // précédent.
            finish();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        // La liste des éléments est ici rechargées car en cas de modification d'un élément, l'ordre
        // a peut-être changé.

        loadPolls();

        myListViewAdapter.setPolls(polls);
    }

    /**
     * Lance l'activité de vue des détails d'un élément de collection lors du clic sur un élément de
     * la liste.
     *
     * @param position Position de l'élément dans la liste.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, MainActivity.class);
        // L'id de l'élément de collection est passé en argument afin que la vue de détails puisse
        // récupérer celui-ci.
        intent.putExtra("authorId", polls.get(position).getAuthor().getId());
        intent.putExtra("date", polls.get(position).getDate());
        startActivity(intent);
    }

}