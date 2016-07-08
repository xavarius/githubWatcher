package adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.maciejmalak.githubstatistics.R
import com.maciejmalak.githubstatistics.model.Account

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val dataSet : MutableList<Account> = java.util.ArrayList<Account>()

    fun addToDataSet(user : Account) {
        dataSet.add(user)
        notifyDataSetChanged()
    }

    /*ViewHolder is a constructor and RecyclerView.ViewHolder(view) is a call to super class*/
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val userAvatar : ImageView by bindView(R.id.userAvatar)
        val userName : TextView by bindView(R.id.userName)
        val repoNbr : TextView by bindView(R.id.reposNbr)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent!!.context)
        val rootView : View = inflater.inflate(R.layout.one_user_row, parent, false)

        val viewHolder = ViewHolder(rootView)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as ViewHolder
        if( !dataSet.isEmpty()) {
            holder.userName.text = dataSet[position].name
            holder.repoNbr.text = dataSet[position].publicRepos.toString()
        }
    }

    override fun getItemCount(): Int = dataSet.size
}
