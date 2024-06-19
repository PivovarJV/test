package org.example.currencyExchanger.service;

import org.example.currencyExchanger.dao.CurrencyDAO;
import org.example.currencyExchanger.model.Currency;

import java.util.List;

public class CurrencyService {
    private CurrencyDAO currencyDAO = new CurrencyDAO();

    public List<Currency> getAllCurrencies() {
        return currencyDAO.getAllCurrencies();
    }
}
