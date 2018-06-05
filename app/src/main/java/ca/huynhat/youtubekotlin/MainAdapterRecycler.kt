package ca.huynhat.youtubekotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.huynhat.youtubekotlin.Model.HomeFeed
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapterRecycler(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInfater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInfater.inflate(R.layout.video_row,parent, false)

        return CustomViewHolder(cellForRow)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val stadium = homeFeed.stadiums.get(position)
        holder?.view?.statium_name?.text=stadium.name + " ," + stadium.city

        val statiumImage = holder?.view?.statium_img
        Picasso.get().load(stadium.image).into(statiumImage)


    }

    //Number of items
    override fun getItemCount(): Int {
        return homeFeed.stadiums.count()
    }
}

class CustomViewHolder(val view: View) :RecyclerView.ViewHolder(view) {

}