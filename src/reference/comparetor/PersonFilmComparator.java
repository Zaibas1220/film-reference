package reference.comparetor;

import reference.domain.Film;
import reference.domain.Rating;

import java.util.Comparator;
import java.util.Map;

public class PersonFilmComparator implements Comparator<Film> {
    private Map<Film, Rating> personalRatings;

    public PersonFilmComparator(Map<Film, Rating> personalRatings) {
        this.personalRatings = personalRatings;
    }

    @Override
    public int compare(Film o1, Film o2) {
        int n1 = 0;
        int n2 = 0;

        Rating rating1 = personalRatings.get(o1);
        Rating rating2 = personalRatings.get(o2);

        if (rating1 != null) {
            n1 = rating1.getValue();
        }

        if (rating2 != null) {
            n2 = rating2.getValue();
        }

        if (n1 > n2) {
            return -1;
        } else if (n1 < n2) {
            return 1;
        } else {
            return 0;
        }
    }
}
