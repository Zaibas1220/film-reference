package reference.comparetor;

import reference.domain.Film;
import reference.domain.Rating;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FilmComparator implements Comparator<Film> {
    private Map<Film, List<Rating>> ratings;

    public FilmComparator(Map<Film, List<Rating>> ratings) {
        this.ratings = ratings;
    }

    @Override
    public int compare(Film o1, Film o2) {
        int n1 = 0;
        int n2 = 0;

        List<Rating> ratings1 = ratings.get(o1);
        List<Rating> ratings2 = ratings.get(o2);

        if (ratings1 != null) {
            for (Rating i : ratings1) {
                n1 += i.getValue();
            }
        }

        if (ratings2 != null) {
            for (Rating i : ratings2) {
                n2 += i.getValue();
            }
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
