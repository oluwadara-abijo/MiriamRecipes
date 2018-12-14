package com.example.dara.miriamsrecipes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.dara.miriamsrecipes.data.model.Ingredient;
import com.example.dara.miriamsrecipes.data.model.Recipe;
import com.example.dara.miriamsrecipes.ui.list.MainActivity;

import java.util.List;

import static com.example.dara.miriamsrecipes.ui.list.MainActivity.EXTRA_RECIPE_ID;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {

    private static Recipe mDesiredRecipe;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //Create an intent to launch main activity when clicked
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_RECIPE_ID, mDesiredRecipe);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);

        //Handle clicks and launch main activity
        views.setOnClickPendingIntent(R.id.widget_tv_ingredients, pendingIntent);
        views.setOnClickPendingIntent(R.id.widget_image, pendingIntent);

        //Get the ingredients of the desired recipe
        List<Ingredient> mIngredients = mDesiredRecipe.getIngredients();

        //Build a string containing all ingredients
        StringBuilder allIngredients = new StringBuilder();

        for (int i = 0; i < mIngredients.size(); i++) {
            String ingredient = mIngredients.get(i).getIngredient();

            allIngredients.append(ingredient).append("\n");
        }

        //Display the ingredients on the remote view
        views.setTextViewText(R.id.widget_tv_ingredients, allIngredients);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static void setDesiredRecipe(Recipe desiredRecipe) {
        BakingAppWidget.mDesiredRecipe = desiredRecipe;
    }
}

