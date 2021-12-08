package ua.goit.javacore4.hw13;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class jsonplaceholderDemo {
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) throws IOException, InterruptedException {
        //create new user to add
        JPHUser defaultUser = createDefaultUser();
        //add new user
        JPHUser createdUser = jsonplaceholderUtil.createUser(URI.create(USERS_URL), defaultUser);
        System.out.println("Created user:\n" + createdUser);
        //get user to update some info
        JPHUser usertoUpdate = jsonplaceholderUtil.getUserById(URI.create(USERS_URL), 7);
        //update name
        usertoUpdate.setName("Yurik");
        //update user
        JPHUser updatedUser = jsonplaceholderUtil.updateUser(URI.create(USERS_URL), usertoUpdate);
        System.out.println("Updated user name:\n" + updatedUser.getName());
        System.out.println("Updated user:\n" + updatedUser);
        //delete user
        jsonplaceholderUtil.deleteUser(URI.create(USERS_URL), createdUser.getId());
        //get all users
        List<JPHUser> allUsers = jsonplaceholderUtil.getUsers(URI.create(USERS_URL));
        System.out.println("All users:\n" + allUsers);
        //get user by ID
        JPHUser userById = jsonplaceholderUtil.getUserById(URI.create(USERS_URL), 7);
        System.out.println("User got by ID:\n" + userById);
        //get user by username
        JPHUser userByUsername = jsonplaceholderUtil.getUserbyUsername(URI.create(USERS_URL), "Bret");
        System.out.println("User got by username:\n" + userByUsername);

        //get user posts
        int userId = 5;
        List<Post> userPosts = jsonplaceholderUtil.getUserPosts(URI.create(USERS_URL), userId);
        System.out.println("User posts: \n" + userPosts);
        //get user last post id
        int lastPostId = jsonplaceholderUtil.getUserLastPostId(userPosts);
        System.out.println("User last post id: " + lastPostId);
        //get comments to last user post
        List<Comment> userLastPostComments = jsonplaceholderUtil.getUserLastPostComments(URI.create(POSTS_URL), lastPostId);
        System.out.println(userLastPostComments);
        //write comments to file
        String fileJSON = GSON.toJson(userLastPostComments);
        jsonplaceholderUtil.fileWriter(userId, lastPostId, fileJSON);
    }

    public static JPHUser createDefaultUser() {
        JPHUser user = new JPHUser();
        user.setId(0);
        user.setName("Yuriy");
        user.setUsername("YuraCoupe");
        user.setEmail("yuriy@gmail.com");
        Address address = new Address();
        address.setStreet("Khreschatyk");
        address.setSuite("Apt.100");
        address.setCity("Kyiv");
        address.setZipcode("00001");
        Geo geo = new Geo();
        geo.setLat(77.7777f);
        geo.setLng(55.5555f);
        address.setGeo(geo);
        user.setAddress(address);
        user.setPhone("+380505005050");
        user.setWebsite("hellojava.org");
        Company company = new Company();
        company.setName("CCCompany");
        company.setCatchPhrase("copy paste print");
        company.setBs("no wonder what to write here");
        user.setCompany(company);
        return user;
    }
}
