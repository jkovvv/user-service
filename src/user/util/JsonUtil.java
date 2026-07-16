package user.util;

import user.model.User;

import java.util.List;

public class JsonUtil {

    public static String usersToJson(List<User> users) {

        StringBuilder json = new StringBuilder();

        json.append("[");

        for (int i = 0; i < users.size(); i++) {

            User user = users.get(i);

            json.append("{")
                    .append("\"id\":")
                    .append(user.getId())
                    .append(",")
                    .append("\"name\":\"")
                    .append(user.getName())
                    .append("\"")
                    .append("}");

            if (i < users.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");

        return json.toString();
    }

    public static String userToJson(User user) {

        if (user == null) {
            return "{}";
        }

        return "{"
                + "\"id\":" + user.getId()
                + ",\"name\":\"" + user.getName() + "\""
                + "}";
    }

    public static String extractName(String json) {

        return json
                .replace("{", "")
                .replace("}", "")
                .replace("\"", "")
                .split(":")[1]
                .trim();
    }
}