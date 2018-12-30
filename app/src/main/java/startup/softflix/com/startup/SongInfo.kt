package startup.softflix.com.startup

/**
 * Created by Zanis on 12/30/2018.
 */
 class SongInfo{

    var Title:String?=null
    var AuthorName:String?=null
    var SongURL:String?=null

    constructor(Title:String, AuthorName:String, SongURL:String){
        this.Title=Title
        this.AuthorName=AuthorName
        this.SongURL=SongURL
    }
}