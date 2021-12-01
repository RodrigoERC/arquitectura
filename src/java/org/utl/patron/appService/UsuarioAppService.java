/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utl.patron.appService;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HOME
 */
public class UsuarioAppService extends Application {
    
    //ControlDatosVehiculo cdv;
    /*******************************USERS**********************************/
    @GET
    @Path("getAllUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser() {
        cdv = new ControlDatosVehiculo();
        Gson conversor = new Gson();
        String out = "";
        List<DatosVehiculo> listaDV = new ArrayList<>();
        try {
            listaDV = cdv.getAll();
            out = conversor.toJson(listaDV);
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\":\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @POST
    @Path("insertUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUser(@FormParam("json") String json) {
        cdv = new ControlDatosVehiculo();
        Gson conversor = new Gson();
        String out = "";
        DatosVehiculo dv = conversor.fromJson(json, DatosVehiculo.class);

        try {
            if (cdv.insert(dv)) {
                out = "{\"result:\":\"1\"}";
            } else {
                out = "{\"result:\":\"0\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\":\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @POST
    @Path("updateUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@FormParam("json") String json) {
        cdv = new ControlDatosVehiculo();
        Gson conversor = new Gson();
        String out = "";
        DatosVehiculo dv = conversor.fromJson(json, DatosVehiculo.class);

        try {
            if (cdv.update(dv)) {
                out = "{\"result:\":\"1\"}";
            } else {
                out = "{\"result:\":\"0\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\":\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @GET
    @Path("searchUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchUser(@QueryParam("search") String search) {
        cdv = new ControlDatosVehiculo();
        Gson conversor = new Gson();
        String out = "";
        List<DatosVehiculo> listaDV = new ArrayList<>();
        try {
            listaDV = cdv.searchBy(search);
            out = conversor.toJson(listaDV);
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error:\":\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
