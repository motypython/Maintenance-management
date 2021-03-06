/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mm.rest.resources;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mm.rest.Example;
import com.mm.rest.UserContent;
import com.mm.rest.db.DbConnection;
import com.mm.rest.filter.JwtTokenNeeded;
import com.mm.rest.helper.JwtProperties;
import com.mm.rest.service.TestService;
import com.mm.rest.models.TestModel;
import com.mm.rest.models.authentication.LoginModel;
import com.mm.rest.models.equipment.SubCategoryModel;
import com.mm.rest.service.AuthService;
import com.mm.rest.service.SubCategoryService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

/**
 *
 * @author burkus
 */
// http://localhost:8080/api/subcategory
@Path("/subcategory")
public class SubCategoryResource {
    private static final ObjectMapper mapper = new ObjectMapper();
    //private static final Connection con = DbConnection.getConnection();
    private static final SubCategoryService scs = new SubCategoryService();
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    /*
        Post Body JSON:
        {
            "subCategoryId":Integer(This value just has to be present, make it 0 or something)
            "categoryId":Integer(Has to be a valid category id)
            "subCategoryName":String
            "qualificationId":Integer(Has to be a valid qualification id)
            "standardTime":Integer(Hours)
            "order":String
        }
    */
    public Response addSubCategory(SubCategoryModel subCategory) {
        try{
            System.out.println(subCategory.getQualificationId());
            return scs.addSubCategory(subCategory);

        }catch(Exception ex){
            System.out.println(ex);
            return Response.status(Response.Status.OK).entity("Error").build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubCategories() {
        try{
            return scs.getSubCategories();
        }catch(Exception ex){
            System.out.println(ex);
            return Response.status(Response.Status.OK).entity("Error").build();
        }
    }
}
