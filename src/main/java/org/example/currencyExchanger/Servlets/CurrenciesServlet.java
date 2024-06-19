package org.example.currencyExchanger.Servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.currencyExchanger.model.Currency;
import org.example.currencyExchanger.service.CurrencyService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {
    private CurrencyService currencyService = new CurrencyService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Currency> currencies = currencyService.getAllCurrencies();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter pw = response.getWriter();

        Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
        String json = gson.toJson(currencies);
        pw.println(json);
    }
}