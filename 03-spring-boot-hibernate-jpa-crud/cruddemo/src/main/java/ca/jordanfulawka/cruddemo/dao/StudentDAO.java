package ca.jordanfulawka.cruddemo.dao;
import ca.jordanfulawka.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save (Student theStudent);
    Student findById(Integer id);
    List<Student> findAll();
    List<Student>  findBYLastName(String theLastName);

    void update(Student theStudent);

    void delete(Integer id);

    int deleteAll();
}
