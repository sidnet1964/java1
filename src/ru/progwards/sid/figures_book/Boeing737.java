package ru.progwards.sid.figures_book;

public class Boeing737 {
    private int manufactureYear;
    private static int maxPassengersCount = 300;

    public Boeing737(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public static class Drawing {
        private int id;

        public Drawing(int id) {
            this.id = id;
        }

        public static int getMaxPassengersCount() {
            return maxPassengersCount;
        }
        @Override
        public String toString() {
            return "Drawing{" +
                    "id=" + id +
                    '}';
        }
    }
}
