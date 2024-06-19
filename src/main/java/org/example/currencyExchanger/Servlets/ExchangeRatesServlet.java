package org.example.currencyExchanger.Servlets;

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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        PrintWriter pw = response.getWriter();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        try {
//            SQLiteDataSource dataSource = new SQLiteDataSource();
//            URL db = getClass().getClassLoader().getResource("db.db");
//            dataSource.setUrl("jdbc:sqlite:" + db);
//            Connection connection = dataSource.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("""
//                                                    SELECT ExchangeRates.ID,
//                                                    C1.ID,
//                                                    C1.FullName,
//                                                    C1.Code,
//                                                    C1.Sing,
//                                                    C2.ID,
//                                                    C2.FullName,
//                                                    C2.Code, C2.Sing,
//                                                    ExchangeRates.Rate
//                                                    FROM ExchangeRates
//                                                    JOIN Currencies C1 ON ExchangeRates.BaseCurrencyId = C1.ID
//                                                    JOIN Currencies C2 ON ExchangeRates.TargetCurrencyId = C2.ID
//                                                    """);
//
//            JsonArray jsonArray = new JsonArray();
//
//            while (rs.next()) {
//                JsonObject jsonExchangeRates = new JsonObject();
//                jsonExchangeRates.addProperty("id", rs.getInt("ID"));
//
//                JsonObject baseCurrency = new JsonObject();
//                baseCurrency.addProperty("id", rs.getInt("C1.ID"));
//                baseCurrency.addProperty("name", rs.getString("FullName"));
//                baseCurrency.addProperty("code", rs.getString("Code"));
//                baseCurrency.addProperty("sign", rs.getString("Sing"));
//                jsonExchangeRates.add("baseCurrency", baseCurrency);
//
//                JsonObject targetCurrency = new JsonObject();
//                targetCurrency.addProperty("id", rs.getInt("ID"));
//                targetCurrency.addProperty("name", rs.getString("FullName"));
//                targetCurrency.addProperty("code", rs.getString("Code"));
//                targetCurrency.addProperty("sign", rs.getString("Sing"));
//                jsonExchangeRates.add("targetCurrency", targetCurrency);
//
//                jsonExchangeRates.addProperty("rate", rs.getDouble("Rate"));
//
//                jsonArray.add(jsonExchangeRates);
//            }
//
//            Gson gson = new GsonBuilder()
//                    .setPrettyPrinting()
//                    .create();
//            String json = gson.toJson(jsonArray);
//            pw.println(json);
//
//            connection.close();
//            statement.close();
//            rs.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
