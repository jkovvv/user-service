package user;

import user.server.UserServer;

public class Main {

    public static void main(String[] args) throws Exception {

        UserServer server = new UserServer();

        server.start();

    }
}