package com.frank.moviebook.Util;

/**
 * Created by FRANK on 19/11/2017.
 */

public class Globals {

    public static final String URL_IMAGES = "https://image.tmdb.org/t/p/w640";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "5f9b575bdd7b895c79917da012169744";

    public static final String URL_SEARCH = "/search/movie";

    public static final String PAGE = "1";
    public static final String LANGUAGE = "en-US";

    public static final class Category {
        public static final int POPULAR = 0;
        public static final int TOP_RATED = 1;
        public static final int UPCOMING = 2;

        public static final String [] nameCategory  = {"popular","top_rated","upcoming"};
    }

}
