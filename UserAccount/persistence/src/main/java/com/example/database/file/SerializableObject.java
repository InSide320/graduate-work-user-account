package com.example.database.file;

import com.example.user.UserPersonalData;
import com.example.user.UserPersonalService;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerializableObject {
    private static final Logger logger = Logger.getGlobal();

    private static final Path projectPath = Paths.get(System.getProperty("user.dir"));
    public static final Path filePath = projectPath.resolve(projectPath + "/src/main/resources/com/example/coursework/file/txt/saveObject.txt");
    public static final File createFile = new File(String.valueOf(filePath));

    public static void setFileValues(List<UserPersonalData> userPersonalDataList) throws IOException {

        creatFileInResources();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(createFile, false))) {
            outputStream.writeObject(userPersonalDataList);
        }
    }

    static UserPersonalService userPersonalService = UserPersonalService.getInstance();

    public static void getFileValues() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(createFile))) {

            userPersonalService = new UserPersonalService((List<UserPersonalData>) inputStream.readObject());

            for (int i = 0; i < userPersonalService.userPersonalDataList().size(); i++) {
                logger.log(Level.INFO, String.valueOf(userPersonalService.userPersonalDataList().get(i)));
            }

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void creatFileInResources() throws IOException {
        if (createFile.createNewFile()) logger.log(Level.INFO, "File created!");
        else logger.log(Level.INFO, "File not created or exists");
    }
}
