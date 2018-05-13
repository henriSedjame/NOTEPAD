package training.androidkotlin.notepadapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import training.androidkotlin.notepadapp.Delegates.contentView
import training.androidkotlin.notepadapp.databinding.ActivityNoteDetailBinding
import training.androidkotlin.notepadapp.models.Note

class NoteDetailActivity : AppCompatActivity() {

  companion object {
    val EXTRA_NOTE = "mNote";
    val EXTRA_INDEX = "indexNote";
    val REQUEST_CODE_EDIT_NOTE= 1;
  }

  //Data binding
  val binding: ActivityNoteDetailBinding? by contentView<NoteDetailActivity, ActivityNoteDetailBinding>(R.layout.activity_note_detail)
  var indexNote : Int = -1;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState);

    binding?.apply {
      currentNote = intent.getParcelableExtra<Note>(EXTRA_NOTE);
      setSupportActionBar(toolbar);
    }
    indexNote = intent.getIntExtra(EXTRA_INDEX, -1);

    supportActionBar!!.setDisplayHomeAsUpEnabled(true);
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.activity_note_detail, menu);
    return true;
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {

    when (item.itemId) {

      R.id.item_save -> {

        saveNote()

        return true;
      }
      else -> return super.onOptionsItemSelected(item);
    }
  }

  private fun saveNote() {
    var note = binding?.currentNote;
    var intent = Intent();
    intent.putExtra(EXTRA_NOTE, note);
    intent.putExtra(EXTRA_INDEX, indexNote);
    setResult(Activity.RESULT_OK, intent);
    finish();
  }
}

