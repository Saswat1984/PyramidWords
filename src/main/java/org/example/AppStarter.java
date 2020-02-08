package org.example;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class AppStarter {
    private static final int VERTICLE_INSTANCES = Runtime.getRuntime().availableProcessors();
    private static final long MAX_EVENT_LOOP_EXECUTION_TIME = 30 * VertxOptions.DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME;

    public static void main( String[] args )
    {
        Vertx vertx = Vertx.vertx(new VertxOptions().setMaxEventLoopExecuteTime(MAX_EVENT_LOOP_EXECUTION_TIME).setWorkerPoolSize(1));
        DeploymentOptions deploymentOptions = new DeploymentOptions();
        deploymentOptions.setInstances(VERTICLE_INSTANCES);
        vertx.deployVerticle("org.example.verticle.Routerverticle", deploymentOptions, response -> {
            if (response.succeeded()){
                System.out.println("Verticle deployed successfully. Server ready to receive requests at port 8443");
            } else {
                System.out.println("Verticle deployement failed.");
            }
        });
    }
}
