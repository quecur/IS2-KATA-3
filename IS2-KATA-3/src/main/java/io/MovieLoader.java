package io;

import model.Movie;

import java.util.List;

public interface MovieLoader {
    List<Movie> loadAll();
}
