package com.kramkroc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/configured")
@RefreshScope
@EnableAutoConfiguration
public class ConfigurableController {
	
	@Value("${bar:World!}")
	private String bar;
	
    @RequestMapping(method = RequestMethod.GET)
	String getConfigurableResult() {
		final String result = bar; 
		System.out.println(String.format("Found the following response '%s'", result));
		return result;
	}
}
