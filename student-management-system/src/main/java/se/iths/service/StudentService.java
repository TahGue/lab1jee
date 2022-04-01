package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student) {
        entityManager.persist(student);
    }

    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT i from Student i", Student.class).getResultList();
    }

    public void deleteStudent(Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        entityManager.remove(foundStudent);
    }

    public Student updatePhoneNumber(Long id, String phoneNumber) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setPhoneNumber(phoneNumber);
        return foundStudent;
    }
    public List<Student> getByLastName(String lastName) {
        String query = "SELECT i FROM Student i WHERE i.lastName = :lastName";
        return entityManager.createQuery(query, Student.class).setParameter("lastName", lastName).getResultList();
    }



}
