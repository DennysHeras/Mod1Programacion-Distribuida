package com.distribuida.service;

import com.distribuida.model.Factura;

public interface CheckoutService {

    Factura checkoutByToken(String token);
}
