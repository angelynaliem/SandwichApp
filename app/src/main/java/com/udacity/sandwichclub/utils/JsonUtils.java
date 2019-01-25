package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sw = null;

        String Name = "name",
                MainName = "mainName",
                AlsoKnownAs = "alsoKnownAs",
                PlaceOfOrigin = "placeOfOrigin",
                Description = "description",
                ImageUrl = "image",
                Ingredients = "ingredients";

        try {

            JSONObject rootJObject, nameObject;
            String mainName, placeOfOrigin, description, image;
            List<String> alsoKnownAsArray, ingredientsArray;

            rootJObject = new JSONObject(json);

            nameObject = rootJObject.getJSONObject(Name);

            mainName = nameObject.optString(MainName);

            alsoKnownAsArray = jsonArrayValuesToList(nameObject.getJSONArray(AlsoKnownAs));

            placeOfOrigin = rootJObject.optString(PlaceOfOrigin);
            description = rootJObject.optString(Description);
            image = rootJObject.optString(ImageUrl);

            ingredientsArray = jsonArrayValuesToList(rootJObject.getJSONArray(Ingredients));

            sw = new Sandwich(mainName, alsoKnownAsArray, placeOfOrigin, description, image, ingredientsArray);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sw;
    }

    private static List<String> jsonArrayValuesToList(JSONArray jsonArray) {
        List<String> swList = new ArrayList<String>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                swList.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return swList;
    }


}
