/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mm.rest.service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mm.rest.db.equipment.EquipmentDatabase;
import com.mm.rest.exceptions.DatabaseException;
import com.mm.rest.models.equipment.EquipmentModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;

/**
 *
 * @author burkus
 */
public class EquipmentService {
    
    private EquipmentDatabase EDB = new EquipmentDatabase();
    
    public Response addEquipment(EquipmentModel equipment) {
        
        try {
            //Need to add the subcategory's category's repair interval
            //Maybe with doing a join select to get the interval, and add it to the timestamp
            //created in addEquipmentToDB
            int interval = EDB.getSubCategoryInterval(equipment.getSubCategoryId());
            int temp = EDB.addEquipmentToDB(equipment, interval);
            
            if(temp!=-1){
                return Response.status(Response.Status.OK).entity("Equipment added!").build();
            } else {
                return Response.status(Response.Status.NOT_MODIFIED).entity("ERROR!").build();
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    public Response getEquipments() {
        try {
            ArrayNode equipments = EDB.getEquipments();
            return Response.status(Response.Status.OK).entity(equipments.toString()).build();
        } catch (DatabaseException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.NOT_MODIFIED).entity("ERROR!").build();
        }
    }
}
