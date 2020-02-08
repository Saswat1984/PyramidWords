package org.example.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import org.example.handler.Pyramid;

public class Routerverticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx);

        router
                .route(HttpMethod.GET, "/pyramidword/")
                .handler(Pyramid.create());

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8443, listnerHandler -> {
                    if (listnerHandler.succeeded()) {
                        startFuture.complete();
                    } else {
                        startFuture.fail(listnerHandler.cause());
                    }
                });
    }
}
