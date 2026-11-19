package api.endpoint;
/////https://petstore3.swagger.io/
////https://petstore3.swagger.io/#/user/createUser
/// https://petstore3.swagger.io/#/user/getUserByName
/// https://petstore3.swagger.io/#/user/updateUser
/// https://petstore3.swagger.io/#/user/deleteUser
public class Routes {

   public static String base_url = "https://petstore.swagger.io/v2";

   ///user model
   public static String post_url=base_url+"/user";
   public static String get_url=base_url+"/user/{username}";
   public static String update_url=base_url+"/user/{username}";
   public static String delete_url=base_url+"/user/{username}";

}
