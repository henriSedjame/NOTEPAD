package training.androidkotlin.notepadapp

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import training.androidkotlin.notepadapp.Delegates.getNotes
import training.androidkotlin.notepadapp.View.Adapters.NoteAdapter
import training.androidkotlin.notepadapp.databinding.ActivityNoteListBinding
import training.androidkotlin.notepadapp.models.Note

class NoteListActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var notes: MutableList<Note>;
    lateinit var adapter: NoteAdapter;
    lateinit var recyclerView : RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityNoteListBinding>(this, R.layout.activity_note_list);

        binding.apply {
          setSupportActionBar(toolbar);
          recyclerView= notesRecyclerView;
        }

        notes = getNotes();

        adapter = NoteAdapter(notes, this);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter = adapter;
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

      if (resultCode != Activity.RESULT_OK || data == null){
        return;
      }

      when(requestCode){

        NoteDetailActivity.REQUEST_CODE_EDIT_NOTE -> {
          processEditNoteResult(data)
        }
      }

    }

  private fun processEditNoteResult(data: Intent) {
    var index = data.getIntExtra(NoteDetailActivity.EXTRA_INDEX, -1);
    var note = data.getParcelableExtra<Note>(NoteDetailActivity.EXTRA_NOTE);

    saveNote(index, note)
  }

  private fun saveNote(index: Int, note: Note) {
    notes[index] = note;

    adapter.notifyDataSetChanged();
  }

  override fun onClick(view: View) {

        if (view.tag  != null) {
            val pos = view.tag as Int;
            showNoteDetail(pos);
        }
    }

    fun showNoteDetail(noteIndex: Int){

        val intent = Intent(this, NoteDetailActivity::class.java);
        val note = notes[noteIndex];
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note);
        intent.putExtra(NoteDetailActivity.EXTRA_INDEX, noteIndex);
        startActivityForResult(intent, NoteDetailActivity.REQUEST_CODE_EDIT_NOTE);
    }
}
