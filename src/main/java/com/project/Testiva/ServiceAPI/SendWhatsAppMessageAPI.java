package com.project.Testiva.ServiceAPI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.project.Testiva.Model.StudentInfo;
import com.project.Testiva.Model.TestInfo;

@Service
public class SendWhatsAppMessageAPI {

	private final String INSTANCE_ID = "instance128849";
	private final String TOKEN = "t0dtwn8ud0lm4cif";

	private final String URL = "https://api.ultramsg.com/" + INSTANCE_ID + "/messages/chat";

	public String sendTestReminderMessage(StudentInfo studentInfo, TestInfo testInfo) {

		// String Message
		String message = "📢 *Test Reminder*\n\n" +

				"Dear " + studentInfo.getName() + ",\n\n" +

				"📝 Your Test *\"" + testInfo.getTestName() + "\"*\n (Test ID: *" + testInfo.getTestId() + "*)\n" +

				"For Course: *" + testInfo.getCourse() + "*,\nBranch: *" + testInfo.getBranch() + "*, \nYear: *"
				+ testInfo.getYear() + "*\n\n" +

				"is scheduled to begin in *15 minutes*.\n\n" +

				"🕒 Start Time: *" + testInfo.getStartTime().toLocalTime() + "*,\n" +

				"📅 Date: *" + testInfo.getStartTime().toLocalDate() + "*\n" +

				"❓ Total Questions: *" + testInfo.getNumberOfQuestion() + "*\n\n" +

				"⏲️ Test Active Time: *" + testInfo.getTestDuration() + "*\n\n" +

				"*Important 🔔*\nLogin Through your creadentials and please give your test within the Active time by using *TestID : "
				+ testInfo.getTestId() + "*\\n\n" +

				"*Login Creadentials :*" +

				"User Id : " + studentInfo.getEmail() +

				"\nPassword : " + studentInfo.getPassword() +

				"\n\n✅ Please be ready and ensure a stable internet connection.\n" +

				"Good luck! 🍀\n\n" +

				"-- Team Testiva 🧑‍💻⚙️";

		// mess
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

		map.add("token", TOKEN);
		map.add("to", studentInfo.getContactno());
		map.add("body", message);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, request, String.class);

		System.err.println("Message Sent to " + studentInfo.getContactno());

		System.err.println(responseEntity.getBody());
		return responseEntity.getBody();

	}

	// second method
	public String sendTestScheduledMessage(StudentInfo studentInfo, TestInfo testInfo) {

		String message = "📢 *Test Notification – Testiva Portal*\n\n"
				+ "Hello *" + studentInfo.getName() + "*, 👋\n\n"
				+ "🎓 Course: *" + testInfo.getCourse() + "*\n"
				+ "🏫 Branch: *" + testInfo.getBranch() + "*\n\n"
				+ "📝 Your upcoming test *\"" + testInfo.getTestName() + "\"* (ID: *" + testInfo.getTestId() + "*)\n"
				//+ "is *scheduled to begin in 15 minutes*.\n\n"
				+ "📅 *Date:* " + testInfo.getStartTime().toLocalDate() + "\n"
				+ "🕒 *Start Time:* " + testInfo.getStartTime().toLocalTime() + "\n\n"
				+ "🛠️ Please login on time using your credentials and Test ID.\n"
				+ "🔗 *Login Portal:* http://10.0.0.125:8484/StudentLogin\n\n"
				+ "🎯 Stay focused and give it your best!\n"
				+ "🍀 *Good luck,*\n-- Team Testiva 🧑‍💻⚙️";

		// mess
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

		map.add("token", TOKEN);
		map.add("to", studentInfo.getContactno());
		map.add("body", message);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, request, String.class);

		System.err.println("Message Sent to " + studentInfo.getContactno());

		System.err.println(responseEntity.getBody());
		return responseEntity.getBody();

	}
}
