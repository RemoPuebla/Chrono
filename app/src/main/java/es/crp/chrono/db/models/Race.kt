package es.crp.chrono.db.models

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Race(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "date") val date: Date = Date(System.currentTimeMillis())
) {
    fun readableDate(context: Context): String {
        return android.text.format.DateFormat.getLongDateFormat(context).format(this.date)
    }
}