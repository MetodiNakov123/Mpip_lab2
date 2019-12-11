package finki.ukim.com.lab2.persisntance.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import finki.ukim.com.lab2.model.OmdbShortItem;

@Dao
public interface OmdbItemDao {

    @Insert
    void insertItem(OmdbShortItem omdbShortItem);

    @Query("SELECT * FROM omdb_item c ORDER BY c.title")
    LiveData<List<OmdbShortItem>> getAllSync();

    @Query("DELETE from omdb_item")
    void deleteAll();

    @Query("SELECT * FROM omdb_item WHERE imdbID = :imdbID")
    LiveData<List<OmdbShortItem>> findItemByImdbId (String imdbID);

}
