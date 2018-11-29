package src;

import src.events.Event;
import src.users.Customer;
import src.users.Employee;
import src.users.Partner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Database {

    public ArrayList<Event> events = readEventsFile();
    public ArrayList<Employee> employees = readEmployeesFile();
    public ArrayList<Partner> partners = readPartnersFile();
    public ArrayList<Customer> customers = readCustomersFile();

    // constructor
    public Database() {}


    /**
     * Searches through arrayList of Employees (database) for
     * a particular employee
     * @param username (String) - employee name to find
     * @return employee (Object)
     */
    public Employee getEmployee(String username) {

        // loop over employees
        for (Employee employee: employees) {
            if (employee.getName().equals(username)) {
                return employee;
            }
        }

        // user not found
        return null;
    }



    /**
     * Readers
     * Saves the data of the ArrayLists to the .csv files
     */
    public ArrayList<Event> readEventsFile() {
        ArrayList<Event> events = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Event Read from .csv
        File csvFile = new File("src/storage/events.csv");

        try {
            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                // Event(ID,name,type,startDate,endDate)
                //TODO: make switch cases for the different type of events
                switch (row[1]) {
                    case "1":
                        events.add(new Event(Integer.parseInt(row[0]), row[2], row[3], "Conference", row[4], row[5]));
                        break;

                    case "2":
                        events.add(new Event(Integer.parseInt(row[0]), row[2], row[3], "Trip", row[4], row[5]));
                        break;

                    case "3":
                        events.add(new Event(Integer.parseInt(row[0]), row[2], row[3], "Business Party", row[4], row[5]));
                        break;
                }

                //System.out.println(ft.format(events.get(events.size()-1).startDate));
                //System.out.println(ft.format(events.get(events.size()-1).endDate));
                //System.out.println();
                // This checks the input, not the objects
//                System.out.println("Event - code = " + row[0] +
//                        ", ID = " + row[1] +
//                        ", name = " + row[2] +
//                        ", type = " + row[3] +
//                        ", start date = " + row[4] +
//                        ", end date = " + row[5]);



            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } //end of try-catch-finally

        return events;
    }

    public ArrayList<Partner> readPartnersFile() {
        ArrayList<Partner> partners = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Partners read from .csv
        File csvFile = new File("src/storage/partners.csv");

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                // Partner(name,occupation)
                partners.add(new Partner(row[0], row[1]));


                //Checking if the read is correct -- the variables were public at checking for speed

//                System.out.print(partners.get(partners.size()-1).name);
//                System.out.print(" - ");
//                System.out.println(partners.get(partners.size()-1).occupation);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return partners;
    }

    public ArrayList<Employee> readEmployeesFile() {
        ArrayList<Employee> employees = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Partners read from .csv
        File csvFile = new File("src/storage/employees.csv");

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                // Separating the IDs -- still a String
                String[] IDs = row[1].split(";");

                // Casting the String IDs to Integer
                ArrayList<Integer> castedIDs = new ArrayList<>();
                for (int i = 0; i < IDs.length; i++) {
                    castedIDs.add(Integer.parseInt(IDs[i]));
                }

                // public Employee(ArrayList<Integer> ids, String name, String pass, String email)
                employees.add(new Employee(Integer.parseInt(row[0]), castedIDs, row[2],row[3],row[4]));


                //Checking if the read is correct -- the variables were public at checking for speed

//                System.out.print(employees.get(employees.size()-1).ID);
//                System.out.print(" - ");
//                System.out.print(employees.get(employees.size()-1).eventIDs);
//                System.out.print(" - ");
//                System.out.print(employees.get(employees.size()-1).name);
//                System.out.print(" - ");
//                System.out.print(employees.get(employees.size()-1).password);
//                System.out.print(" - ");
//                System.out.println(employees.get(employees.size()-1).email);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return employees;
    }

    public ArrayList<Customer> readCustomersFile(){
        ArrayList<Customer> customers = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Partners read from .csv
        File csvFile = new File("src/storage/customers.csv");

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                // Separating the IDs -- still a String
                String[] IDs = row[0].split(";");

                // Casting the String IDs to Integer
                ArrayList<Integer> castedIDs = new ArrayList<>();
                for (int i = 0; i < IDs.length; i++) {
                    castedIDs.add(Integer.parseInt(IDs[i]));
                }

                // public Employee(ArrayList<Integer> ids, String name, String pass, String email)
                customers.add(new Customer(castedIDs, row[1]));


                //Checking if the read is correct -- the variables were public at checking for speed

//                System.out.print(customers.get(customers.size()-1).ownEvents);
//                System.out.print(" - ");
//                System.out.println(customers.get(customers.size()-1).name);



            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return customers;
    }

    /**
     * Writers
     * Saves the data of the ArrayLists to the .csv files
     */

    public void writeEventsFile(){
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File("src/storage/events.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < events.size(); i++) {
            Event current = events.get(i);
            builder.append(current.getID()+",");
            builder.append(current.getEventType()+",");
            builder.append(current.getName()+",");
            builder.append(current.getServiceType()+",");
            builder.append(current.getOrgStartDate()+",");
            builder.append(current.getOrgEndDate()+",");
            builder.append('\n');
        }

        pw.write(builder.toString());
        pw.close();
    }

    public void writePartnersFile(){
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File("src/storage/partners.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < partners.size(); i++) {
            Partner current = partners.get(i);
            builder.append(current.getName()+",");
            builder.append(current.getOccupation());
            builder.append('\n');
        }

        pw.write(builder.toString());
        pw.close();
    }

    public void writeEmployeesFile(){
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File("src/storage/employees.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();




        for (int i = 0; i < employees.size(); i++) {
            Employee current = employees.get(i);
            // Creating a string from the array of events, with ";" between them
            ArrayList<Integer> eventIDs = current.getEventIDs();
            String stringOfEventIDs = "";
            for (int j = 0; j < eventIDs.size(); j++) {
                stringOfEventIDs = stringOfEventIDs + eventIDs.get(j).toString();
            }

            builder.append(current.getID()+",");
            builder.append(stringOfEventIDs+",");
            builder.append(current.getName()+",");
            builder.append(current.getPassword()+",");
            builder.append(current.getEmail()+",");
            builder.append('\n');
        }

        pw.write(builder.toString());
        pw.close();
    }

    public void writeCustomersFile(){
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File("src/storage/customers.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < customers.size(); i++) {
            Customer current = customers.get(i);
            // Creating a string from the array of events, with ";" between them
            ArrayList<Integer> eventIDs = current.getOwnEvents();
            String stringOfEventIDs = "";
            for (int j = 0; j < eventIDs.size(); j++) {
                stringOfEventIDs = stringOfEventIDs + eventIDs.get(j).toString();
            }
            builder.append(stringOfEventIDs + ",");
            builder.append(current.getName());
            builder.append('\n');
        }

        pw.write(builder.toString());
        pw.close();
    }




}
