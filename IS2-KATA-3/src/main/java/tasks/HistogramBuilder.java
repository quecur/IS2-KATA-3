package tasks;

import model.Movie;
import modelView.Histogram;

import java.util.List;
import java.util.function.Function;

public class HistogramBuilder {
    public <T> Histogram<T> build(List<Movie> movies, Function<Movie, T> binarize) {
        Histogram<T> histogram = new Histogram<>();
        for (Movie movie : movies) {
            histogram.put(binarize.apply(movie));
        }
        return histogram;
    }
}
