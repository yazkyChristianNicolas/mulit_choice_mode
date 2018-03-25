package ar.com.yazkychristian.multichoicemodeexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ar.com.yazkychristian.multichoicemodeexample.adapters.ItemArrayAdapter;
import ar.com.yazkychristian.multichoicemodeexample.vo.Item;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView itemList;
    private List<Item> itemDataset = new ArrayList<>();
    private ItemArrayAdapter adapter;
    private List<Item> itemsSelecteds = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        setUpView();
        setMultiChoiceMode();
    }

    private void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.list_title));
        setSupportActionBar(toolbar);
    }

    private void setUpView(){
        itemList = findViewById(R.id.list);
        for(int i = 0; i < 5; i++){
            itemDataset.add(new Item("Name " + i, "Description " + i));
        }
        adapter = new ItemArrayAdapter(MainActivity.this,itemDataset);
        itemList.setAdapter(adapter);
    }

    private void setMultiChoiceMode(){
        itemList.setClickable(true);
        itemList.setLongClickable(true);
        itemList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        itemList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            int contadorItemsSeleccionados = 0;
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
                Item itemSelected = adapter.getItem(position);
                contadorItemsSeleccionados = itemList.getCheckedItemCount();
                if (checked) {
                    itemsSelecteds.add(itemSelected);
                } else {
                    itemsSelecteds.remove(itemSelected);
                }

                if (contadorItemsSeleccionados > 1) {
                    actionMode.getMenu().findItem(R.id.edit).setVisible(false);
                } else {
                    actionMode.getMenu().findItem(R.id.edit).setVisible(true);
                }

                if (contadorItemsSeleccionados == 0) {
                    actionMode.finish();
                }

                actionMode.setTitle(String.format(getResources().getString(R.string.items_selected), String.valueOf(contadorItemsSeleccionados)));
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                itemsSelecteds.clear();
                contadorItemsSeleccionados = 0;
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.delete:
                        Toast.makeText(MainActivity.this, "Remove!", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.edit:
                        Toast.makeText(MainActivity.this, "Edit!", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
    }
}
