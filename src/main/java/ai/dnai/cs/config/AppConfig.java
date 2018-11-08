package ai.dnai.cs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by palos on 8/19/17.
 */
@ConfigurationProperties(prefix = "cs.config", ignoreUnknownFields = false)
@Component
public class AppConfig {

    @Autowired
    private org.springframework.boot.autoconfigure.web.ServerProperties serverProperties;

    private String baseDir;

    private String processedDir;

    private long delay;

    private String annotationServiceURL;

    private String aINetworkURL;

    private int port;

    public String getAINetworkURL() {
        return aINetworkURL;
    }

    public void setAINetworkURL(String aINetworkURL) {
        this.aINetworkURL = aINetworkURL;
    }

    public String getAnnotationServiceURL() {
        return annotationServiceURL;
    }

    public int getPort() {
        return this.serverProperties.getPort();
    }

    public void setAnnotationServiceURL(String annotationServiceURL) {
        this.annotationServiceURL = annotationServiceURL;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getProcessedDir() {
        return processedDir;
    }

    public void setProcessedDir(String processedDir) {
        this.processedDir = processedDir;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

}
