import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    static Scanner sc = new Scanner(System.in);

    //For Passengers Use
    static ArrayList<String> PassengerName = new ArrayList<>(List.of("A", "B", "C", "D"));
    static ArrayList<String> password = new ArrayList<>(List.of("1111", "2222", "3333", "4444"));
    static ArrayList<Integer> money = new ArrayList<>(List.of(1000, 5000, 8000, 9000));
    static ArrayList<Passenger> passengers = new ArrayList<>();
    static String currentPassenger;

    //Trains Purpose
    static ArrayList<Train> train = new ArrayList<>(List.of(new Train("CHENNAI EXPRESS", new String[]{"COIMBATORE", "TIRUPPUR", "ERODE", "SALEM", "ARAKKONAM", "CHENNAI CTL"}, 3)));
    static int currentTrain;
    static int seatNo;

    // Main Home page 
    private static void home()
    {
        boolean flag = true;
        while (flag) {
            System.out.println("\n<*************> Welcome To Indian Railways <*************>");
            System.out.println("""
                    1 - Admin('For Officials Only')
                    2 - Passenger('For Public')
                    3 - Exit""");
            System.out.print("Choose Your Option : ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option)
            {
                case 1:
                    admin();
                    break;
                case 2:
                    passengerPage();
                    break;
                case 3:
                    System.out.println("\tThank You For Choosing Us!");
                    flag = false;
                    break;
            }
        }

    }

    // Main Admin page
    private static void admin() {
        //Admin Login
        System.out.println("\n<****** ADMIN LOGIN ******>");
        System.out.print("User name : ");
        String adminName = sc.nextLine();
        System.out.print("Password : ");
        String adminPassword = sc.nextLine();
        if (adminName.equalsIgnoreCase("admin") && adminPassword.equalsIgnoreCase("admin")) {
            boolean flag = true;
            while (flag)
            {
                System.out.println("\n<******* ADMIN *******>");
                System.out.println("\tWelcome, Admin");
                System.out.println("""
                        1 - Manage train
                        2 - Add train
                        3 - Remove train
                        4 - Back""");
                System.out.print("Choose your option : ");
                int option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        manageTrain();
                        break;
                    case 2:
                        addTrain();
                        break;
                    case 3:
                        remove();
                        break;
                    case 4:
                        System.out.println("Thank You For Your Work ");
                        flag = false;
                        break;
                }
            }
        } else {
            System.out.println("Invalid Option!, Try again");
            admin();
        }
    }

    // --------------------------------------TRAIN  CONFIGURATIONS----------------------------------
    // Add The train
    private static void addTrain() {
        System.out.print("\nTrain name : ");
        String trainName = sc.nextLine();

        System.out.print("Number of station : ");
        int numOfStations = sc.nextInt();
        sc.nextLine();
        String[] station = new String[numOfStations];
        for (int i = 0; i < station.length; i++) {
            System.out.print("Station " + (i + 1) + " - ");
            station[i] = sc.nextLine();
        }

        System.out.print("Number of seats in The Train : ");
        int numOfSeats = sc.nextInt();

        train.add(new Train(trainName, station, numOfSeats));
        System.out.println("\t" + trainName + " Train successfully added");
    }

    // Remove the train
    private static void remove() {
        //Choose the train
        System.out.println();
        for (int i = 0; i < train.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + train.get(i).trainName + " (" + train.get(i).destination + ")");
        }
        System.out.print("\nSelect The Train : ");
        int option = (sc.nextInt() - 1);
        sc.nextLine();
        System.out.println("\t" + train.get(option).trainName + " successfully removed");
        train.remove(option);
    }


    // Manage the train
    private static void manageTrain() {
        //Choose train
        System.out.println();
        for (int i = 0; i < train.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + train.get(i).trainName + " (" + train.get(i).destination + ")");
        }
        System.out.print("\nSelect The Train : ");
        int option = (sc.nextInt() - 1);
        sc.nextLine();
        currentTrain = option;

        //Update train
        boolean flag = true;
        while (flag) {
            System.out.println("\n<****** " + train.get(currentTrain).trainName + " *******>");
            System.out.println("""
                    1 - View bookings
                    2 - View route
                    3 - Update route
                    4 - Update number of seats
                    5 - Back""");
            System.out.print("Choose your option : ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    viewBookings();
                    break;
                case 2:
                    viewRoute();
                    break;
                case 3:
                    updateRoute();
                    break;
                case 4:
                    updateSeat();
                    break;
                case 5:
                    flag = false;
                    break;
            }
        }
    }


    // View ticket bookings
    private static void viewBookings() {
        if (!passengers.isEmpty()) {
            System.out.println("*************************************************************************************************************************");
            System.out.printf("%6s %19s %24s %19s %17s %11s %15s", "S.NO", "PASSENGER NAME", "DESTINATION", "TIME", "SEAT NO", "PRICE", "STATUS");
            System.out.println();
            System.out.println("*************************************************************************************************************************");

            for (int i = 0, j = 1; i < passengers.size(); i++) {
                Passenger curPassenger = passengers.get(i);
                if (curPassenger.trainName.equals(train.get(currentTrain).trainName)) {
                    System.out.format("%4s %13s %38s %14s %12s %14s %16ss", j, curPassenger.name, (train.get(currentTrain).route[curPassenger.from - 1] + " - " + train.get(currentTrain).route[curPassenger.to - 1]), curPassenger.time, (curPassenger.seatNo + 1), ("₹" + curPassenger.ticketPrice), curPassenger.bookingStatus);
                    System.out.println();
                    j++;
                }
            }
            System.out.println("*************************************************************************************************************************");
        } else System.out.println("\tNo Passenger List Available");
    }


    // View the  train route
    private static void viewRoute() {
        System.out.println("\nTrain name : " + train.get(currentTrain).trainName);
        System.out.println("Destination : " + train.get(currentTrain).destination);
        System.out.println("Train routes : ");
        for (int i = 0; i < train.get(currentTrain).route.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + train.get(currentTrain).route[i]);
        }
    }


    // Update the train route
    private static void updateRoute() {
        System.out.print("\nNumber of stations : ");
        int numOfStation = sc.nextInt();
        sc.nextLine();
        train.get(currentTrain).route = new String[numOfStation];

        for (int i = 0; i < train.get(currentTrain).route.length; i++) {
            System.out.print("Station " + (i + 1) + " - ");
            String station = sc.nextLine();
            train.get(currentTrain).route[i] = station;
        }
        System.out.println("\tStations Successfully Updated");
    }


    // Update the train seats
    private static void updateSeat() {
        System.out.print("\nNumber of seats : ");
        int numOfSeat = sc.nextInt();
        sc.nextLine();
        train.get(currentTrain).numOfSeats = numOfSeat;
        System.out.println("\t" + "Seats successfully updated");
    }

    // ---------------------------------- PASSENGER  CONFIGURATIONS ----------------------------------
    // Passenger Main page
    private static void passengerPage() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n******** PASSENGER ********");
            System.out.println("""
                    1 - Login
                    2 - Create Account
                    3 - Back""");
            System.out.print("Choose your option : ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    System.out.println("\tThank You For Your Visit");
                    flag = false;
                    break;
            }
        }

    }


    // Login For Passenger
    private static void login() {
        System.out.println("\n******* LOGIN *******");
        System.out.print("UserName : ");
        String Name = sc.nextLine();
        System.out.print("Password : ");
        String Pass = sc.nextLine();

        if (PassengerName.contains(Name)) {
            if (password.get(PassengerName.indexOf(Name)).equals(Pass)) {
                currentPassenger = Name;
                userPage();
            } else {
                System.out.println("\tPassword Mismatch, Try Again");
                login();
            }
        } else {
            System.out.println("\tYou Don't Have Account, Create New Account");
            login();
        }
    }


    // Create Passenger account
    private static void createAccount() {
        System.out.println("\n<------ SIGNUP ------->");
        System.out.print("UserName : ");
        String newPassengerName = sc.nextLine();
        System.out.print("Password : ");
        String newUserPass = sc.nextLine();
        System.out.print("Conform password : ");
        String conformPass = sc.nextLine();
        System.out.print("Wallet balance : ");
        int walletMoney = sc.nextInt();
        sc.nextLine();

        if (!newUserPass.equals(conformPass)) {
            System.out.println("\tPassword mismatch, try again");
            createAccount();
        } else if (PassengerName.contains(newPassengerName))
            System.out.println("\tUser " + newPassengerName + " Already Exist, LOGIN");
        else {
            PassengerName.add(newPassengerName);
            password.add(conformPass);
            money.add(walletMoney);
            System.out.println("\tAccount Created Successfully, Login Into Your Account");
            login();
        }
    }


    // ---------------------------------- TICKET BOOKING PROCESS CONFIGURATIONS ----------------------------------
    // User Ticket Booking Page
    private static void userPage() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n******** Welcome Mr." + currentPassenger + " ********");
            System.out.println("""
                    1 - Book ticket
                    2 - View bookings
                    3 - Cancel ticket
                    4 - Wallet
                    5 - Back""");
            System.out.print("Choose your option : ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    if
                        (train.get(currentTrain).waitingList.size() < 3) bookTicket();
                    else
                        System.out.println("\tBooking Has Been Temporarily Closed, Come After Some Time");
                    break;
                case 2:
                    viewTicketBookings();
                    break;
                case 3:
                    cancelTicket();
                    break;
                case 4:
                    wallet();
                    break;
                case 5:
                    System.out.println("Thank You For Visit");
                    flag = false;
                    break;
            }
        }
    }


    // Book Processes
    private static void bookTicket() {
        //Choose the train
        System.out.println();
        for (int i = 0; i < train.size(); i++)
            System.out.println(" " + (i + 1) + ". " + train.get(i).trainName + " (" + train.get(i).destination + ")");
        System.out.print("\nSelect train : ");
        int option = (sc.nextInt() - 1);
        sc.nextLine();
        currentTrain = option;


        //Show train info
        System.out.println("\nTrain Name : " + train.get(currentTrain).trainName);
        System.out.println("Destination : " + train.get(currentTrain).destination);
        System.out.println("Train Stations : ");
        for (int i = 0; i < train.get(currentTrain).route.length; i++)
            System.out.println("\t" + (i + 1) + ". " + train.get(currentTrain).route[i]);


        //select station
        System.out.print("\nFROM : ");
        int from = sc.nextInt();
        System.out.print(" TO  : ");
        int to = sc.nextInt();
        sc.nextLine();
        if (from >= to) {
            System.out.println("\tChoose Stations Correctly");
            bookTicket();
            return;
        }


        //Check the bookings
        if (!train.get(currentTrain).passengerList.isEmpty())
            for (int i = 0; i < train.get(currentTrain).passengerList.size(); i++) {
                Passenger passengerPosition = train.get(currentTrain).passengerList.get(i);
                if (passengerPosition.name.equals(currentPassenger))
                    if (passengerPosition.from == from && passengerPosition.to == to) {
                        System.out.println("\tYou Have Already Booked Ticket For The Same Destination");
                        bookTicket();
                        return;
                    }
            }


        //Ticket price
        int ticketPrice = (to - from) * 100;

        //Check wallet
        int walletAmt = money.get(PassengerName.indexOf(currentPassenger));
        if (walletAmt >= ticketPrice) {
            String bookingStatus = "Filled";
            //Check seat availability

            if (fillSeats(from - 1, to, -1)) {
            money.set(PassengerName.indexOf(currentPassenger), walletAmt - ticketPrice);
                train.get(currentTrain).passengerList.add(new Passenger(currentPassenger, from, to, train.get(currentTrain).trainName, ticketPrice, (seatNo - 1), randomTime(), walletAmt, "Filled"));
                System.out.println("\tYour Booking Has Been Filled, Seat Number is " + seatNo);
            } else {
                bookingStatus = "Waiting List";
                train.get(currentTrain).waitingList.add(new Passenger(currentPassenger, from, to, train.get(currentTrain).trainName, ticketPrice, (seatNo - 1), randomTime(), walletAmt, "Waiting list"));
                System.out.println("\tCurrently No Seats Are Available, You Are in Waiting List");
            }

            //Add passenger
            passengers.add(new Passenger(currentPassenger, from, to, train.get(currentTrain).trainName, ticketPrice, (seatNo - 1), randomTime(), walletAmt, bookingStatus));
        } else System.out.println("\tInsufficient Fund in Your Wallet");
    }


    // Fill seat Status
    private static boolean fillSeats(int from, int to, int cancel) {
        int crttrain = currentTrain;
        if (cancel != -1)
            for (int i = 0; i < train.size(); i++)
                if (passengers.get(cancel).trainName.equals(train.get(i).trainName)) crttrain = i;


        for (int i = 0; i < train.get(crttrain).seat.length; i++) {
            int isFree = 0;
            for (int j = from; j < to; j++)
                if (train.get(crttrain).seat[i][j] != 0) isFree++;
            if (isFree <= 1) {
                for (int k = from; k < to; k++)
                    train.get(crttrain).seat[i][k] = 1;
                seatNo = i + 1;
                train.get(crttrain).seatManage.add(new Passenger(currentPassenger, from, to));
                return true;
            } else if (isFree == 2) for (int j = 0; j < train.get(crttrain).seatManage.size(); j++) {
                boolean isSameBoarding = false;
                for (int k = 0; k < train.get(crttrain).seatManage.size(); k++)
                    if (train.get(crttrain).seatManage.get(k).from == from) {
                        isSameBoarding = true;
                        break;
                    }

                if (!isSameBoarding && train.get(crttrain).seatManage.get(i).to == from + 1) {
                    train.get(crttrain).seat[i][from] = 0;
                    fillSeats(from, to, -1);
                    return true;
                }
            }
        }
        return false;
    }


    // View booked ticket status
    private static void viewTicketBookings() {
        int flag = 0;
        for (Passenger passenger : passengers) if (passenger.name.equals(currentPassenger)) flag++;

        if (flag != 0) {
            System.out.println("*************************************************************************************************************************");
            System.out.printf("%6s %17s %29s %19s %17s %11s %13s", "S.NO", "TRAIN NAME", "DESTINATION", "TIME", "SEAT NO", "PRICE", "STATUS");
            System.out.println();
            System.out.println("*************************************************************************************************************************");
            for (int i = 0; i < passengers.size(); i++) {
                if (passengers.get(i).name.equals(currentPassenger)) {
                    int crt_Train = 0;
                    for (int k = 0; k < train.size(); k++)
                        if (train.get(k).trainName.equals(passengers.get(i).trainName)) crt_Train = k;

                    System.out.format("%4s %20s %33s %15s %12s %14s %14s", i + 1, passengers.get(i).trainName, train.get(crt_Train).route[passengers.get(i).from - 1] + " - " + train.get(crt_Train).route[passengers.get(i).to - 1], passengers.get(i).time, passengers.get(i).seatNo + 1, ("₹" + passengers.get(i).ticketPrice), passengers.get(i).bookingStatus);
                    System.out.println();
                }
            }
            System.out.println("*************************************************************************************************************************");
        } else System.out.println("\tYou Don't Have Any Bookings");
    }


    // Cancel Booked ticket
    private static void cancelTicket() {
        if (passengers.isEmpty()) System.out.println("\tYou don't have any bookings");
        else {
            //Show bookings List
            viewTicketBookings();

            //Remove from booking lists
            System.out.print("Cancel Ticket : ");
            int cancel = (sc.nextInt() - 1);

            int crt_Train = 0;
            for (int i = 0; i < train.size(); i++)
                if (passengers.get(cancel).trainName.equals(train.get(i).trainName)) crt_Train = i;


            String nameTODelete = passengers.get(cancel).name;
            int fromTODelete = passengers.get(cancel).from;
            int toTODelete = passengers.get(cancel).to;
            int seatTODelete = passengers.get(cancel).seatNo;
            int ticketAmt = passengers.get(cancel).ticketPrice;

            //removes from passengerList
            for (Train obj : train) {
                for (int i = 0; i < obj.passengerList.size(); i++) {
                    Passenger psList = obj.passengerList.get(i);
                    if (psList.name.equals(nameTODelete) && psList.to == toTODelete && psList.from == fromTODelete)
                        obj.passengerList.remove(i);
                }
            }


            //removes from the passengers
            passengers.remove(cancel);


            //removes from the seatManage
            for (Train obj : train) {
                for (int i = 0; i < obj.seatManage.size(); i++) {
                    Passenger psList = obj.seatManage.get(i);
                    if (psList.name.equals(nameTODelete) && psList.to == toTODelete && (psList.from + 1) == fromTODelete)
                        obj.seatManage.remove(i);
                }
            }

            //Clear seat
            for (int j = fromTODelete - 1; j < toTODelete; j++)
                train.get(crt_Train).seat[seatTODelete][j] = 0;


            //Refund amount
            int userInd = PassengerName.indexOf(currentPassenger);
            money.set(userInd, money.get(userInd) + ticketAmt);
            System.out.println("\tTicket canceled, amount refunded to your wallet");


            //Clear waiting list
            if (!train.get(crt_Train).waitingList.isEmpty()) {
                for (int i = 0; i < train.get(crt_Train).waitingList.size(); i++) {
                    Passenger waitingListPassenger = train.get(crt_Train).waitingList.get(i);
                    fillSeats((waitingListPassenger.from - 1), waitingListPassenger.to, cancel);

                    money.set(PassengerName.indexOf(waitingListPassenger.name), waitingListPassenger.wallet - (waitingListPassenger.to - waitingListPassenger.from) * 100);
                    train.get(crt_Train).passengerList.add(new Passenger(waitingListPassenger.name, waitingListPassenger.from, waitingListPassenger.to, waitingListPassenger.trainName, waitingListPassenger.ticketPrice, (seatNo - 1), waitingListPassenger.time, waitingListPassenger.wallet, "Filled"));
                    //Change passenger
                    for (Passenger passenger : passengers)
                        if (passenger.name.equals(waitingListPassenger.name)) passenger.bookingStatus = "Filled";
                }
            }
        }
    }


    // time Section
    private static String randomTime() {
        String min = Integer.toString((int) ((Math.random() * 60.0) + 1));
        String hr = Integer.toString((int) ((Math.random() * 12.0) + 1));

        if (min.length() <= 1) min = "0" + min;
        if (hr.length() <= 1) hr = "0" + hr;

        return hr + ":" + min;
    }


    // wallet Section
    private static void wallet() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n******* Mr." + currentPassenger + " Wallet *******");
            System.out.println("""
                    1 - Show Balance
                    2 - Add Balance
                    3 - Back""");
            System.out.print("Choose Your Option : ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                //show balance
                case 1:
                    System.out.println("\n\tYour Wallet Balance is : ₹" + money.get(PassengerName.indexOf(currentPassenger)));
                    break;


                //add balance
                case 2:
                    System.out.print("\nEnter Amount : ");
                    int amt = sc.nextInt();
                    sc.nextLine();
                    money.set(PassengerName.indexOf(currentPassenger), money.get(PassengerName.indexOf(currentPassenger)) + amt);
                    System.out.println("\tAmount Added Successfully");
                    break;
                case 3:
                    flag = false;
                    break;
            }
        }
    }


    // Main
    public static void main(String[] args)
    {
        home();
    }
}
