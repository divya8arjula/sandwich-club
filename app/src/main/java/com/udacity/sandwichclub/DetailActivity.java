package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

import static com.udacity.sandwichclub.R.string.detail_error_message;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView place_of_origin = findViewById(R.id.origin_tv_value);
        place_of_origin.setText(sandwich.getPlaceOfOrigin());

        createAlsoKnownAs(sandwich.getAlsoKnownAs());

        createIngredients(sandwich.getIngredients());

        TextView desc_value_tv = findViewById(R.id.description_tv_value);
        desc_value_tv.setText(sandwich.getDescription());



    }



    private void createAlsoKnownAs(List<String> alsoKnownAsList) {
        TextView alsoknownas_tv;
        LinearLayout alsoKnownAsViewGroup = findViewById(R.id.also_known_tv_value);
        if (alsoKnownAsList.isEmpty()) {
            alsoknownas_tv = new TextView(this);
            alsoKnownAsViewGroup.addView(alsoknownas_tv);
            return;
        }
        for(String alsoknownas : alsoKnownAsList) {
            alsoknownas_tv = new TextView(this);
            alsoknownas_tv.setText(alsoknownas);
            alsoKnownAsViewGroup.addView(alsoknownas_tv);
        }
    }

    private void createIngredients(List<String> ingredients) {

        TextView ingredients_tv;
        LinearLayout ingredients_viewgroup = findViewById(R.id.ingredients_value);
        if (ingredients.isEmpty()) {
            ingredients_tv = new TextView(this);
            ingredients_viewgroup.addView(ingredients_tv);
            return;
        }
        for(String ingredient : ingredients) {
            ingredients_tv = new TextView(this);
            ingredients_tv.setText(ingredient);
            ingredients_viewgroup.addView(ingredients_tv);
        }
    }





}
