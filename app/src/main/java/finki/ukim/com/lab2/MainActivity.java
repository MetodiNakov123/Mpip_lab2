package finki.ukim.com.lab2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import finki.ukim.com.lab2.adapter.CustomAdapter;
import finki.ukim.com.lab2.holder.CustomViewHolder;
import finki.ukim.com.lab2.client.OmdbShortApiClient;
import finki.ukim.com.lab2.model.OmdbShortItem;
import finki.ukim.com.lab2.model.OmdbShortResponse;
import finki.ukim.com.lab2.persisntance.repository.OmdbItemRepository;
import finki.ukim.com.lab2.OmdbShortApiInterface.OmdbShortApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private List<OmdbShortItem> mList;
    private CustomAdapter mAdapter;
    private OmdbItemRepository omdbItemRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolBar); //connect custom toolbar and set it as supported ActionBar
        setSupportActionBar(toolbar);


        omdbItemRepository = new OmdbItemRepository(MainActivity.this);
        mList = new ArrayList<>();
        mAdapter = new CustomAdapter(getApplicationContext(),mList, getItemViewOnClickListener());
        mRecyclerView = findViewById(R.id.reciclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);

        LiveData<List<OmdbShortItem>> listLiveData = omdbItemRepository.listAllItems();
        listLiveData.observe(MainActivity.this, new Observer<List<OmdbShortItem>>() {
            @Override
            public void onChanged(@Nullable List<OmdbShortItem> omdbShortItems) {
                if (omdbShortItems != null || omdbShortItems.size() !=0){
                    mAdapter.updateData(omdbShortItems);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_search, menu);
        // Get the SearchView and set the searchable configuration
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                showData(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    private void showData(String s) {
        OmdbShortApiInterface apiInterface = OmdbShortApiClient.getRetrofit().create(OmdbShortApiInterface.class);

        apiInterface.getOmdbItemsShort(s).enqueue(new Callback<OmdbShortResponse>() {
            @Override
            public void onResponse(Call<OmdbShortResponse> call, Response<OmdbShortResponse> response) {
                if(response.body() != null){
                    mList = response.body().getSearch();
                    omdbItemRepository.deleteAll();
                    for (OmdbShortItem item: mList){
                        omdbItemRepository.insertItem(item);
                    }
                }
            }

            @Override
            public void onFailure(Call<OmdbShortResponse> call, Throwable t) {
                Log.d("test","test");
            }
        });
    }

    private View.OnClickListener getItemViewOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomViewHolder holder = (CustomViewHolder) v.getTag();
                int selectedMovieId = mAdapter.getClickedItemId(holder);
                String movieId = Integer.toString(selectedMovieId);

                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
//                intent.putExtra("movieId", movieId);
//                intent.putExtra("title", mAdapter.getClickedItemTitle(holder));
//                intent.putExtra("year", mAdapter.getClickedItemYear(holder));
//                intent.putExtra("image", mAdapter.getClickedItemImage(holder));
                intent.putExtra("imdbID", mAdapter.getClickedItemimdbID(holder));
                startActivity(intent);

            }
        };
    }


}
