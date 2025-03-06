package Aufgabe5;

public enum Criterions {
    CRITERION1("Window should be at least 3 square metres."),
    CRITERION2("Ceiling height should be at least 2.5 metres"),
    CRITERION3("The noise insulation should achieve a sound reduction of at least 35 dB."),
    CRITERION4("The room should have at least one electrical outlet per 4 square metres."),
    CRITERION5("The door width should be at least 80 centimetres."),
    CRITERION6("The room should allow for natural light for at least 50% of daylight hours."),
    CRITERION7("The room temperature should be maintainable between 18°C and 25°C."),
    CRITERION8("The floor area should be at least 9 square metres."),
    CRITERION9("The ventilation should ensure a complete air exchange every hour."),
    CRITERION10("Walls should support at least 20 kilograms per square metre.");

    private String description;


    Criterions(String description) {
        this.description = description;
    }



    public String getDescription() {
        return description;
    }
}
