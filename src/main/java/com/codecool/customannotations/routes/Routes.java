package com.codecool.customannotations.routes;
import com.codecool.customannotations.webroute.WebRoute;


public class Routes {

        @WebRoute("/test1")
        public String test1(){
                return "this is the /test1 page";
        }

        @WebRoute("/test2")
                public String test2(){
                        return "this is the /test2 page";
                }
}
