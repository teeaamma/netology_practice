    package com.example.netology_practice

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.DiffUtil
    import androidx.recyclerview.widget.RecyclerView

    class StatsAdapter(val stats: MutableList<GameStatsEntity>):
        RecyclerView.Adapter<StatsAdapter.StatsHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.list_element_layout, parent, false)
            val holder = StatsHolder(view)
            return holder
        }

        class StatsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var item_time: TextView
            var item_hit: TextView
            var item_count: TextView
            var item_percent: TextView
            init {
                item_time = itemView.findViewById(R.id.game_time)
                item_hit = itemView.findViewById(R.id.game_hit)
                item_count = itemView.findViewById(R.id.game_count)
                item_percent = itemView.findViewById(R.id.game_percent)
            }
        }

        override fun onBindViewHolder(holder: StatsHolder, position: Int) {
            holder.item_time.text = "Время игры: " + stats[position].time + "c"
            holder.item_hit.text = "Количество попаданий: " + stats[position].hit_count
            holder.item_count.text = "Количество нажатий: " + stats[position].count
            if (stats[position].count > 0)
                holder.item_percent.text = "Процент попаданий: " + (stats[position].hit_count.toFloat() / stats[position].count * 100).toString() + "%"
            else
                holder.item_percent.text = "Процент попаданий: 0.0%"
        }

        override fun getItemCount(): Int {
            return stats.size
        }
    }