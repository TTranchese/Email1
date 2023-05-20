package com.example.Email1.services;

import com.example.Email1.entities.Student;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
	private static final List<Student> studentList = List.of(
			new Student(1, "Tommaso", "Tranchese", "tommaso.tranchese.96@gmail.com"),
			new Student(2, "Alessandro", "Mazzella", ""),
			new Student(3, "Luciano", "Rea", ""),
			new Student(4, "Daniele", "Rescigno", "")
	);
	
	public Optional<Student> getStudentById(int id) {
		return studentList.stream().filter(student -> student.getId() == id).findAny();
	}
}
