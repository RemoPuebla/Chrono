package es.crp.chrono.views

import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.input.input
import es.crp.chrono.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { fabButtonClickListener() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fabButtonClickListener() {
        MaterialDialog(this).show {
            datePicker { _, date ->
                MaterialDialog(this@MainActivity).show {
                    input(
                        hintRes = R.string.name,
                        inputType = InputType.TYPE_CLASS_TEXT
                    ) { _, name ->
                        createRace(name.toString(), date.time)
                    }
                    positiveButton(R.string.create)
                }
            }
            positiveButton(res = R.string.next)
        }
    }

    private fun createRace(name: String, date: Date) {

    }
}
