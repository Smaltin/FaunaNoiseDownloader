import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FaunaNoiseDownloader {
    public static void main(String[] args) throws IOException {
        String location = "https://faunaraara.com/sounds/ara-%s.mp3";
        Files.createDirectories(Paths.get("export"));
        for (int i = 1; i <= 511; i++) {
            String downl = String.format(location, String.format("%02d", i));
            downloadFile(new URL(downl), "export/ara-" + String.format("%02d", i) + ".mp3");
            System.out.println("Downloaded #" + i + " to " + "export/ara-" + String.format("%02d", i) + ".mp3");
        }
        System.out.println("Finished downloading all 511 files");
    }

    public static void downloadFile(URL url, String fileName) throws IOException {
        try (InputStream in = url.openStream(); BufferedInputStream bis = new BufferedInputStream(in); FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] data = new byte[1024];
            int count;
            while ((count = bis.read(data, 0, 1024)) != -1) {
                fos.write(data, 0, count);
            }
        }
    }
}