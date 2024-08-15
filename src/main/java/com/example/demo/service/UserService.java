package com.example.demo.service;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Value("${custom.localPath}")
    private String localPath;

    @Value("${custom.accessToken}")
    private String accessToken;

    @Value("${custom.gitLabUrl}")
    private String gitLabUrl;
    static List<String> paths = new ArrayList<>();
    public User Sel(User user) {
        return userMapper.Sel(user);
    }

    public String Add(@NotNull User user) {

        this.scc(user);
        int a = userMapper.Add(user);
        if (a == 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    public void scc(User user)
    {
        String groupId = user.getUsername();
        OkHttpClient client = new OkHttpClient();
        String url = gitLabUrl + "/api/v4/groups/" + groupId + "/projects?simple=true";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("PRIVATE-TOKEN", accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                List<String> httpUrls = new ArrayList<>();
                JsonNode rootNode = objectMapper.readTree(responseBody);
                //获得group中各个项目的url
                for (JsonNode node : rootNode) {
                    String httpUrl = node.get("http_url_to_repo").asText();
                    httpUrl=httpUrl.replace("gitlab.example.com", "localhost");
                    httpUrls.add(httpUrl);
                }
                for (String url1 : httpUrls) {
                    cloneOrUpdateRepository(url1,groupId);
                }
                user.setAddress(runScc(localPath+groupId));

            } else {
                System.out.println("Failed to retrieve projects. Response code: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cloneOrUpdateRepository(String gitlabUrl,String groupId) throws IOException, InterruptedException {
        File gitDir = new File(localPath);
        String path=gitlabUrl;
        int lastSlashIndex = path.lastIndexOf('/');

        // 获取最后一个 '.' 的位置
        int lastDotIndex = path.lastIndexOf('.');
        String fileName=path.substring(lastSlashIndex + 1, lastDotIndex);


        String localdi=localPath+groupId+"/"+fileName;
        File singleDir1 = new File(localdi);

        if (singleDir1.exists() && new File(singleDir1, ".git").exists()) {
            System.out.println("Updating existing repository to"+singleDir1);
            runCommand("git -C " + localdi + " pull");
        } else {
            System.out.println("Cloning new repository...");
            runCommand("git clone --depth 1 " + gitlabUrl + " " + localdi);
        }

    }

    private static String runScc(String directory) throws IOException, InterruptedException {
        System.out.println("Running scc...");
        String sccline="0";
        String output = runCommand("scc " + directory);
        System.out.println("SCC output:");
        System.out.println(output);
        String[] lines = output.split("\n");

        for (String line : lines) {
            if (line.contains("Total")) {  // 查找含有 "Total" 的行
                String[] values = line.trim().split("\\s+");  // 按空白字符分割
                if (values.length > 2) {
                    System.out.println("The second data after 'Total' is: " + values[2]);  // 输出第二个数据
                    sccline=values[2];
                } else {
                    System.out.println("Not enough data after 'Total'");
                }
                break;
            }
        }


        Files.write(Paths.get(directory,"scc_result.txt"), output.getBytes());
        System.out.println("Results saved to scc_result.txt");
        return sccline;
    }

    private static String runCommand(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        StringBuilder output = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        int exitVal = process.waitFor();
        if (exitVal == 0) {
            return output.toString();
        } else {
            throw new RuntimeException("Command execution failed: " + command);
        }
    }
//
//    public String Update(User user) {
//        int a = userMapper.Update(user);
//        if (a == 1) {
//            return "修改成功";
//        } else {
//            return "修改失败";
//        }
//    }
//
//    public String Delete(User user) {
//        int a = userMapper.Delete(user);
//        if (a == 1) {
//            return "删除成功";
//        } else {
//            return "删除失败";
//        }
//    }
//
//    public String addgroup(User user) {
//        int a = userMapper.Add(user);
//
//        if (a == 1) {
//            return "添加成功";
//        } else {
//            return "添加失败";
//        }
//    }

}