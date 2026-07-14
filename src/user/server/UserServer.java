package user.server;

import user.model.User;
import user.repository.UserRepository;
import user.util.JsonUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class UserServer {

    private static final int PORT = 9001;

    private final UserRepository userRepository;

    public UserServer() {
        userRepository = new UserRepository();
    }

    public void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println(
                "User Service running on port " + PORT
        );


        while (true) {

            Socket clientSocket = serverSocket.accept();

            System.out.println(
                    "Client connected: "
                            + clientSocket.getInetAddress()
            );


            PrintWriter writer = new PrintWriter(
                    clientSocket.getOutputStream(),
                    true
            );


            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: application/json");
            writer.println();

            List<User> users = userRepository.findAll();

            String json = JsonUtil.usersToJson(users);
            writer.println(json);


            clientSocket.close();
        }
    }
}