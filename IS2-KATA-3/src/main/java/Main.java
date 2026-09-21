import io.MovieLoader;
import io.RemoteMovieLoader;
import model.Movie;

import java.net.MalformedURLException;
import java.util.List;


void main() throws MalformedURLException {
    MovieLoader loader = new RemoteMovieLoader("https://datasets.imdbws.com/title.basics.tsv.gz");
    List<Movie> movies = loader.loadAll();
    for (Movie movie : movies) {
        System.out.println(movie);
    }
}

