package training.androidkotlin.notepadapp.View.Adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import training.androidkotlin.notepadapp.R
import training.androidkotlin.notepadapp.databinding.ItemNoteBinding
import training.androidkotlin.notepadapp.models.Note

/**
 * Created by Henri-Joel SEDDJAME on 12/05/2018.
 */
class NoteAdapter(val notes : List<Note>, val listener: View.OnClickListener) : RecyclerView.Adapter<NoteAdapter.ViewHolder> (){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cardView = itemView.findViewById<CardView>(R.id.card_view) as CardView;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_note, parent, false);
        return ViewHolder(viewItem);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = DataBindingUtil.bind<ItemNoteBinding>(holder.itemView);
        binding?.note = notes[position];
        holder.cardView.setOnClickListener(listener);
        holder.cardView.tag = position;
    }

    override fun getItemCount(): Int {
        return notes.size;
    }


}
