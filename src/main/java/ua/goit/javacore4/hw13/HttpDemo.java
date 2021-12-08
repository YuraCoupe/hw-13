package ua.goit.javacore4.hw13;


import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HttpDemo {
    private static  final String CREATE_USER_URL = "https://pingponggoit.herokuapp.com/createUser";
    private static final String GET_USER_BY_ID_URL = "https://pingponggoit.herokuapp.com/getUserById";
    private static final String GET_USERS_URL = "https://pingponggoit.herokuapp.com/getUsers";
    private static final String REMOVE_USER_URL = "https://pingponggoit.herokuapp.com/removeUser";
    private static final String OVERWRITE_URL = "https://pingponggoit.herokuapp.com/overwrite";

    public static void main(String[] args) throws IOException, InterruptedException {
        //TASK 1
        //User user = createDefaultUser();
        //User createdUser = HttpUtil.sendPost(URI.create(CREATE_USER_URL), user);

        //System.out.println("task 1 user " + createdUser);

        //TASK 2
        //User task2User = HttpUtil.sendGet(URI.create(String.format("%s?id=%d", GET_USER_BY_ID_URL, createdUser.getId())));
        //System.out.println("task 2 user " + task2User);

//        //TASK 3
//        User user3 = createDefaultUser();
//        User task3User = HttpUtil.sendPost(URI.create(CREATE_USER_URL), user3);
//        System.out.println("task 3 user " + task3User);
//        List<User> Users = HttpUtil.sendGetToGetAllUsers(URI.create(GET_USERS_URL));
//        System.out.println("Users before delete " + Users);
//        HttpUtil.sendDelete(URI.create(REMOVE_USER_URL), task3User);
//        List<User> UsersAfterDelete = HttpUtil.sendGetToGetAllUsers(URI.create(GET_USERS_URL));
//        System.out.println("Users after delete " + UsersAfterDelete);

//        TASK 4
        User user4 = createDefaultUser();
        User task4User = HttpUtil.sendPost(URI.create(CREATE_USER_URL), user4);
        System.out.println(task4User);
        List<User> Users = HttpUtil.sendGetToGetAllUsers(URI.create(GET_USERS_URL));
        System.out.println("Users before change " + Users);
        task4User.setSalary(500100);
        System.out.println(task4User);
        HttpUtil.overwrite(URI.create(OVERWRITE_URL), task4User.getId(), task4User);
        List<User> UsersAfterChange = HttpUtil.sendGetToGetAllUsers(URI.create(GET_USERS_URL));
        System.out.println("Users before change " + UsersAfterChange);

    }

    private static User createDefaultUser() {
        return new User("male", 7, "Yuriy", 100500, "Kozhukhar");
    }
}
