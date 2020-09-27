package com.codecool.customannotations;

import com.codecool.customannotations.webroute.WebRoute;

public class Routes {
        @WebRoute("/test")
        public String test1(){
                return "f";
        }
        @WebRoute("/test2")
        public String test2(){
                return "d";
        }
}
