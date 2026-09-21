import app.RemoteMovieLoader;
import app.TsvMovieParser;
import model.Movie;

import java.net.MalformedURLException;
import java.util.List;


void main() throws MalformedURLException {
    String url = "https://datasets.imdbws.com/title.basics.tsv.gz";
    List<Movie> movies = new RemoteMovieLoader(url, TsvMovieParser::parseMovie).loadAll();
    for (Movie movie : movies) {
        System.out.println(movie);
    }
}

