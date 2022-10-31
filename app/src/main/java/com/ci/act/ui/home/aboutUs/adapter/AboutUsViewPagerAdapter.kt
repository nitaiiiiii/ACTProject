package com.ci.act.ui.home.aboutUs.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.ci.act.R
import com.ci.act.ui.home.aboutUs.model.AboutUsModel
import com.ci.act.ui.home.subscriptions.model.SubscriptionModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView

class AboutUsViewPagerAdapter : RecyclerView.Adapter<AboutUsViewPagerAdapter.AboutUsViewHolder>() {

    val aboutUsArray = ArrayList<AboutUsModel>()
    var playerView: PlayerView? = null
    var simpleExoPlayer: SimpleExoPlayer? = null


    inner class AboutUsViewHolder(aboutUsView: View) : RecyclerView.ViewHolder(aboutUsView) {

        var playeerView: PlayerView? = aboutUsView.findViewById(R.id.playerView)


        fun aboutUsBindItem(aboutUsModel: AboutUsModel) {

            simpleExoPlayer = SimpleExoPlayer.Builder(itemView.context).build()
            playerView = playeerView
            playerView?.player = simpleExoPlayer

            val url: Uri =
                Uri.parse("android.resource://" + itemView.context.packageName.toString() + "/" + aboutUsModel.video)
            val media = MediaItem.fromUri(url)

            simpleExoPlayer?.addMediaItem(media)

            simpleExoPlayer?.seekTo(0, 0L)
            simpleExoPlayer?.prepare()
            simpleExoPlayer?.play()


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutUsViewHolder {
        val aboutUs =
            LayoutInflater.from(parent.context).inflate(R.layout.player_view, parent, false)
        return AboutUsViewHolder(aboutUs)
    }

    override fun onBindViewHolder(holder: AboutUsViewHolder, position: Int) {
        holder.aboutUsBindItem(aboutUsArray[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return aboutUsArray.size
    }

    fun addArrayList(list: ArrayList<AboutUsModel>) {
        aboutUsArray.clear()
        aboutUsArray.addAll(list)
        notifyDataSetChanged()
    }
}