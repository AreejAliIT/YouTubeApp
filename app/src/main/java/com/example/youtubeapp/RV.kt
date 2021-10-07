package com.example.youtubeapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.squareup.picasso.Picasso
import java.net.URL

class Videos(private val videos:Array<Array<String>>, private val player:YouTubePlayer) :
    RecyclerView.Adapter<Videos.ViewHolder>(){

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)
    // for binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var videoName =videos[position][0]
        var videoId =  videos[position][1]
        var thumbnail = videos[position][2]
        Log.d("RV","the thumbnail is --> $thumbnail")
        holder.itemView.apply {
            // for binding
            var btn = findViewById<Button>(R.id.btVideoRV)
            btn.text = videoName
           // var img = findViewById<ImageView>(R.id.img_thumnail)
            //downlode thumbnails
//            Picasso.get().load("http://img.youtube.com/vi/dTH25btDjC8/0.jpg").into(findViewById<ImageView>(R.id.img_thumnail));
//            println("Picasso," + Picasso.get().load("$thumbnail").toString())
            btn.setOnClickListener {
                player.loadVideo(videoId, 0f)
            }
        }
    }
    override fun getItemCount() = videos.size

}




