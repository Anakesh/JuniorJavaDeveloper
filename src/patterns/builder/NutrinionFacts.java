package patterns.builder;

public class NutrinionFacts {
    private final int servings;
    private final int calories;
    private final int fat;

    public static class Builder{
        private final int serving;
        private int calories = 0;
        private int fat = 0;

        public Builder (int serving){
            this.serving = serving;
        }

        public Builder setCalories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder setFat(int fat) {
            this.fat = fat;
            return this;
        }
        public NutrinionFacts build(){
            return new NutrinionFacts(this);
        }
    }
    private NutrinionFacts(Builder builder){
        servings = builder.serving;
        calories = builder.calories;
        fat = builder.fat;
    }

    public static void main(String[] args) {
        NutrinionFacts beaf = new Builder(10)
                .setCalories(456)
                .setFat(78)
                .build();
        NutrinionFacts beaf2 = new Builder(2)
                .setCalories(333)
                .build();
    }
}
