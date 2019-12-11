package finki.ukim.com.lab2.persisntance.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import finki.ukim.com.lab2.model.OmdbShortItem;
import finki.ukim.com.lab2.persisntance.OmdbItemDataBase;

public class OmdbItemRepository {

    private OmdbItemDataBase omdbItemDataBase;

    private Context mContext;

    public OmdbItemRepository(Context mContext) {
        this.mContext = mContext;
        omdbItemDataBase = Room.databaseBuilder(mContext,OmdbItemDataBase.class,"omdb_database").build();
    }


    public void insertItem(final OmdbShortItem omdbShortItem){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                omdbItemDataBase.OmdbItemDao().insertItem(omdbShortItem);
                return null;
            }
        }.execute();
    }

    public LiveData<List<OmdbShortItem>> listAllItems(){
        return omdbItemDataBase.OmdbItemDao().getAllSync();
    }

    public void deleteAll(){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                omdbItemDataBase.OmdbItemDao().deleteAll();
                return null;
            }
        }.execute();
    }

    public LiveData<List<OmdbShortItem>> findItemsByimdbID (String imdbId){
        return omdbItemDataBase.OmdbItemDao().findItemByImdbId(imdbId);
    }
}
