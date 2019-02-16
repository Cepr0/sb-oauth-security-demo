package io.github.cepr0.demo.security.config;

import io.github.cepr0.demo.security.domain.user.dto.AuthUser;
import lombok.SneakyThrows;
import org.springframework.data.spel.spi.EvaluationContextExtension;
import org.springframework.data.spel.spi.Function;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SecurityEvaluationContextExtension implements EvaluationContextExtension {

	private final Map<String, Function> functions = new ConcurrentHashMap<>();

	@SneakyThrows
	public SecurityEvaluationContextExtension() {
		functions.put("userId", new Function(SecurityEvaluationContextExtension.class.getDeclaredMethod("userId")));
	}

	@Override
	public String getExtensionId() {
		return "security";
	}

	@Override
	public Map<String, Function> getFunctions() {
		return functions;
	}

	@Override
	public Object getRootObject() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return new SecurityExpressionRoot(authentication) {};
	}

	@SuppressWarnings("WeakerAccess")
	public static Integer userId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return ((AuthUser) authentication.getPrincipal()).getUserId();
	}
}
