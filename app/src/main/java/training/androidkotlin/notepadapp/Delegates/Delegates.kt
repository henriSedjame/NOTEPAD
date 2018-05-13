package training.androidkotlin.notepadapp.Delegates

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import training.androidkotlin.notepadapp.models.Note
import kotlin.reflect.KProperty


class SetContentView<in R : Activity, out T : ViewDataBinding>(@LayoutRes private val layoutRes : Int){

    private var value: T? = null;

    operator fun getValue(ref : Activity, property : KProperty<*>) : T? {

        value = value ?: DataBindingUtil.setContentView(ref, layoutRes);

        return value;
    }
}

fun <R : Activity, T : ViewDataBinding> contentView(@LayoutRes layoutRes : Int) : SetContentView<R, T> {
    return SetContentView(layoutRes);
}

fun getNotes(): MutableList<Note>{
  var notes :  MutableList<Note> = mutableListOf();

  notes.add(Note("Note 1", "Contenu de la note 1"));
  notes.add(Note("Note 2", "Contenu de la note 2"));
  notes.add(Note("Note 3", "Contenu de la note 3"));
  notes.add(Note("Note 4", "Contenu de la note 4"));
  notes.add(Note("Note 5", "Contenu de la note 5"));
  notes.add(Note("Note 6", "Contenu de la note 6"));
  notes.add(Note("Note 7", "Contenu de la note 7"));
  notes.add(Note("Note 8", "Contenu de la note 8"));
  notes.add(Note("Note 9", "Contenu de la note 9"));
  notes.add(Note("Note 10", "Contenu de la note 10"));
  notes.add(Note("Note 11", "Contenu de la note 11"));
  notes.add(Note("Note 12", "Contenu de la note 12"));
  notes.add(Note("Note 13", "Contenu de la note 13"));
  notes.add(Note("Note 14", "Contenu de la note 14"));
  notes.add(Note("Note 15", "Contenu de la note 15"));

  return notes;
}
