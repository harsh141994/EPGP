package com.epgp.inflibnet.epgp.parsers;

import com.epgp.inflibnet.epgp.model.SubDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 08-07-2015.
 */
public class SubDetailsJSONParser {

    public static List<SubDetails> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<SubDetails> subDetailsList = new ArrayList<SubDetails>();

            for (int i = 0 ; i<ar.length(); i++){
                JSONObject obj = ar.getJSONObject(i);
                SubDetails subDetails = new SubDetails();

                subDetails.setFname(obj.getString("fname"));
                subDetails.setMname(obj.getString("mname"));
                subDetails.setLname(obj.getString("lname"));
                subDetails.setUsrno(obj.getString("usrno"));
                subDetails.setDesignation(obj.getString("designation"));
                subDetails.setOrganization(obj.getString("organization"));
                subDetails.setPhotofile(obj.getString("photofile"));

                subDetails.setPhotofileext(obj.getString("photofileext"));
                subDetails.setContactno(obj.getString("contactno"));
                subDetails.setEmailid(obj.getString("emailid"));
                subDetails.setR_subject(obj.getString("r_subject"));



                subDetailsList.add(subDetails);
            }

            return subDetailsList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
