package weixin;

import com.hlf.util.ZKUtil;
import lombok.Data;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class TokenTest {

    public static void main(String[] args) throws IOException {
        String file = "/cgi-bin/stable_token";
        String file2 = "/cgi-bin/token?grant_type=client_credential&appid=wxca470bcf1c62ef3b&secret=70e4b348a5baf4b45fc93b8768bb1636";
        URL url = new URL("https", "api.weixin.qq.com", 443, file);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        Pamer pamer = new Pamer();
        pamer.setGrant_type("client_credential");
        pamer.setAppid("wxca470bcf1c62ef3b");
        pamer.setSecret("70e4b348a5baf4b45fc93b8768bb1636");
        pamer.setForce_refresh(false);
        String data = ZKUtil.OBJECT_MAPPER.writeValueAsString(pamer);
        try {
            outputStream.write(data.getBytes("UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        InputStream inputStream = httpURLConnection.getInputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Data
    static class Pamer implements Serializable {
        private String grant_type;
        private String appid;
        private String secret;
        private Boolean force_refresh;
    }
}
