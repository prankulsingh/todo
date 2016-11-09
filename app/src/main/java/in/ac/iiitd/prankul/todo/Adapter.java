package in.ac.iiitd.prankul.todo;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prankul on 08-11-2016.
 */

public class Adapter extends RecyclerView.Adapter {
    ArrayList<Card> cards;

    public Adapter(ArrayList cards) {
        this.cards = cards;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        ((RecycleViewHolder)viewHolder).title.setText(cards.get(i).getTitle());
        String s = "";
        if(cards.get(i).getDetails().length()>40)
        {
            s = " . . .";
        }
        ((RecycleViewHolder)viewHolder).details.setText(
                cards.get(i).getDetails().substring(0,min(cards.get(i).getDetails().length(),40))+s);
    }

    @Override
    public int getItemCount() {
        if(cards==null)
        {
            return 0;
        }
        return cards.size();
    }

    public void addItem(Card card) {
        cards.add(card);
        notifyItemInserted(cards.size());
    }

    public void removeItem(int position) {
        cards.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cards.size());
    }
    public class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView details;
        public RecycleViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            title = (TextView)view.findViewById(R.id.title);
            details = (TextView)view.findViewById(R.id.details);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), InformationActivity.class);
            i.putExtra("INDEX",getAdapterPosition());
            v.getContext().startActivity(i);
        }
    }

    int min(int a,int b) {return a<b?a:b ;}
}