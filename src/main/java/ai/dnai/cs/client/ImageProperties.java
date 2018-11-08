package ai.dnai.cs.client;

public class ImageProperties {

    private String imageName;
    private long imageCreatedAt;
    private int imageWidth;
    private int imageHeight;
    private String imagePath;

    public ImageProperties(String imageName, long imageCreatedAt, int imageWidth, int imageHeight, String imagePath) {
        this.imageName = imageName;
        this.imageCreatedAt = imageCreatedAt;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.imagePath = imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public long getImageCreatedAt() {
        return imageCreatedAt;
    }

    public void setImageCreatedAt(long imageCreatedAt) {
        this.imageCreatedAt = imageCreatedAt;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
