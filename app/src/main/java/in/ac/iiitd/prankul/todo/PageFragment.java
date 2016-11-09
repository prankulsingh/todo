package in.ac.iiitd.prankul.todo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Prankul on 08-11-2016.
 */


public class PageFragment extends Fragment {

    TextView title,details;
    int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.page_fragment, container, false);

        title = (TextView) rootView.findViewById(R.id.title);
        details = (TextView) rootView.findViewById(R.id.details);

        setProperties(ListActivity.cards.get(index));

        return rootView;
    }

    public void setProperties(Card card)
    {
        title.setText(card.getTitle());
        details.setText(card.getDetails());
    }

}