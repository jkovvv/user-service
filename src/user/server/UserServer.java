package user.server;

import user.model.User;
import user.repository.UserRepository;
import user.util.JsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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


            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()
                    )
            );


            String requestLine = reader.readLine();

            System.out.println(
                    "Request: " + requestLine
            );


            String method = requestLine.split(" ")[0];

            String path = requestLine.split(" ")[1];


            String json;


            if (method.equals("GET") && path.equals("/users")) {

                List<User> users =
                        userRepository.findAll();

                json =
                        JsonUtil.usersToJson(users);


            } else if (method.equals("GET") && path.startsWith("/users/")) {


                int id = Integer.parseInt(
                        path.split("/")[2]
                );


                User user =
                        userRepository.findById(id);


                json =
                        JsonUtil.userToJson(user);



            } else if (method.equals("POST") && path.equals("/users")) {

                String line;
                int contentLength = 0;

                while ((line = reader.readLine()) != null) {

                    if (line.startsWith("Content-Length:")) {

                        contentLength = Integer.parseInt(
                                line.split(":")[1].trim()
                        );
                    }


                    if (line.isEmpty()) {
                        break;
                    }
                }


                char[] bodyChars = new char[contentLength];

                reader.read(bodyChars);


                String body = new String(bodyChars);


                System.out.println("BODY:");
                System.out.println(body);


                String name =
                        JsonUtil.extractName(body);


                User user =
                        new User(0, name);


                User saved =
                        userRepository.save(user);


                json =
                        JsonUtil.userToJson(saved);
            } else if (method.equals("DELETE") && path.startsWith("/users/")) {

                int id = Integer.parseInt(
                        path.split("/")[2]
                );


                boolean deleted =
                        userRepository.deleteById(id);


                if (deleted) {

                    json = "{\"message\":\"User deleted\"}";

                } else {

                    json = "{\"message\":\"User not found\"}";
                }

            } else {

                json = "{}";

            }


            PrintWriter writer = new PrintWriter(
                    clientSocket.getOutputStream(),
                    true
            );


            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: application/json");
            writer.println(
                    "Content-Length: "
                            + json.getBytes().length
            );
            writer.println();
            writer.println(json);


            writer.flush();

            clientSocket.close();
        }
    }
}