package ai.dnai.cs.timer;

import ai.dnai.cs.client.AINetworkClient;
import ai.dnai.cs.client.Client;
import ai.dnai.cs.client.ImageProperties;
import ai.dnai.cs.client.MutationClient;
import ai.dnai.cs.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class CheckNewFiles {

    private static final Logger log = LoggerFactory.getLogger(CheckNewFiles.class);

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private AINetworkClient aiNetworkClient;

    @Autowired
    private MutationClient mutationClient;

    @Scheduled(fixedDelayString = "${cs.config.delay}")
    public void run() {
        String baseDir = appConfig.getBaseDir();
        String processDir = appConfig.getProcessedDir();

        Path basePath = FileSystems.getDefault().getPath(baseDir);

        if(!basePath.toFile().isDirectory()) {
            throw new RuntimeException("Given base directory " + baseDir + " is not found or is not dir");
        }

        try {
            Stream<Path> pathStream = Files.walk(basePath, FileVisitOption.FOLLOW_LINKS);

            Stream<Path> imagePaths = pathStream.filter(path -> {
                String fileName = path.getFileName().toString();
                if(fileName.endsWith("jpg") || fileName.endsWith("jpeg") || fileName.endsWith("tif") ||
                        fileName.endsWith("tiff") || fileName.endsWith("bpm")) {
                    return true;
                }
                return false;
            });

            imagePaths.forEach(impath -> {
                File f = impath.toFile();
                try {
                    BufferedImage bimg = ImageIO.read(f);
                    int width          = bimg.getWidth();
                    int height         = bimg.getHeight();
                    System.out.println(impath.toString() + "    " + f.lastModified() + "     " + width + " X " + height);

                    Path processPath = FileSystems.getDefault().getPath(processDir + impath.toString().substring(baseDir.length()));

                    String features = aiNetworkClient.idnetifyImageFatures(impath);

                    this.mutationClient.createImage(new ImageProperties(impath.getFileName().toString(),
                            f.lastModified(), width, height,  "http://"+ Client.getLocalHostLANAddress().getHostAddress() + ":" + appConfig.getPort() + "/content/image/stream" + impath.toString().substring(baseDir.length())), features);

                    Files.createDirectories(processPath.getParent());
                    Files.move(impath, processPath);

                } catch(Exception e) {
                    throw new RuntimeException("Can not read image " + impath, e);
                }
            });


        } catch (Exception ex) {
            throw new RuntimeException("Exception during travers of " + baseDir, ex);
        }

    }
}