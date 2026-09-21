package io;

import model.Movie;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class RemoteMovieLoader implements MovieLoader {

    private final URL remoteURL;

    public RemoteMovieLoader(String remoteURL) throws MalformedURLException {
        this.remoteURL = URI.create(remoteURL).toURL();
    }

    @Override
    public List<Movie> loadAll() {
        try {
            return loadAllFrom (remoteURL.openConnection());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Movie> loadAllFrom(URLConnection urlConnection) throws IOException {
        try (GZIPInputStream gzip = new GZIPInputStream(new BufferedInputStream((urlConnection.getInputStream())))){
            return loadAllFrom(gzip);
        }
    }

    private List<Movie> loadAllFrom(GZIPInputStream gzip) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gzip))){
            return loadAllFrom(bufferedReader);
        }
    }

    private List<Movie> loadAllFrom(BufferedReader bufferedReader) throws IOException {
        List<Movie> movies = new ArrayList<>();
        MovieParser movieParser = new TsvMovieParser();
        bufferedReader.readLine();
        while (true){
            String line = bufferedReader.readLine();
            if (line == null) break;
            movies.add(movieParser.parseMovie(line));
        }
        return movies;
    }
}
