package reference.comparetor;

import reference.domain.Person;

import java.util.Comparator;
import java.util.Map;

public class PersonComparator implements Comparator<Person> {
    private Map<Person, Integer> peopleIdentities;

    public PersonComparator(Map<Person, Integer> peopleIdentities) {
        this.peopleIdentities = peopleIdentities;
    }

    @Override
    public int compare(Person o1, Person o2) {
        int n1 = peopleIdentities.get(o1);
        int n2 = peopleIdentities.get(o2);
        if (n1 > n2) {
            return -1;
        } else if (n1 < n2) {
            return 1;
        } else {
            return 0;
        }
    }
}
