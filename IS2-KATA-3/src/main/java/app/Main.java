import app.RemoteMovieLoader;
import app.TsvMovieParser;
import model.Movie;
import modelView.Histogram;
import tasks.HistogramBuilder;

import java.net.MalformedURLException;
import java.util.List;


void main() throws MalformedURLException {
    String url = "https://datasets.imdbws.com/title.basics.tsv.gz";
    List<Movie> movies = new RemoteMovieLoader(url, TsvMovieParser::parseMovie).loadAll();
    Histogram<Integer> histogram = new HistogramBuilder().build(movies, Movie::year);
    for(Integer bin : histogram){
        System.out.println(bin + ":" + histogram.count(bin));
    }
    System.out.println("Total: " + histogram.size());
}

