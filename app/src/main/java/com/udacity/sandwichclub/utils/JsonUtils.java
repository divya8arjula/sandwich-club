package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static JSONObject jObject = null;


    public static Sandwich parseSandwichJson(String json) {
        String desc = null;
        String placeOfOrigin = null;
        String mainName = null;
        List<String> alsoKnownAsList = new ArrayList<>();
        ArrayList<String> ingredientsList = new ArrayList<>();


        String imageUrl = null;

        try {
            jObject = new JSONObject(json);

            JSONObject nameInfo = (JSONObject) jObject.get("name");
            mainName = (String) nameInfo.get("mainName");
            JSONArray alsoKnownAsJsonArray = (JSONArray) nameInfo.get("alsoKnownAs");
            if (alsoKnownAsJsonArray != null) {
                for (int i=0;i<alsoKnownAsJsonArray.length();i++){
                    alsoKnownAsList.add(alsoKnownAsJsonArray.getString(i));
                }
            }

            placeOfOrigin = (String) jObject.get("placeOfOrigin");
            desc = (String) jObject.get("description");
            imageUrl = (String)jObject.get("image");

            JSONArray ingrediantsJsonArray = (JSONArray) jObject.get("ingredients");

            if (ingrediantsJsonArray != null) {
                for (int i=0;i<ingrediantsJsonArray.length();i++){
                    ingredientsList.add(ingrediantsJsonArray.getString(i));
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        // TODO: Parse the given JSON string.
        Sandwich resultSandwich = new Sandwich();

        resultSandwich.setMainName(mainName);
        resultSandwich.setAlsoKnownAs(alsoKnownAsList);

        resultSandwich.setPlaceOfOrigin(placeOfOrigin);
        resultSandwich.setDescription(desc);
        resultSandwich.setImage(imageUrl);
        resultSandwich.setIngredients(ingredientsList);





        return resultSandwich;
    }
}
