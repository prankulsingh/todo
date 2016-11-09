package in.ac.iiitd.prankul.todo;

import android.app.*;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    ArrayList<PageFragment> pfl;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        index = getIntent().getIntExtra("INDEX",0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pfl = new ArrayList<>();
        int i=0;
        for(Card card : ListActivity.cards)
        {
            PageFragment pf = new PageFragment();
            pfl.add(pf);
            pf.index = i;
            i++;
        }

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(this,getSupportFragmentManager(),pfl);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(getIntent().getIntExtra("INDEX",0));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private final ArrayList<PageFragment> fragments;
        private final Context context;

        public ScreenSlidePagerAdapter(final Context context, final FragmentManager fragmentManager,
                                       final ArrayList<PageFragment> fragments) {
            super(fragmentManager);
            this.context = context;
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(final int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.edit){
//            final android.support.v7.app.AlertDialog.Builder dialogBuilder =
//                    new android.support.v7.app.AlertDialog.Builder(InformationActivity.this);
//            dialogBuilder.setTitle("Edit ToDo item");
//            LayoutInflater inflater = getLayoutInflater();
//            View dialogView = inflater.inflate(R.layout.alert_dialog, null);
//            dialogBuilder.setView(dialogView);
//            final android.support.v7.app.AlertDialog alertDialog = dialogBuilder.create();
//
//            final EditText new_title = (EditText) dialogView.findViewById(R.id.new_title);
//            final EditText new_details = (EditText) dialogView.findViewById(R.id.new_details);
//            Button button_save = (Button) dialogView.findViewById(R.id.button_save);
//            Button button_discard = (Button) dialogView.findViewById(R.id.button_discard);
//            button_save.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if(new_title.getText().toString().isEmpty() ||
//                            new_details.getText().toString().isEmpty())
//                    {
//                        new_title.setHint("Title - This is a required field");
//                        new_details.setHint("Details - This is a required field");
//                    }
//                    else
//                    {
//                        ListActivity.cards.remove(index);
//                        ListActivity.cards.add(index,
//                                new Card(new_title.getText().toString(),new_details.getText().toString()));
//                        pfl.get(index).setProperties(new Card(new_title.getText().toString(),new_details.getText().toString()));
//                        alertDialog.cancel();
//                    }
//
//                }
//            });
//            button_discard.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    alertDialog.cancel();
//                }
//            });
//
//            alertDialog.show();
//        }

        return super.onOptionsItemSelected(item);
    }
}
