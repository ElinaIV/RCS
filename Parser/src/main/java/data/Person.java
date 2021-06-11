package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Person {
    private String name;
    private String email;
    private List<String> phones;

    @Autowired
    public Person(String name, String email, List<String> phones) {
        this.name = name;
        this.email = email;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getPhones() {
        return phones;
    }
}
