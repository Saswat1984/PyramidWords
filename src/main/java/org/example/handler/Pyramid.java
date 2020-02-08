package org.example.handler;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;


public interface Pyramid extends Handler<RoutingContext> {
    static Pyramid create(){
        return new PyramidImpl();
    }
}
