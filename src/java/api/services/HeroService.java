/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.services;

import api.models.Hero;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.faces.push.Push;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author jcpalma
 */
@Path("/heros")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class HeroService {

    private static final List<Hero> heros = new ArrayList();

    static {
        heros.add(new Hero(1, "Hulk", "Ira", true));
        heros.add(new Hero(2, "Thor", "Martillo", true));
        heros.add(new Hero(3, "IronMan", "Dinero", false));
        heros.add(new Hero(4, "Cápitana Marvel", "Cosmo", true));

    }

    public HeroService() {
//        System.out.println("HeroService: Constructor");
    }

    @GET
    public Response getAllHeros() {
        return Response.ok(heros).build();
    }
    
    @GET
    @Path("{id}")
    public Response getHero(
            @PathParam("id") int id,
            @HeaderParam("token") String token,
            @QueryParam("page") int page
    ){
        System.out.printf("ID: %1$d\n", id);
        System.out.printf("Page: %1$d\n", page);
        System.out.printf("Token: %1$s\n", token);
        
        
        //Busca al héroe según el id
        Optional<Hero> hero = heros.stream().filter( h -> h.getId() == id).findFirst();
        
        if( hero.isEmpty() ){
            //return Response.status(Response.Status.BAD_REQUEST).entity("Hero not found").build();
            return Response.ok("{}", MediaType.APPLICATION_JSON).build();
        }
        
        return Response.ok( hero.get() ).build();
    }
    
    @POST
    public Response addHero(Hero heroResquest){
        heroResquest.setId( heros.size()+1 );
        heros.add(heroResquest);
        return Response.ok( heroResquest ).build();
    }

}
