package src;

import src.events.BusinessParty;
import src.events.Conference;
import src.events.Event;
import src.events.Trip;
import src.users.Employee;
import java.util.Random;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Application {

    // keep track of currently logged in Employee
    private static Employee currentUser;

//    private Scanner scn = new Scanner(System.in);
    private Database db = new Database();


    // constructor
    public Application() { }


    /**
     * starts application
     */
    public void run() {

        // TODO: think about that while loop
        // while (true) {

        // show starting login page
        showLogin("");

    }

    // ==========================
    // screens of the application
    // ==========================
    /**
     * prints login screen info and asks
     * for credentials: username and password
     * @param errorMsg (String) - message to be displayed under header
     * */
    private void showLogin(String errorMsg) {
        String username;
        String password;

        Screen.showLogo();
        System.out.println(errorMsg);
        System.out.println();
        // get username
        username = Helper.getString("Username: ");
        // get password
        password = Helper.getString("Password: ");
        // authenticate user
        validateUser(username, password);
    }


    private void showDashboard() {
        Screen.clearScreen();
        Screen.showLogo();
        // showing header with default date
        Screen.showHeader();
        ArrayList<Integer> eventIDsForToday = db.getEmployeeEventsForToday(currentUser.getEventIDs());
        if (eventIDsForToday.isEmpty()) {
            System.out.println("No events for today");
        }
        else {
            System.out.println("Today's events:");
            for (int i = 0; i < eventIDsForToday.size(); i++) {
                System.out.print("Event ID: " + db.events.get(i).getID() + "| ");
                System.out.print("Name: " + db.events.get(i).getName() + "| ");
                System.out.print("Event Type: " + db.events.get(i).getEventType() + "| ");
                System.out.println("Service Type: " + db.events.get(i).getServiceType() + "| ");
            }
        }
        System.out.println();
        System.out.println();

        String[] options = Screen.getOptions(currentUser.getID());
        // show menu options
        Screen.listOptions(options);
        // user inputs option number
        int selection = Helper.selectOption(options.length);
        // activate chosen option
        handleSelectedOption(options[selection]);
    }

    private void handleDateMenu(String action) {
        Screen.clearScreen();
        Screen.showLogo();
        if (action.equals("show options")) {
            // todo: those 4 calls are repeating - for making a method
            String[] options = Screen.getOptions("date options");
            // print options
            Screen.listOptions(options);
            // user inputs option number
            int selection = Helper.selectOption(options.length);

            handleSelectedOption(options[selection]);
        } else if (action.equals("date")) {
            // todo: Get it from Helper + convert and return properly converted string

            String date = Helper.getDate();
            System.out.println(date);

            // todo: show events for that date
        } else if (action.equals("period")) {
            System.out.println("=== Start Date ===");
            String startDate = Helper.getDate();

            System.out.println("=== End Date ===");
            String endDate = Helper.getDate();

            System.out.println("start date: " + startDate);
            System.out.println("end date: " + endDate);
            // todo: display events between those periods
        }

    }

    private void showSelectEventMenu(){
        String[] options = Screen.getOptions("select event options");
        // print options
        Screen.listOptions(options);
        // user inputs option number
        int selection = Helper.selectOption(options.length);
        System.out.println("selected: " + options[selection]);
    }

    // ==================================
    // ======== Option selecting ========
    // ==================================
    private void handleSelectedOption(String option) {
        switch (option) {
            case Helper.LOGOUT:
                logout();
                break;
            case Helper.SHOW_DASHBOARD:
                showDashboard();
                break;
            case Helper.SELECT_DATE:
                handleDateMenu("date");
                break;
            case Helper.SELECT_PERIOD:
                handleDateMenu("period");
                break;
            case Helper.ADD_EVENT:
                System.out.println("Showing New Event Form");
                addEvent();
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
            case Helper.CHANGE_DATE:
                handleDateMenu("show options");
                break;
            case Helper.SHOW_CUSTOMERS:
                System.out.println("Showing Customers");
                showCustomers();
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
            case Helper.SHOW_EMPLOYEES:
                System.out.println("Showing Employees");
                break;
            case Helper.SHOW_PARTNERS:
                System.out.println("Showing Partners");
                showPartners();
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
            case Helper.SELECT_EVENT:
                System.out.println("Showing my events");
                selectEvent();
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
            default:
                System.out.println("Option does not exist");
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
        }
    }

    public void selectEvent() {
        System.out.println();

        if (currentUser.getID() == 1111) {
            for (int j = 0; j < db.employees.size(); j++) {
                ArrayList<Integer> eventIDs = db.getEmployeeEventsForToday(db.employees.get(j).getEventIDs());
                System.out.println("Employee: " + db.employees.get(j).getName());
                for (int i = 0; i < eventIDs.size(); i++) {
                    System.out.print("Event ID: " + db.events.get(i).getID() + "| ");
                    System.out.print("Name: " + db.events.get(i).getName() + "| ");
                    System.out.print("Event Type: " + db.events.get(i).getEventType() + "| ");
                    System.out.println("Service Type: " + db.events.get(i).getServiceType() + "| ");
                    System.out.print("Org Start Date: " + new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(db.events.get(i).getOrgStartDate()) + "| ");
                    System.out.println("Org End Date: " + new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(db.events.get(i).getOrgEndDate()) + "| ");
                    System.out.println();
                }
            }
        }

        else {
            ArrayList<Integer> eventIDs = db.getEmployeeEventsForToday(currentUser.getEventIDs());
            for (int i = 0; i < eventIDs.size(); i++) {
                System.out.print("Event ID: " + db.events.get(i).getID() + "| ");
                System.out.print("Name: " + db.events.get(i).getName() + "| ");
                System.out.print("Event Type: " + db.events.get(i).getEventType() + "| ");
                System.out.println("Service Type: " + db.events.get(i).getServiceType() + "| ");
                System.out.print("Org Start Date: " + new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(db.events.get(i).getOrgStartDate()) + "| ");
                System.out.println("Org End Date: " + new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(db.events.get(i).getOrgEndDate()) + "| ");
                System.out.println();
            }
        }

    }

    private void showPartners() {
        System.out.println();
        for (int i = 0; i < db.partners.size(); i++) {
            System.out.print("Name: " + db.partners.get(i).getName() + "| ");
            System.out.println("Service: " + db.partners.get(i).getOccupation());
        }
    }

    private void showCustomers() {
        System.out.println();
        for (int i = 0; i < db.customers.size(); i++) {
            System.out.print("Name: " + db.customers.get(i).getName() + "| ");
            System.out.println("Event ID(s): " + db.customers.get(i).getOwnEvents());
        }
    }

    // =======================
    // Login connected methods
    // =======================
    /**
     * validates Login information to see if the user exists and if he entered the correct password
     * */
    private void validateUser (String username, String password){
        // retrieve user data from db
        Employee user = db.getEmployeeByName(username);

        // user doesn't exist
        if (user == null) {
            Screen.clearScreen();
            showLogin(Helper.USER_NOT_FOUND);
            return;
        }

        // login user if password correct
        if (user.getPassword().equals(password)) {
            login(user);
            showDashboard();
        } else {
            Screen.clearScreen();
            // authentication failed
            showLogin(Helper.PASSWORD_INCORRECT);
        }
    }

    /**
     * Save user into global attirbute currentUser, so it is easily accessed
     * @param user (Employee) - user to be logged in
     */
    private void login(Employee user) {
        currentUser = user;
    }

    /**
     * removes logged in user reference from currentUser attribute
     */
    private void logout() {
        // TODO: save information into files
        // remove user
        currentUser = null;
        // show login screen
        showLogin(Helper.LOGOUT_SUCCESS);
    }

    public static Employee getCurrentUser(){ return currentUser; }


    public static int generateRandomID(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void addEvent(){
        Employee responsibleEmployee;
        int newID = 0;
        String eventTypeString = "";
        String serviceTypeString = "";
        int nbOfHoursNeeded;

        boolean uniqueID = false;
        //check if the ID exists
        while (!uniqueID) {
            newID = generateRandomID(1000, 9999);
            int i = 0;

            while (!uniqueID) {
                if (db.events.get(i).getID() != newID)
                    uniqueID = true;
                else if (i == (db.events.size() - 1))
                    break;
            }
        }

        String name = Helper.getString("Event name: ");
        while (name.equals("")) {
            name = Helper.getString("Event name cannot be empty. Please try again.");
        }

        System.out.println("Event type: ");
        System.out.println("1 - Conference");
        System.out.println("2 - Trip");
        System.out.println("3 - Business Party");
        int eventType = Helper.selectOption(0, 3);

        System.out.println("Event service: ");
        System.out.println("1 - Consultancy");
        System.out.println("2 - Planning");
        System.out.println("3 - Full Organization");
        int serviceType = Helper.selectOption(0, 3);

        if (serviceType == 1){
            serviceTypeString = "Consultancy";
        }else if (serviceType == 2){
            serviceTypeString = "Planning";
        }else if(serviceType == 3){
            serviceTypeString = "Full Organization";
        }

        if (currentUser.getID() == 1111){
            int employeeID = Helper.getInt("Enter ID of the employee that is going to be responsible for this event: ");
            responsibleEmployee = db.getEmployeeByID(employeeID);
        }else{
            responsibleEmployee = currentUser;
        }

        nbOfHoursNeeded = Helper.getInt("Enter the number of hours needed to organize this event: ");

        if(eventType == 1){
            String officeSupplies = Helper.getString("Enter needed office supplies: ");
            eventTypeString = "Conference";
            Conference newEvent = new Conference(newID, eventTypeString, name, serviceTypeString, responsibleEmployee, nbOfHoursNeeded, officeSupplies);
            db.events.add(newEvent);
        }else if (eventType == 2){
            String transport = Helper.getString("Enter type of transportation needed for the trip: ");
            eventTypeString = "Trip";
            Trip newEvent = new Trip(newID, eventTypeString, name, serviceTypeString, responsibleEmployee, nbOfHoursNeeded, transport);
            db.events.add(newEvent);
        }else if (eventType == 3){
            String decoration = Helper.getString("Enter decoration needed for the party: ");
            eventTypeString = "Business Party";
            BusinessParty newEvent = new BusinessParty(newID, eventTypeString, name, serviceTypeString, responsibleEmployee, nbOfHoursNeeded, decoration);
            db.events.add(newEvent);
        }
        //TODO: add location, parters
        //are we going to select an existing location from a list? and the same for partners?
    }

    public void editEvent(){



    }
}
