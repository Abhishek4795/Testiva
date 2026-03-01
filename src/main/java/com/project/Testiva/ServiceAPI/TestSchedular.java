package com.project.Testiva.ServiceAPI;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.Testiva.Model.StudentInfo;
import com.project.Testiva.Model.StudentInfo.UserRole;
import com.project.Testiva.Model.TestInfo;
import com.project.Testiva.Model.TestInfo.TestStatus;
import com.project.Testiva.Repository.StudentInfoRepo;
import com.project.Testiva.Repository.TestInfoRepo;

@Component
public class TestSchedular {
	@Autowired
	private TestInfoRepo testInfoRepo;

	@Autowired
	private StudentInfoRepo studentInfoRepo;

	@Autowired
	private SendWhatsAppMessageAPI sendWhatsAppMessageAPI;

	@Scheduled(fixedRate = 30000)
	public void manageScheduleTest() {
		ZonedDateTime zoneTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDateTime currentTime = zoneTime.toLocalDateTime();
		List<TestInfo> testList = testInfoRepo.findAll();

		for (TestInfo test : testList) {

			LocalDateTime startTime = test.getStartTime();
			LocalDateTime endTime = test.endtime();

			// 1. When test status scheduled
			if (test.getStatus().equals(TestStatus.Scheduled) && currentTime.isAfter(startTime.minusMinutes(15))
					&& currentTime.isBefore(startTime)) {
				// send reminder
				List<StudentInfo> stdList = studentInfoRepo.findAllByRoleAndCourseAndBranchAndYear(UserRole.STUDENT,
						test.getCourse(), test.getBranch(), test.getYear());

				for (StudentInfo student : stdList) {

					sendWhatsAppMessageAPI.sendTestReminderMessage(student, test);
				}

				test.setStatus(TestStatus.Reminder_Sent);
				testInfoRepo.save(test);

			}

			// 2. when Test Reminder Sent
			if (test.getStatus().equals(TestStatus.Reminder_Sent) && currentTime.isAfter(startTime)) {
				test.setStatus(TestStatus.Active);
				testInfoRepo.save(test);

			}

			// 3. when test is active and going to over(End Test)

			if (test.getStatus().equals(TestStatus.Active) && currentTime.isAfter(endTime)) {
				test.setStatus(TestStatus.Test_Over);
				testInfoRepo.save(test);

			}

		}
	}
}
