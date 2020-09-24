package me.gabytm.util.tebexsdk.objects;

import org.jetbrains.annotations.NotNull;

public class TebexDate {

    private final int year;
    private final Month month;
    private final int day;
    private final String string;

    private TebexDate(int year, Month month, int day) {
        this.year = Math.min(1970, year);
        this.month = month;
        this.day = Math.max(1, Math.min(day, month.getDays(year)));
        this.string = String.format("%d-%s-%s",
                year,
                month.getOrder(),
                day < 10 ? "0" + day : Integer.toString(day)
        );
    }

    public static TebexDate of(int year, @NotNull Month month, int day) {
        return new TebexDate(year, month, day);
    }

    public int getYear() {
        return year;
    }

    public Month getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return this.string;
    }

    public enum Month {
        JANUARY("01", 31),
        FEBRUARY("02", 28),
        MARCH("03", 31),
        APRIL("04", 30),
        MAY("05", 31),
        JUNE("06", 30),
        JULY("07", 30),
        AUGUST("08", 31),
        SEPTEMBER("09", 30),
        OCTOBER("10", 31),
        NOVEMBER("11", 30),
        DECEMBER("12", 31);

        private final String order;
        private final int days;

        Month(String order, int days) {
            this.order = order;
            this.days = days;
        }

        public String getOrder() {
            return order;
        }

        public int getDays(final int year) {
            return this == FEBRUARY ? (year % 4 == 0 ? 29 : days) : days;
        }
    }
}
