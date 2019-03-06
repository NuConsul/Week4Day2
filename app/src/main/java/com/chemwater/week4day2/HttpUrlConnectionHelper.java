package com.chemwater.week4day2;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.chemwater.week4day2.UrlConstants.ACTUAL_URL;

public class HttpUrlConnectionHelper {
    /******************************************
     * Returns json string from RandomUser API
     * @return jsonString
     */
    public static String getJsonUsingHttpURLConn() throws Exception {
        String jsonReturnString = "";  //Return holder

        //Set-up URL object that will be used for connection
        URL url = new URL(ACTUAL_URL);
        //Declare our httpurlconnection
        HttpURLConnection httpURLConnection = null;
        //establish connection using url
        httpURLConnection = (HttpURLConnection)url.openConnection();

        //Get the stream of info coming from the connection
        InputStream inputStream = httpURLConnection.getInputStream();
        //Read the stream
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        //read the current data in reader
        int data = inputStreamReader.read();
        //While data is in stream and not at end
        while (data != -1) {
            //cast data to char
            char current = (char) data;
            //cycle the reader
            data = inputStreamReader.read();
            //set new char into our return string
            jsonReturnString = jsonReturnString + current;
        }
        //return the built json string
        return jsonReturnString;
    }
}