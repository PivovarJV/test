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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {
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
            ResultSet rs = statement.executeQuery("""
                                                    SELECT ExchangeRates.ID,
                                                    CurrencyBase.ID,
                                                    CurrencyBase.FullName,
                                                    CurrencyBase.Code,
                                                    CurrencyBase.Sing,
                                                    CurrencyTarget.ID,
                                                    CurrencyTarget.FullName,
                                                    CurrencyTarget.Code,
                                                    CurrencyTarget.Sing,
                                                    ExchangeRates.Rate
                                                    FROM ExchangeRates
                                                    JOIN Currencies AS CurrencyBase
                                                    ON ExchangeRates.BaseCurrencyId = CurrencyBase.ID
                                                    JOIN Currencies AS CurrencyTarget
                                                    ON ExchangeRates.TargetCurrencyId = CurrencyTarget.ID
                                                    """);

            JsonArray jsonArray = new JsonArray();

            while (rs.next()) {
                JsonObject jsonExchangeRates = new JsonObject();
                jsonExchangeRates.addProperty("id", rs.getInt("ID"));

                JsonObject baseCurrency = new JsonObject();
                baseCurrency.addProperty("id", rs.getInt("ID"));
                baseCurrency.addProperty("name", rs.getString("FullName"));
                baseCurrency.addProperty("code", rs.getString("Code"));
                baseCurrency.addProperty("sign", rs.getString("Sing"));
                jsonExchangeRates.add("baseCurrency", baseCurrency);

                JsonObject targetCurrency = new JsonObject();
                targetCurrency.addProperty("id", rs.getInt("ID"));
                targetCurrency.addProperty("name", rs.getString("FullName"));
                targetCurrency.addProperty("code", rs.getString("Code"));
                targetCurrency.addProperty("sign", rs.getString("Sing"));
                jsonExchangeRates.add("targetCurrency", targetCurrency);

                jsonExchangeRates.addProperty("rate", rs.getDouble("Rate"));

                jsonArray.add(jsonExchangeRates);
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
