package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhoneBook {
    private List<Person> personList;

    @Autowired
    public PhoneBook(List<Person> phoneList) {
        this.personList = phoneList;
    }

    public List<Person> getPhoneList() {
        return personList;
    }
}
