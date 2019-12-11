package finki.ukim.com.lab2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import finki.ukim.com.lab2.model.OmdbShortItem;
import finki.ukim.com.lab2.persisntance.repository.OmdbItemRepository;

public class MovieDetailsActivity extends AppCompatActivity {

    public TextView title;
    public TextView year;
    public ImageView imageView;
    public OmdbItemRepository repository;
    public TextView imdbID;
    // public OmdbShortItem movieE;

    // Logger logger = Logger.getLogger("MovieDetailsActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        initViews();
        bindViews();


//        repository = new OmdbItemRepository(MovieDetailsActivity.this);
//
//
//        LiveData<OmdbShortItem> movie = repository.findItemById(Integer.parseInt(getIntent().getStringExtra("movieId")));
//
//        movie.observe(MovieDetailsActivity.this, new Observer <OmdbShortItem>() {
//            @Override
//            public void onChanged(@Nullable OmdbShortItem omdbShortItem) {
//
//                    movieE = omdbShortItem;
//                }
//
//        });
//
//        logger.info(Integer.toString(movieE.getId()));


// ------------------------------------------------------------------------------------------
//        title = findViewById(R.id.textTitle);
//        title.setText("Title: " + getIntent().getStringExtra("title"));
//
//        year = findViewById(R.id.textYear);
//        year.setText("Year: " + getIntent().getStringExtra("year"));
//
//        String imdbID = getIntent().getStringExtra("imdbID");
//
//        imageView = findViewById(R.id.imageView);
//
//        String image = getIntent().getStringExtra("image");
//        Glide.with(getApplicationContext()).load(image).into(imageView);

//---------------------------------------------------------------------------------------------

    }

    public void initViews() {
        imageView = (ImageView) findViewById(R.id.imageView);
        title = (TextView) findViewById(R.id.textTitle);
        year = (TextView) findViewById(R.id.textYear);
        imdbID = findViewById(R.id.textImdbID);

    }


    public void bindViews() {
        String searchImDbId = getIntent().getStringExtra("imdbID");
        repository = new OmdbItemRepository(MovieDetailsActivity.this);

        LiveData<List<OmdbShortItem>> ldItems = repository.findItemsByimdbID(searchImDbId);

        ldItems.observe(MovieDetailsActivity.this, new Observer<List<OmdbShortItem>>() {
            @Override
            public void onChanged(@Nullable List<OmdbShortItem> items) {
                if (items == null || items.size() == 0) {
                    // nishto
                } else {
                    OmdbShortItem m = items.get(0);
                    Glide.with(MovieDetailsActivity.this)
                            .load(m.getPoster())
                            .into(imageView);
                    title.setText("Title: " + m.getTitle());
                    year.setText("Year: " + m.getYear());
                    imdbID.setText("ImdbID: " + m.getImdbID() );

                }
            }
        });

    }
}
