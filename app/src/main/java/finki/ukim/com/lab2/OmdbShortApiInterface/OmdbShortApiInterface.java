package finki.ukim.com.lab2.OmdbShortApiInterface;

import finki.ukim.com.lab2.model.OmdbShortResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbShortApiInterface {

    @GET("/?apikey=2835bbe4&type=movie&plot=short&r=json")
    Call<OmdbShortResponse> getOmdbItemsShort(@Query("s") String s);
}
