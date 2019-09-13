/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class RecipeList extends ArrayList<Recipe> {
       private static final long serialVersionUID = 1L;
       public RecipeList() {
        super();
    }
    public RecipeList(List<Recipe> c) {
        super(c);
    }

    public List<Recipe> getRecipes() {
        return this;
    }
    public void setRecipes(List<Recipe> recipes) {
        this.addAll(recipes);
    }
    
    public RecipeList sortByDate() {     
        this.sort(new DateComparator());
        return this;
    }

    class DateComparator implements Comparator {

        @Override
        public int compare(Object ob1, Object ob2) {
            Recipe recipe1 = (Recipe) ob1;
            Recipe recipe2 = (Recipe) ob2;
            
            if (recipe1.getReleaseDate() == null || recipe2.getReleaseDate() == null )
                return 0;
            
            if (recipe1.getReleaseDate().after(recipe2.getReleaseDate()))
                return 1;
            else if (recipe1.getReleaseDate().before(recipe2.getReleaseDate()))
                return -1;
            else
                return 0;
        } 
    
    } 
}
