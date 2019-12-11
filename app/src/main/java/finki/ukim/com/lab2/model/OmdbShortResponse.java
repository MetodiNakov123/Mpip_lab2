package finki.ukim.com.lab2.model;

import java.util.List;

public class OmdbShortResponse {

    private List<OmdbShortItem> Search;

    public List<OmdbShortItem> getSearch() {
        return Search;
    }

    public void setmItems(List<OmdbShortItem> Search) {
        this.Search = Search;
    }
}
