package qu.senior.services;

import com.google.gson.Gson;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import qu.common.PasswordSecurity;
import qu.senior.entities.NmapHost;
import qu.senior.entities.Scan;
import qu.senior.entities.User;
import qu.senior.model.networkscan.BasicScan;
import qu.senior.model.networkscan.NetworkScan;
import qu.senior.model.webscan.WebScan;
import qu.senior.repositories.UserRepository;


@Stateless
@Path("/users")
public class UsersService {

    @Inject
    private UserRepository userRepository;
    @Inject
    private WebScan webScan;
    @Inject
    private NetworkScan networkScan;
    @Inject
    private BasicScan basicScan;

    
    @PostConstruct
    private void insertTestData() {
        //Insert some test data just in case tha database is empty
        
    }


      @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUsers() {
        String json = (new Gson()).toJson(userRepository.getUsers());
        return Response.ok(json).build();
    }


//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getUser(@PathParam("id") int userId) {
//            System.out.println("getUser service. userId: " + userId);
//            User user = new User();
//            try {
//                user = userRepository.login("sebti", "password"); //userRepository.getUser(userId);
//            } catch (Exception ex) {}
//            Gson gson = new Gson();
//            String json = gson.toJson(user);
//            System.out.println(json);
//            return Response.ok(json, MediaType.APPLICATION_JSON).build();
//            //return Response.ok(user).build();
//    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginParam loginParam, @Context HttpServletRequest req) {
        System.out.println(loginParam.email);
        try {
            User user = userRepository.login(loginParam.getEmail(), loginParam.getPassword()); //userRepository.getUser(id);
            //This line was supposed to work but it did NOT as a workaround I am using Gson serializer
            //return Response.ok(user).build(); 
            if(user == null){
               return Response.status(Response.Status.BAD_REQUEST).entity("Wrong Credential").build();
            }
            Gson gson = new Gson();
            String json = gson.toJson(user);
            System.out.println(json);
            req.getSession().setAttribute("currentUser", user);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
            //return Response.ok(user).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    
    
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(UserParam userParam) {
        try {
            
             User user=new User(userParam.getFirstName(),userParam.getLastName(),userParam.getEmail(),userParam.getPassword(),userParam.getUserType());
           System.out.println(userParam);
      //user.setPassword(new PasswordSecurity().encryptPassword(user.getPassword()));
          userRepository.addUser(user);
            //return Response.ok(user).build(); 
            Gson gson = new Gson();
            String json = gson.toJson(user);
            System.out.println(json);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
            //return Response.ok(user).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
//    
//    @POST
//    @Path("/scan")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response scan(ScanParam scanParam , @Context HttpServletRequest req) {
//        try {
//            System.out.println(scanParam.getIp() + "  " + scanParam.isIsWebScan() + "   "+ scanParam.isIsNetworkScan() );
//            User curruntUser = (User) req.getSession().getAttribute("currentUser");
//            Gson gson = new Gson();
//            
//            Scan scan= new Scan();
//            scan.setIp(scanParam.getIp());
//            scan.setIsNetworkScam(scanParam.isIsNetworkScan());
//            scan.setIsWebScan(scanParam.isIsWebScan());
//            scan.setUserId(curruntUser.getId());
//            if(scan.isIsNetworkScam())
//                scan.setNetworkScanResult(networkScan.networkscan(scan.getIp()));
//            if(scan.isIsWebScan())
//                scan.setWebScanResult(webScan.webScan(scan.getIp(),scanParam.getScanlevel()));
//            scan=userRepository.inserScan(scan);
//            String json = gson.toJson(scan);
//            System.out.println(json);
//            return Response.ok(json, MediaType.APPLICATION_JSON).build();
//            //return Response.ok(user).build();
//        } catch (Exception ex) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
//        }
//    }
//    
    
        @POST
    @Path("/networkscan")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response netWorkScan(String ip , @Context HttpServletRequest req) {
        try {
            System.out.println(ip);

            List<NmapHost> hosts ; 
            
            Gson gson = new Gson();

            hosts=basicScan.getNmapHost(ip);
            String json = gson.toJson(hosts);
            System.out.println(json);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
            //return Response.ok(user).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    
    
    
    
    @POST
    @Path("/history")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response history( @Context HttpServletRequest req) {
        try {
        
            User curruntUser = (User) req.getSession().getAttribute("currentUser");
            Gson gson = new Gson();
            String json = gson.toJson(userRepository.getHistoryList(curruntUser.getId()));
          
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
            //return Response.ok(user).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addUser(UserParam newuser) {
          User user=new User(newuser.getFirstName(),newuser.getLastName(),newuser.getEmail(),newuser.getPassword(),newuser.getUserType());
           System.out.println(newuser);
      //user.setPassword(new PasswordSecurity().encryptPassword(user.getPassword()));
          userRepository.addUser(user);
       try {
             
            return Response.ok(newuser).build();
        } catch (Exception ex) {
            String msg = String.format("adding new user  failed because : \n%s",
                    ex.getMessage());
            return Response.serverError().entity(msg).build();
        }
            
    }
 
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int userId) {
        try {
            userRepository.deleteUser(userId);
            String msg = String.format("User #%s deleted sucessfully", userId);
            return Response.ok(msg).build();
        } catch (Exception ex) {
            String msg = String.format("Deleting user failed because : %s",
                    ex.getCause().getMessage());
            return Response.serverError().entity(msg).build();
        }
    }
   
    
    
//    @GET
//    @Path("/scan/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getScans(@PathParam("id") int scanId) {
//
//            Scan scan  = userRepository.getScan(scanId);
////           System.out.println("preint object:");
////           System.out.println(scan.getId());
////           for(int i = 0  ; i<1000 ; i++){
////                 System.out.println("preint object:");
////           }
//         System.out.println(scan.getWebScanResult().getWebsites());
//         System.out.println(scan.getNetworkScanResult());
//         System.out.println(scan.toString());
//            
//            Gson gson = new Gson();
//            String json = gson.toJson(scan);
//            System.out.println(json);
//            return Response.ok(json, MediaType.APPLICATION_JSON).build();
//            //return Response.ok(user).build();
//    }
}
