package com.redpilllinpro.xmas.calendar.holidays;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.BadRequestException;

@Path("/holidays/{year}")
public class SwedishHolidaysResource {
    
    @GET
    @Produces("application/json")
    public Response getHolidays(@PathParam("year") Integer year) {
        if (year < 2000 || year > 2099) {
            Map<String,String> errorResponse = new HashMap<>();
            errorResponse.put("message", "{year} must be betwwen 2000 and 2099");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
        }
        SwedishHolidays sh = SwedishHolidays.getInstance(year);
        return Response.ok(sh.getHolidayMap()).build();
    }
}
