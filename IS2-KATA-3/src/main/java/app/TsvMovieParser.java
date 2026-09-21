package app;

import model.Movie;

public class TsvMovieParser  {

    public static Movie parseMovie(String str) {
        return parseMovie(str.split("\t"));
    }

    private static Movie parseMovie(String[] split) {
        return new Movie(split[2], toInt(split[5]), toInt(split[7]));
    }

    private static int toInt(String s) {
        if(isNullValue(s)) return 0;
        return Integer.parseInt(s);
    }

    private static boolean isNullValue(String s) {
        return s.equals("\\N");
    }
}
