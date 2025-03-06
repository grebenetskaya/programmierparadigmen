public class Constants {
    /** Lifetime of construction expectancy in years for different building types. */
    public static final int LIFETIME_EXP_M = 50;  // for MinimalBuilding
    public static final int LIFETIME_EXP_E = 50;  // for EcoBuilding
    public static final int LIFETIME_EXP_HQ = 100; // for HighQualityBuilding


    /** Renovation period in years for different building types. */
    public static final int RENOVATION_PERIOD_M = 20; // for MinimalBuilding
    public static final int RENOVATION_PERIOD_E = 20; // for EcoBuilding
    public static final int RENOVATION_PERIOD_HQ = 25; // for HighQualityBuilding

    /** Critical damage threshold in units, applicable to all building types. */
    public static final int CRITICAL_DAMAGE = 100;  // однакове для всіх типів будівель
    /** Standard deviation in units for renovation intervals. */
    public static final int STANDARD_DEVIATION = 5; // однакове для всіх типів будівель

    /** Increase in CO2 emissions per year per renovated unit, in units of CO2. */
    public static final int INCREASE_CO2_PER_UNIT_PER_YEAR_RENOVATION = 3;
    /** Increase in damage per renovation in units of damage. */
    public static final int INCREASE_DAMAGE_PER_RENOVATION = 20;


    /** Number of residential units in a building. */
    //public static final int NUMBER_OF_UNITS = 25;

    /** Number of simulation runs. */
    public static final int NUMBER_OF_RUNS_OF_SIMULATION = 10;

    /** Minimum and maximum number of residents per residential unit. */
    public static final int MIN_NUMBER_OF_RESIDENTS_PER_UNITS = 1;
    public static final int MAX_NUMBER_OF_RESIDENTS_PER_UNITS = 3;

    /** Coefficient for reducing satisfaction during renovations. */
    public static final double COEFF_FOR_SATISFACTION_FUNCTION_BY_RENOVATION = 0.01;



    /** Probability of a catastrophe occurring, as a percentage. */
    public static final double PROBABILITY_OF_CATASTROPHE = 0.3;

    /** Coefficients for average financial expenditure, CO2 emissions, and waste amounts. */
    public static final double COEFF_avFinancialExpenditureGeneral = 0.0006;
    public static final double COEFF_avCO2Emission = -0.012;
    public static final double COEFF_avWasteAmount = -0.0008;

    /** A small constant used for comparison in calculations to avoid precision issues. */
    public static final double EPSILON = 0.00000001;

    /** Location factors affecting cost or impact: city, suburban area, and village multipliers. */
    public static final double CITY = 1.0;
    public static final double SUBURBAN_AREA = 1.25;
    public static final double VILLAGE = 2.0;


}


