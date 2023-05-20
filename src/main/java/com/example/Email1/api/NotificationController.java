package com.example.Email1.api;

import com.example.Email1.DTO.NotificationDTO;
import com.example.Email1.email.MailService;
import com.example.Email1.entities.Student;
import com.example.Email1.services.StudentService;
import com.sendgrid.helpers.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	MailService mailService;
	@Autowired
	StudentService studentService;
	
	@PostMapping
	public ResponseEntity<String> sendEmail(@RequestParam int id, @RequestBody NotificationDTO notificationDTO) {
		try {
			Optional<Student> student = studentService.getStudentById(id);
			if (student.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				mailService.sendEmail(
						studentService.getStudentById(notificationDTO.getContactId()).get().getEmail(),
						notificationDTO.getTitle(),
						notificationDTO.getText()
				);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
