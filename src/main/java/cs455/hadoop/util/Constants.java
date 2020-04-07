package cs455.hadoop.util;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final List<String> eastCoastStates =
            Arrays.asList("New York",
                    "New Jersey",
                    "Florida",
                    "Georgia",
                    "South Carolina",
                    "North Carolina",
                    "Virginia",
                    "Maryland",
                    "Delaware",
                    "Connecticut",
                    "Rhode Island",
                    "Massachusetts",
                    "New Hampshire",
                    "Maine");

    public static final List<String> westCoastStates =
            Arrays.asList("California",
                    "Washington",
                    "Oregon",
                    "Alaska");

    public static final String EAST_COAST = "east-coast";
    public static final String WEST_COAST = "west-coast";

    public static final String DELIMETER = ",";

}
