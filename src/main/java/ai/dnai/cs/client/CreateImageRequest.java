package ai.dnai.cs.client;

public class CreateImageRequest {

    private String type = "FeatureCollection";
    private ImageProperties properties;
    private String[] features = {};

    public CreateImageRequest(ImageProperties properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public ImageProperties getProperties() {
        return properties;
    }

    public void setProperties(ImageProperties properties) {
        this.properties = properties;
    }

    public String[] getFeatures() {
        return features;
    }
}
