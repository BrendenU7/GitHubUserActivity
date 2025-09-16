import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GitHubActivity {
    public static void main(String[] args) {

        if(args.length == 0){
            System.out.println("Please enter a Github username "); //kamranahmedse
            return;
        }

        String username = args[0];
        System.out.println("Fetching activity for: " + username + "/events");


        try {
            URL url = new URL("https://api.github.com/users/" + username + "/events");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//open connection
            conn.setRequestMethod("GET"); //Creating the GET request

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){//200 = It works
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                //read response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print raw JSON response
                System.out.println("response from GitHub:");
                System.out.println(response.toString());
            }else{
                System.out.println("Error GitHub API returned response code: " + responseCode);
            }


        } catch (Exception e) {
            System.out.println("Error occured: " + e.getMessage());
        }


    }
}