package es.crp.chrono.views

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.input.input
import es.crp.chrono.Chrono
import es.crp.chrono.R
import es.crp.chrono.R2
import es.crp.chrono.db.models.Race
import es.crp.chrono.db.repositories.RaceRepository
import es.crp.chrono.views.adapters.RaceAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    @BindView(R2.id.races_list)
    lateinit var racesRecyclerView: RecyclerView

    private lateinit var raceRepository: RaceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        ButterKnife.bind(this)

        racesRecyclerView.layoutManager = LinearLayoutManager(this)
        racesRecyclerView.adapter = RaceAdapter(this, listOf())

        raceRepository = Chrono.database?.raceDao()?.let { RaceRepository(it) }!!
        raceRepository.allRaces.observe(this, Observer<List<Race>> { races: List<Race> ->
            Log.d(TAG, races.toString())
            racesRecyclerView.adapter = RaceAdapter(this, races)
        })

        fab.setOnClickListener { fabButtonClickListener() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
        CoroutineScope(Dispatchers.IO).launch {
            val result = raceRepository.insert(Race(name = name, date = date))
            Log.d("Created", result.toString())
        }
    }
}
