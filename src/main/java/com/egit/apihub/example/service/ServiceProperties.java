package com.egit.apihub.example.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
 * demonstrates how service-specific properties can be injected
 */
@ConfigurationProperties(prefix = "hotel.service", ignoreUnknownFields = false)
@Component
public class ServiceProperties {

    @NotNull // you can also create configurationPropertiesValidator
	private String name = "Empty";

    private Map<String, String> systemProperties = new HashMap<>();
	private Map<String, String> customProperties = new HashMap<>();

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getSystemProperties() {
		if(systemProperties.isEmpty()) {
			this.systemProperties.putAll(System.getenv());
		}
		return this.systemProperties;
	}

	public Map<String, String> getCustomProperties() {
		if(customProperties.isEmpty()) {
			try {
				Properties props = new Properties();
				props.load(new FileReader("/opt/app/etc/props/app.properties"));

				Enumeration enProps = props.propertyNames();
				while(enProps.hasMoreElements()) {
					String key = (String) enProps.nextElement();
					this.customProperties.put(key, props.getProperty(key));
				}

			} catch (Exception e) {
				this.customProperties.put("CUSTOM_PROPERTIS_NOT_AVAILABLE", e.getMessage());
			}
		}

		return this.customProperties;
	}

	public Map<String, Object> getOverallStatus() {
		Map<String, Object> status = new HashMap<>();
		status.put("customProperties", this.getCustomProperties());
		status.put("systemProperties", this.getSystemProperties());
		return status;
	}

}
