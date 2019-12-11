package finki.ukim.com.lab2.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "omdb_item")
public class OmdbShortItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "title")
    private String Title;
    @ColumnInfo(name = "year")
    private String Year;

    @ColumnInfo(name = "imdbID")
    private String imdbID;
    @ColumnInfo(name = "poster")
    private String Poster;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
