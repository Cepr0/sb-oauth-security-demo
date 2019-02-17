package com.github.cepr0.demo.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Profile("!basic")
@EnableAuthorizationServer
@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;

	public AuthServerConfig(
			final AuthenticationManager authenticationManager,
			@Qualifier("userServiceImpl") final UserDetailsService userDetailsService
	) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService)
				.tokenStore(new InMemoryTokenStore())
				.reuseRefreshTokens(false);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clientDetailsService) throws Exception {
		clientDetailsService.inMemory()
				.withClient("client")
				.secret("{noop}")
				.scopes("*")
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(60 * 2) // 2 min
				.refreshTokenValiditySeconds(60 * 60); // 60 min
	}
}
