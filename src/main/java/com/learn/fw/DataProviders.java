package com.learn.fw;

import com.learn.models.ChangePassword;
import com.learn.models.Course;
import com.learn.models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    //UserLogin

    @DataProvider
    public Iterator<Object[]> getLoginInvalidData() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/LoginNegative.csv")));
        String line = reader.readLine();

        while (line != null){
            String[] split = line.split(",");
            list.add(new Object[]{new User().setEmail(split[0]).setPassword(split[1])});
            line = reader.readLine();
        }

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> getLoginEmptyData() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/LoginNegativeEmpty.csv")));
        String line = reader.readLine();

        while (line != null) {
            String[] split = line.split(",");
            if (split.length == 2) {
                if (split[0].isEmpty()) {
                    list.add(new Object[]{new User().setEmail("").setPassword(split[1])});
                }
                else if (split[1].isEmpty()) {
                    list.add(new Object[]{new User()
                            .setEmail(split[0])
                            .setPassword("")});
                }
            } else {
                System.out.println("Wrong line: " + line);
            }
            line = reader.readLine();
        }
        return list.iterator();
    }

    ///UserReg
    @DataProvider
    public Iterator<Object[]> getRegisterPositiveData() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/RegWithPositiveData.csv")));
        String line = reader.readLine();

        while (line != null){
            String[] split = line.split(",");
            list.add(new Object[]{new User()
                    .setNickname(split[0])
                    .setEmail(split[1])
                    .setPassword(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> getRegisterInvalidData() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/RegWithInvalidData.csv")));
        String line = reader.readLine();

        while (line != null){
            String[] split = line.split(",");
            User user = new User()
                    .setNickname(split[0])
                    .setEmail(split[1])
                    .setPassword(split[2])
                    .setError(split[3]);
            list.add(new Object[]{user});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> getRegisterEmptyData() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/RegWithIEmptyData.csv")));
        String line = reader.readLine();

        while (line != null) {
            String[] split = line.split(",");
            if (split.length == 3) {
                if (split[0].isEmpty()) {
                    list.add(new Object[]{new User()
                            .setNickname("")
                            .setEmail(split[1])
                            .setPassword(split[2])});
                }
                else if (split[1].isEmpty()) {
                    list.add(new Object[]{new User()
                            .setNickname(split[0])
                            .setEmail("")
                            .setPassword(split[2])});
                }
                else if (split[2].isEmpty()) {
                    list.add(new Object[]{new User()
                            .setNickname(split[0])
                            .setEmail(split[0])
                            .setPassword("")});
                }
            } else {
                System.out.println("Wrong line: " + line);
            }
            line = reader.readLine();
        }
        return list.iterator();
    }

    ///Course

    @DataProvider
    public Iterator<Object[]> getCourseInvalidData() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/CreateCourseInvalidData.csv")));

        String line = reader.readLine();
        while (line != null) {

            String[] split = line.split(",");
            list.add(new Object[]{new Course()
                    .setPhotoPath(split[0])
                    .setDescription((split[1]))
                    .setPrice(split[2])
                    .setTitle(split[3])});
            line = reader.readLine();
        }

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> getCreateCourseEmptyData() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/CreateCourseEmptyData.csv")));
        String line = reader.readLine();

        while (line != null) {
            String[] split = line.split(",");
            if (split.length == 4) {
                if (split[0].isEmpty()) {
                    list.add(new Object[]{new Course()
                            .setPhotoPath(split[0])
                            .setDescription(split[1])
                            .setPrice(split[2])
                            .setTitle(split[3])});
                }
                else if (split[1].isEmpty()) {
                    list.add(new Object[]{new Course()
                            .setPhotoPath(split[0])
                            .setDescription("")
                            .setPrice(split[2])
                            .setTitle(split[3])});
                }
                else if (split[2].isEmpty()) {
                    list.add(new Object[]{new Course()
                            .setPhotoPath(split[0])
                            .setDescription(split[1])
                            .setPrice("")
                            .setTitle(split[3])});
                }
                else if (split[3].isEmpty()) {
                    list.add(new Object[]{new Course()
                            .setPhotoPath(split[0])
                            .setDescription(split[1])
                            .setPrice(split[2])
                            .setTitle("")});
                }
            } else {
                System.out.println("Wrong line: " + line);
            }
            line = reader.readLine();
        }
        return list.iterator();
    }

    //ChangePass

    @DataProvider
    public Iterator<Object[]> getPasswordInvalidData() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/ChangePassWithInvalidData.csv")));

        String line = reader.readLine();
        while (line != null) {

            String[] split = line.split(",");
            list.add(new Object[]{new ChangePassword()
                    .setOldPassword(split[0])
                    .setNewPassword((split[1]))
                    .setConfirmPassword(split[2])});
            line = reader.readLine();
        }

        return list.iterator();
    }

}

