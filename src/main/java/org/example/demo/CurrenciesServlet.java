package org.example.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            SQLiteDataSource dataSource = new SQLiteDataSource();
            URL db = getClass().getClassLoader().getResource("db.db");
            dataSource.setUrl("jdbc:sqlite:" + db);
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM currencies");

            JsonArray jsonArray = new JsonArray();

            while (rs.next()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", rs.getInt("ID"));
                jsonObject.addProperty("code", rs.getString("Code"));
                jsonObject.addProperty("name", rs.getString("FullName"));
                jsonObject.addProperty("sing", rs.getString("Sing"));

                jsonArray.add(jsonObject);
            }

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            String json = gson.toJson(jsonArray);
            pw.println(json);

            connection.close();
            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}