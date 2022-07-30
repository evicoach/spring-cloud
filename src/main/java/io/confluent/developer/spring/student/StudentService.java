package io.confluent.developer.spring.student;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository repository) {
        this.studentRepository = repository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        studentRepository.save(student);
    }

//    public void deleteStudent(Long id) {
//        Optional<Student> studentOptional = studentRepository.findById(id);
//        if(studentOptional.isEmpty()){
//            throw new IllegalStateException("Student with this id does not exist");
//        }
//        studentRepository.deleteById(id);
//    }

    public void deleteStudent(Long id) {
        boolean exist = studentRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("Student with id " + id + " doest not exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(()
                        -> new IllegalStateException("Student with id " + studentId
                        + " does not exit"));
        if (name != null && name.length() > 0
                && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if(email != null && email.length() > 0
        && !Objects.equals(student.getEmail(), email)){
            student.setEmail(email);
        }
    }
}
