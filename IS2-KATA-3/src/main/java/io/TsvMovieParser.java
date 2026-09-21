package io;

import model.Movie;

public class TsvMovieParser implements MovieParser {
    @Override
    public Movie parseMovie(String str) {
        return parseMovie(str.split("\t"));
    }

    private Movie parseMovie(String[] split) {
        return new Movie(split[2], toInt(split[5]), toInt(split[7]));
    }

    private int toInt(String s) {
        if(isNullValue(s)) return 0;
        return Integer.parseInt(s);
    }

    private boolean isNullValue(String s) {
        return s.equals("\\N");
    }
}
