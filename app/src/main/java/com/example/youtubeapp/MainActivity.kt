package com.example.youtubeapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MainActivity : AppCompatActivity() {

    private var videos : Array<Array<String>> = arrayOf(
        arrayOf("Humpty Dumpty Song" , "BD9LoGgwIGE" ,
            "http://img.youtube.com/vi/BD9LoGgwIGE/0.jpg"),
        arrayOf("Baby Shark Dance","XqZsoesa55w",
            "http://img.youtube.com/vi/XqZsoesa55w/0.jpg"),
        arrayOf("Play Outside Song","QT8YJ8xQFcQ",
            "http://img.youtube.com/vi/QT8YJ8xQFcQ/0.jpg"),
        arrayOf("Vlad and Nikita Ride on Toy Sportbike","dTH25btDjC8",
            "http://img.youtube.com/vi/dTH25btDjC8/0.jpg"))

    private lateinit var ytVideoView: YouTubePlayerView
    private lateinit var player: YouTubePlayer
    private var videoId = 0
    private var startSeconds = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         checkConnection()

        ytVideoView = findViewById(R.id.ytPlayer)
        ytVideoView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            //Called every time the state of the player changes.
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                //
                player = youTubePlayer
                player.loadVideo(videos[videoId][1], startSeconds)

                val recyclerView: RecyclerView = findViewById(R.id.rvVideos)
                recyclerView.adapter = Videos(videos, player)
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.setHasFixedSize(true)
            }
        })

    }
    private fun checkConnection(){
        if(!connectedToInternet()){
            AlertDialog.Builder(this@MainActivity)
                .setTitle("something went wrong, check you Internet connection!")
                .setPositiveButton("RETRY"){_, _ -> checkConnection()}
                .show()
        }
    }

    private fun connectedToInternet(): Boolean{
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}