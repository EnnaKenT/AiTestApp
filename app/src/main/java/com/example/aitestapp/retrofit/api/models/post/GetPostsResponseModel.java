package com.example.aitestapp.retrofit.api.models.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPostsResponseModel {
    @SerializedName("hits")
    @Expose
    private List<Hit> hits = null;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("nbPages")
    @Expose
    private Integer nbPages;
    @SerializedName("hitsPerPage")
    @Expose
    private Integer hitsPerPage;

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNbPages() {
        return nbPages;
    }

    public void setNbPages(Integer nbPages) {
        this.nbPages = nbPages;
    }

    public Integer getHitsPerPage() {
        return hitsPerPage;
    }

    public void setHitsPerPage(Integer hitsPerPage) {
        this.hitsPerPage = hitsPerPage;
    }
}
