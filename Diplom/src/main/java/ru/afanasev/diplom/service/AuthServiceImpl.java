package ru.afanasev.diplom.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.github.cage.Cage;
import com.github.cage.GCage;

import net.bytebuddy.utility.RandomString;
import ru.afanasev.diplom.object.User;
import ru.afanasev.diplom.object.dto.authDtos.LoginDtoRequest;
import ru.afanasev.diplom.object.repository.CaptchaRepository;
import ru.afanasev.diplom.object.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	private final CaptchaRepository captchaRepository;
	private final UserRepository userRepository;
	private static final String FIRSTNAME = "captcha/";
	private static final String LASTNAME = ".img";
	private static final String PREFIX = "data:image/png;base64, ";

	public AuthServiceImpl(CaptchaRepository captchaRepository, UserRepository userRepository) {
		this.captchaRepository = captchaRepository;
		this.userRepository = userRepository;
	}

	@Override
	public String getCaptcha(String secretv2) throws Exception {

		Cage cage = new GCage();
		String name = getImageName();
		System.out.println(name);
		String code;
		OutputStream os = new FileOutputStream(name, false);
		try {
			code = cage.getTokenGenerator().next();
			cage.draw(code, os);
		} finally {
			os.close();
		}
		File file = new File(name);
		captchaRepository.insertCaptcha(code, LocalDateTime.now(), secretv2);
		byte[] fileContent = FileUtils.readFileToByteArray(file);
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		StringBuilder base64 = new StringBuilder();
		base64.append(PREFIX);
		base64.append(encodedString);
		file.delete();

		return base64.toString();

	}

	@Override
	public User postLogin(LoginDtoRequest request) {
		User user = userRepository.findByEmail(request.getE_mail()).get();
		return user;
	}

	private String getImageName() {
		StringBuilder nameString = new StringBuilder();
		nameString.append(FIRSTNAME);
		nameString.append(new RandomString(10).nextString());
		nameString.append(LASTNAME);

		return nameString.toString();
	}

}
