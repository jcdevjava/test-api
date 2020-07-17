/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author jcpalma
 */

@ApplicationPath("/")
public class RestApplication extends Application{
    
    public RestApplication(){
        super();
        System.out.println("RestApplication()");
    }
    
}
