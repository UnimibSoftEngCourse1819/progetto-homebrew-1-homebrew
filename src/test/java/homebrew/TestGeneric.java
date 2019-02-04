package homebrew;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.recipe.Recipe;
import model.recipe.RecipeDao;
//import controller.user.Login;

public class TestGeneric {

	public static void main(String[] args) {
		// Login log = new Login();
		// USER mail@prova.it - password
		// HASH
		// e9a75486736a550af4fea861e2378305c4a555a05094dee1dca2f68afea49cc3a50e8de6ea131ea521311f4d6fb054a146e8282f8e35ff2e6368c1a62e909716
		// boolean resp = log.match("mail@prova.it", "password");
		// System.out.println(resp);
		RecipeDao recipeDao = new RecipeDao();
		Recipe recipe = recipeDao.findRecipeByID(10000001);
		Map<Integer, String> hashmapObject = recipe.getSteps();


		// we will iterate using Iterator
		Iterator<Map.Entry<Integer, String>> iterator = hashmapObject.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<Integer, String> entry = iterator.next();
			System.out.println("Key is = " + entry.getKey() + ", Value is = " + entry.getValue());
		}
	}

}
