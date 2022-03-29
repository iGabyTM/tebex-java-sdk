package me.gabytm.util.tebexsdk.objects;

import org.jetbrains.annotations.NotNull;

/**
 * An util class to create a date following the format used by Tebex
 *
 * @author GabyTM
 * @see TebexDate#of(int, Month, int)
 * @since 0.0.1-BETA
 */
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

    /**
     * An enum representing each month and it's number of days
     */
    public enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(30),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private static final int FEBRUARY_LEAP_YEAR_DAYS = 29;
        private final String order;
        private final int days;

        Month(int days) {
            this.order = (ordinal() < 9 ? "0" : "") + (ordinal() + 1);
            this.days = days;
        }

        public String getOrder() {
            return order;
        }

        /**
         * Get the number of days of a month. The year parameter is only used
         * for {@link Month#FEBRUARY} whose number of days depends on if the year is leap (29) or not (28)
         *
         * @param year year
         * @return numbers of days
         * @see <a href="https://en.wikipedia.org/wiki/Leap_year">Leap Year</a>
         */
        public int getDays(final int year) {
            if (this == FEBRUARY) {
                if (year % 4 != 0) {
                    return days;
                }

                if (year % 100 != 0) {
                    return FEBRUARY_LEAP_YEAR_DAYS;
                }

                if (year % 400 != 0) {
                    return days;
                }

                return FEBRUARY_LEAP_YEAR_DAYS;
            }

            return days;
        }
    }
}
