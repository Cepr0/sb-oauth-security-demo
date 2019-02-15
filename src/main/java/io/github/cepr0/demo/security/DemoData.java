package io.github.cepr0.demo.security;

import io.github.cepr0.demo.security.domain.user.UserService;
import io.github.cepr0.demo.security.model.User;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static io.github.cepr0.demo.security.model.User.Role.ADMIN;

@Component
public class DemoData {

	private final UserService userService;
	private final PasswordEncoder encoder;

	public DemoData(final UserService userService, final PasswordEncoder encoder) {
		this.userService = userService;
		this.encoder = encoder;
	}

	@EventListener
	public void onReady(ApplicationReadyEvent event) {
		User admin = new User();
		admin.setName("admin");
		admin.setEmail("admin@mail.com");
		admin.setPassword(encoder.encode("123456"));
		admin.setRole(ADMIN);
		userService.create(admin);
	}
}
