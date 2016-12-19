/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.services;

import com.google.gson.Gson;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import qu.senior.entities.Scan;
import qu.senior.entities.User;
import qu.senior.model.networkscan.BasicScan;
import qu.senior.model.networkscan.NetworkScan;
import qu.senior.model.webscan.WebScan;
import qu.senior.repositories.ScanRepository;
import qu.senior.repositories.UserRepository;

@Stateless
@Path("/scan")
public class ScanService {

    @Inject
    private ScanRepository scanRepository;
    @Inject
    private WebScan webScan;
    @Inject
    private NetworkScan networkScan;
    @Inject
    private BasicScan basicScan;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response scan(ScanParam scanParam, @Context HttpServletRequest req) {
        try {
            System.out.println(scanParam.getIp() + "  " + scanParam.isIsWebScan() + "   " + scanParam.isIsNetworkScan());
            User curruntUser = (User) req.getSession().getAttribute("currentUser");
            Gson gson = new Gson();

            Scan scan = new Scan();
            scan.setIp(scanParam.getIp());
            scan.setIsNetworkScam(scanParam.isIsNetworkScan());
            scan.setIsWebScan(scanParam.isIsWebScan());
            scan.setUserId(curruntUser.getId());
            if (scan.isIsNetworkScam()) {
                scan.setNetworkScanResult(networkScan.networkscan(scan.getIp()));
            }
            if (scan.isIsWebScan()) {
                scan.setWebScanResult(webScan.webScan(scan.getIp(), scanParam.getScanlevel()));
            }
            scan = scanRepository.inserScan(scan);
            String json = gson.toJson(scan);
            System.out.println(json);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
            //return Response.ok(user).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScans(@PathParam("id") int scanId) {

        Scan scan = scanRepository.getScan(scanId);
//           System.out.println("preint object:");
//           System.out.println(scan.getId());
//           for(int i = 0  ; i<1000 ; i++){
//                 System.out.println("preint object:");
//           }
        System.out.println(scan.getWebScanResult().getWebsites());
        System.out.println(scan.getNetworkScanResult());
        System.out.println(scan.toString());

        Gson gson = new Gson();
        String json = gson.toJson(scan);
        System.out.println(json);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
        //return Response.ok(user).build();
    }
}
