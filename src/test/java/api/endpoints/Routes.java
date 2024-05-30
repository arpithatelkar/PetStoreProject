package api.endpoints;

/* swaager link = "https://petstore3.swagger.io"
 * Create a user (Post) :https://petstore3.swagger.io/api/v3/user
 * Get a user by name (get) : https://petstore3.swagger.io/user/{username}
 * Update a user (Put) : https://petstore3.swagger.io/user/{username}
 * Delete a user (Delete) : https://petstore3.swagger.io/user/{username}
 * 
 */
public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2";

	// User module

	public static String post_url = base_url + "/user";
	public static String get_url = base_url + "/user/{username}";
	public static String update_url = base_url + "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";

	// store Module

}
