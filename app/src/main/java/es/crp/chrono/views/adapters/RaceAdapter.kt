package es.crp.chrono.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import es.crp.chrono.R
import es.crp.chrono.R2
import es.crp.chrono.db.models.Race


class RaceAdapter(private val context: Context, private val dataset: List<Race>) :
    RecyclerView.Adapter<RaceAdapter.RaceViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaceViewHolder {
        return RaceViewHolder(inflater.inflate(R.layout.item_race, parent, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RaceViewHolder, position: Int) {
        val race = dataset[position]

        holder.raceName.text = race.name
        holder.raceDate.text = race.readableDate(this.context)
    }

    class RaceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @BindView(R2.id.race_name)
        lateinit var raceName: TextView
        @BindView(R2.id.race_date)
        lateinit var raceDate: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }
}