package ca.jordanfulawka.cruddemo.dao;

import ca.jordanfulawka.cruddemo.entity.Instructor;
import ca.jordanfulawka.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
}
