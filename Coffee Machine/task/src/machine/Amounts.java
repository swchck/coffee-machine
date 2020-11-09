package machine;

public enum Amounts {
        AMOUNT_OF_WATER("amountOfWater"),
        AMOUNT_OF_MILK("amountOfMilk"),
        AMOUNT_OF_COFFEE("amountOfCoffee"),
        DISPOSABLE_CUPS("disposableCups"),
        MONEY("money");

        String name;

        public String getName() {
                return name;
        }

        Amounts(String name) {
                this.name = name;
        }
}
