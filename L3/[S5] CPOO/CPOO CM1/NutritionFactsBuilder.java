package fr.uparis.exemples;
//adapté de "Effective Java" 

//technique recommandée!!!

public class NutritionFactsB {
	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;

	public static final int FAT_VALUE = 9;
	public static final int SUGAR_VALUE = 4;

	public static class Builder {
// Required parameters
		private final int servingSize;
		private final int servings;
// Optional parameters - initialized to default values
		private int calories = 0;
		private int fat = 0;
		private int sodium = 0;
		private int carbohydrate = 0;

		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}

		public Builder calories(int val) {
			calories = val;
			return this;
		}

		public Builder fat(int val) {
			fat = val;
			return this;
		}

		public Builder sodium(int val) {
			sodium = val;
			return this;
		}

		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}

		public NutritionFactsB build() {
			// on peut ajouter ce genre de tests de cohérence
			if (calories != 0 && calories < FAT_VALUE * fat + SUGAR_VALUE * carbohydrate) // cheating detected
				throw new IllegalArgumentException();
			return new NutritionFactsB(this);
		}
	}

	private NutritionFactsB(Builder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}
}
