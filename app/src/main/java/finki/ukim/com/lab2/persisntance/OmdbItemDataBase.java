package finki.ukim.com.lab2.persisntance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import finki.ukim.com.lab2.model.OmdbShortItem;
import finki.ukim.com.lab2.persisntance.dao.OmdbItemDao;

@Database(entities = {OmdbShortItem.class},version = 1,exportSchema = false)
public abstract class OmdbItemDataBase extends RoomDatabase {

    public abstract OmdbItemDao OmdbItemDao();
}
