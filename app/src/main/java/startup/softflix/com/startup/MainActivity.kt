package startup.softflix.com.startup

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.song_ticket.view.*

class MainActivity : AppCompatActivity() {

    //list of songs
    var listSongs=ArrayList<SongInfo>()

    var adapter:MySongAdapter?=null

    //instance of media player builtin class that allows to run music etc

    var mp:MediaPlayer?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoadURLOnline()
        adapter=MySongAdapter(listSongs)
        lsListSongs.adapter=adapter

        var mytracking= mySongTrack()
        mytracking.start()

    }

    fun LoadURLOnline(){
        listSongs.add(SongInfo("001","Ahmed","http://server6.mp3quran.net/thubti/001.mp3"))
        listSongs.add(SongInfo("002","Ahmed","http://server6.mp3quran.net/thubti/002.mp3"))
        listSongs.add(SongInfo("003","Alex","http://server6.mp3quran.net/thubti/003.mp3"))
        listSongs.add(SongInfo("004","Ahmed","http://server6.mp3quran.net/thubti/004.mp3"))
        listSongs.add(SongInfo("005","Alex","http://server6.mp3quran.net/thubti/005.mp3"))
    }

    inner class MySongAdapter:BaseAdapter{

        var myListSong= ArrayList<SongInfo>()
        constructor(myListSong:ArrayList<SongInfo>):super(){
            this.myListSong=myListSong
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView= layoutInflater.inflate(R.layout.song_ticket,null)
            val Song= this.myListSong[position]
            myView.tvSongName.text= Song.Title
            myView.tvAuthor.text=Song.AuthorName
            myView.buPlay.setOnClickListener(){
                //play song when someone click
                //first initialize media player
                mp= MediaPlayer()

                if(myView.buPlay.text.equals("Stop")){
                    mp!!.stop()
                    //after successfull playing set the text of button to stop
                    myView.buPlay.text = "Start"
                }else {
                    try {
                        //play from this place
                        mp!!.setDataSource(Song.SongURL)
                        //prepare to run
                        mp!!.prepare()
                        //start
                        mp!!.start()
                        myView.buPlay.text = "Stop"
                        sbProgress.max=mp!!.duration //max value = length of song duration

                    } catch (ex: Exception) {
                    }
                }
            }

            return myView
        }

        override fun getItem(position: Int): Any {
            return this.myListSong[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listSongs.size
        }

    }

    inner class mySongTrack():Thread(){
                override fun run() {
           while (true)
           {
               try {
                   Thread.sleep(1000)
               }
               catch (ex:Exception){}
//always synch progress bar with song play duration
               runOnUiThread() {

                   if (mp!=null){
                   sbProgress!!.progress = mp!!.currentPosition
               }
               }
           }

        }
    }
}
