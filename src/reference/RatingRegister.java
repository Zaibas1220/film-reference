package reference;

import reference.domain.Film;
import reference.domain.Person;
import reference.domain.Rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingRegister {
    private final Map<Film, List<Rating>> filmRatings;
    private final Map<Person, Map<Film, Rating>> personRatings;

    public RatingRegister() {
        filmRatings = new HashMap<>();
        personRatings = new HashMap<>();
    }

    public void addRating(Film film, Rating rating) {
        if (filmRatings.get(film) == null) {
            filmRatings.put(film, new ArrayList<>());
        }
        filmRatings.get(film).add(rating);
    }

    public List<Rating> getRatings(Film film) {
        return filmRatings.get(film);
    }

    public Map<Film, List<Rating>> filmRatings() {
        return filmRatings;
    }

    public void addRating(Person person, Film film, Rating rating) {
        if (personRatings.get(person) == null) {
            personRatings.put(person, new HashMap<>());
        }
        if (!personRatings.get(person).containsKey(film)) {
            personRatings.get(person).put(film, rating);
            addRating(film, rating);
        }
    }

    public Map<Film, Rating> getPersonalRatings(Person person) {
        return personRatings.get(person);
    }

    public List<Person> reviewers() {
        return new ArrayList<>(personRatings.keySet());
    }
}
