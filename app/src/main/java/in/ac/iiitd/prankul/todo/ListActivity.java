package in.ac.iiitd.prankul.todo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    static ArrayList<Card> cards =  new ArrayList<>();
    Adapter adapter;
    RecyclerView recyclerView;
    Button button_save, button_discard;
    EditText new_title, new_details;
    static Boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.rcv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(cards);
        recyclerView.setAdapter(adapter);
        initSwipe();
        readData();
        adapter.notifyDataSetChanged();






        if(flag==true)
        {
            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ListActivity.this);
            dialogBuilder.setTitle("Add new ToDo item");
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_dialog, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();

            new_title = (EditText) dialogView.findViewById(R.id.new_title);
            new_details = (EditText) dialogView.findViewById(R.id.new_details);
            new_title.setText(savedInstanceState.getString("title"));
            new_details.setText(savedInstanceState.getString("details"));
            button_save = (Button) dialogView.findViewById(R.id.button_save);
            button_discard = (Button) dialogView.findViewById(R.id.button_discard);
            button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(new_title.getText().toString().isEmpty() ||
                            new_details.getText().toString().isEmpty())
                    {
                        new_title.setHint("Title - This is a required field");
                        new_details.setHint("Details - This is a required field");
                    }
                    else
                    {
                        cards.add(new Card(new_title.getText().toString(),new_details.getText().toString()));
                        adapter.notifyDataSetChanged();
                        flag=false;
                        alertDialog.cancel();
                    }
                }
            });
            button_discard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag=false;
                    alertDialog.cancel();
                }
            });
            flag=true;
            alertDialog.show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                adapter.removeItem(position);
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            cards.clear();
            adapter.notifyDataSetChanged();
        }
        if (id == R.id.add){
            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ListActivity.this);
            dialogBuilder.setTitle("Add new ToDo item");
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_dialog, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();

            new_title = (EditText) dialogView.findViewById(R.id.new_title);
            new_details = (EditText) dialogView.findViewById(R.id.new_details);
            button_save = (Button) dialogView.findViewById(R.id.button_save);
            button_discard = (Button) dialogView.findViewById(R.id.button_discard);
            button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(new_title.getText().toString().isEmpty() ||
                            new_details.getText().toString().isEmpty())
                    {
                        new_title.setHint("Title - This is a required field");
                        new_details.setHint("Details - This is a required field");
                    }
                    else
                    {
                        cards.add(new Card(new_title.getText().toString(),new_details.getText().toString()));
                        adapter.notifyDataSetChanged();
                        flag=false;
                        alertDialog.cancel();
                    }
                }
            });
            button_discard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag=false;
                    alertDialog.cancel();
                }
            });
            flag=true;
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(flag==true)
        {
            outState.putString("title",new_title.getText().toString());
            outState.putString("details",new_details.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    public void saveData()
    {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int i=0;
        for(Card card : cards)
        {
            editor.putString(""+i,card.getTitle()+"~"+card.getDetails());
            i++;
        }
        editor.putInt("size",cards.size());
        editor.commit();
    }

    public void readData()
    {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String s = new String();
        int i=0;
        cards.clear();
        String msg = sharedPref.getString(""+i,"~");
        int size = sharedPref.getInt("size",99);
        while(i<size && !msg.equals("~"))
        {
            String a,b;
            a = msg.split("~")[0];
            b = msg.split("~")[1];
            cards.add(new Card(a,b));
            i++;
            msg = sharedPref.getString(""+i,"~");
        }
    }
}
