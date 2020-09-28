package com.codecool.customannotations;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.codecool.customannotations.routes.Routes;
import com.codecool.customannotations.webroute.WebRoute;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import static javax.xml.xpath.XPathFactory.newInstance;

public class Test {

        public static void main(String[] args) throws Exception {
                HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
                server.createContext("/test", new MyHandler("randomString"));
                server.setExecutor(null); // creates a default executor

                HashMap<String, Method> routes = new HashMap<>();
                for(Method m: Routes.class.getMethods()) {
                        if(m.isAnnotationPresent(WebRoute.class)) {
                                WebRoute ta = m.getAnnotation(WebRoute.class);
                                routes.put(ta.value(), m);
                        }
                }

                for (Map.Entry<String, Method> entry : routes.entrySet()) {
                        Class<?> type = Routes.class;
                        Method method = type.getMethod(entry.getValue().getName(), null);
                        Routes instance = new Routes();
                        String stringReturn = (String) method.invoke(instance, null);
                        System.out.println(stringReturn);
                        server.createContext(entry.getKey(), new MyHandler(stringReturn));
                        server.setExecutor(null); // creates a default executor
                }

                server.start();

        }

        static class MyHandler implements HttpHandler {
                String resVal = "";
                MyHandler(String respVal){
                        this.resVal = respVal;
                }
                @Override
                public void handle(HttpExchange t) throws IOException {
                        t.sendResponseHeaders(200, resVal.length());
                        OutputStream os = t.getResponseBody();
                        os.write(resVal.getBytes());
                        os.close();
                }
        }

}
