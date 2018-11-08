package ai.dnai.cs.service;

import ai.dnai.cs.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
 * demonstrates how service-specific properties can be injected
 */
@ConfigurationProperties(prefix = "cs.service", ignoreUnknownFields = false)
@Component
public class ServiceProperties {

	@Autowired
	private AppConfig appConfig;

    @NotNull // you can also create configurationPropertiesValidator
	private String name = "Empty";


	private Map<String, String> systemProperties = new HashMap<>();
	private Map<String, String> customProperties = new HashMap<>();
	private Map<String, String> envProperties = new HashMap<>();

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Map<String, String> getSystemProperties() {
		if(systemProperties.isEmpty()) {
			this.systemProperties.putAll(propsToMap(System.getProperties()));
		}
		return this.systemProperties;
	}

	public Map<String, String> getEnvProperties() {
		if(envProperties.isEmpty()) {
			this.envProperties.putAll(System.getenv());
		}
		return this.envProperties;
	}


	private static Map<String, String> propsToMap(Properties props) {
		Map<String, String> result = new HashMap<>();
		Enumeration enProps = props.propertyNames();
		while(enProps.hasMoreElements()) {
			String key = (String) enProps.nextElement();
			result.put(key, props.getProperty(key));
		}
		return result;
	}

	public Map<String, Object> getOverallStatus() {
		Map<String, Object> status = new HashMap<>();
		status.put("appProperties", this.appConfig);
		status.put("systemProperties", this.getSystemProperties());
		status.put("envProperties", this.getEnvProperties());
		return status;
	}

}
