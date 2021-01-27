package reference;

import reference.comparetor.FilmComparator;
import reference.comparetor.PersonFilmComparator;
import reference.domain.Film;
import reference.domain.Person;
import reference.domain.Rating;

import java.util.*;
import java.util.function.BiFunction;

public class Reference {
    RatingRegister ratingRegister;

    public Reference(RatingRegister ratingRegister) {
        this.ratingRegister = ratingRegister;
    }

    public Film recommendFilm(Person person) {
        if (ratingRegister.getPersonalRatings(person) == null) {
            List<Film> filmList = new ArrayList<>(ratingRegister.filmRatings().keySet());

            filmList.sort(new FilmComparator(ratingRegister.filmRatings()));

            return filmList.get(0);
        } else {
            Person similarPerson = findSimilarPerson(person);
            Map<Film, Rating> personRatings = ratingRegister.getPersonalRatings(person);
            Map<Film, Rating> similarPersonRatings = ratingRegister.getPersonalRatings(similarPerson);

            List<Film> similarPersonFilms = getSimilarPersonFilms(personRatings, similarPersonRatings);

            if (similarPersonFilms.isEmpty()) return null;

            similarPersonFilms.sort(new PersonFilmComparator(similarPersonRatings));

            return similarPersonFilms.get(0);
        }
    }

    private Person findSimilarPerson(Person person) {
        Map<Film, Rating> personRatings = ratingRegister.getPersonalRatings(person);

        Person similarPerson = null;
        int maxSimilarityIndex = Integer.MIN_VALUE;

        for (Person reviewer : ratingRegister.reviewers()) {
            if (reviewer == person) continue;

            Map<Film, Rating> reviewerRatings = ratingRegister.getPersonalRatings(reviewer);
            int similarityIndex = 0;

            for (Film film : personRatings.keySet()) {
                if (!reviewerRatings.containsKey(film)) continue;

                Rating personRating = personRatings.get(film);
                Rating reviewerRating = reviewerRatings.get(film);

                similarityIndex += personRating.getValue()*reviewerRating.getValue();
            }

            if (similarityIndex > maxSimilarityIndex) {
                maxSimilarityIndex = similarityIndex;
                similarPerson = reviewer;
            }
        }
        return similarPerson;
    }

    private List<Film> getSimilarPersonFilms(Map<Film, Rating> personRatings, Map<Film, Rating> similarPersonRatings) {
        List<Film> similarPersonFilms = new ArrayList<>(similarPersonRatings.keySet());
        similarPersonFilms.removeAll(personRatings.keySet());

        List<Film> removableFilms = new ArrayList<>();

        for (Film film : similarPersonFilms) {
            Rating rating = similarPersonRatings.get(film);
            if (rating.getValue() <= 0)
                removableFilms.add(film);
        }

        similarPersonFilms.removeAll(removableFilms);

        return similarPersonFilms;
    }
}