package src.events;

import src.users.Employee;

public class BusinessParty extends Event implements EventPricing {
    private double basePartyPrice = 30000;

    /**
     * Constructor for creating a whole new event by employee
     * */
    public BusinessParty(int ID,  String eventType, String name, String serviceType, Employee employeeResponsible, int nbOfHoursNeeded, String specs) {
        super(ID, eventType, name, serviceType, employeeResponsible, nbOfHoursNeeded, specs);
    }

    /**
     * Constructor for creating a new event with reading from our database
     * */
    public BusinessParty(int ID, String eventType, String name, String serviceType, String startDate, String endDate, int nbOfHoursNeeded, String specs){
        super(ID, eventType, name, serviceType, startDate, endDate, nbOfHoursNeeded, specs);
    }

    //Setters

    /**
     * set base party price
     * @param price (double) - base price for the party
     */
    public void setBaseTripPrice(double price) {this.basePartyPrice = price;}

    //getters

    /**
     * returns base party price
     * @return basePartyPrice (double) - base price for the party
     */
    public double getBaseTripPrice() { return this.basePartyPrice; }


    /**
     * Calculates the event price that the customer needs to pay in order for the event to be organized
     * @return price (double) - price of the whole event*/
    @Override
    public double calculateEventPrices() {
        int nbOfSpecs = super.specs.size();
        return (basePartyPrice + getNbOfHoursNeeded() * Employee.getHourlyWage() + nbOfSpecs * 400);
    }
}
