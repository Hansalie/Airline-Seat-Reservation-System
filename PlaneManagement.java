import java.util.*;
public class PlaneManagement {

    // Declare class-level variables
    char[][] seatArray=new char[4][];
    static boolean boolean_value=true;
    char row_letter;
    int row_number, seat_number;
    static int user_option;
    String name,surname,email;
    int ticket_price;
    Ticket ticket;
    Ticket ticket1;
    Ticket[] ticketArray=new Ticket[52];
    static Person[] personArray=new Person[52];
    Person personDetail;
    int person_index=0;

    int i=0;

    // Method to initialize the seating array
    public void initializeSeats() {
        seatArray[0]=new char[14];
        seatArray[1]=new char[12];
        seatArray[2]=new char[12];
        seatArray[3]=new char[14];

        for (int i=0;i<seatArray.length;i++) {
            for (int j=0;j<seatArray[i].length;j++) {
                seatArray[i][j]='O';
            }
        }
    }

    // Method to determine ticket price based on seat number
    public void prisingTickets(){
        if(seat_number<=5){
            ticket_price=200;}
        else if (seat_number<=9) {
            ticket_price=150;}
        else{
            ticket_price=180;}
    }

    // Method to prompt user for seat details
    public void enteringSeatDetails() {
        // Variable declarations
        boolean boolean_row_letter=false, boolean_seat_number=false;
        String letter;
        // Scanner for user input
        Scanner input = new Scanner(System.in);
        System.out.println();
        //Until user enters valid seat number
        while(!boolean_seat_number) {
            try {
                //Until user enters valid row letter
                while (!boolean_row_letter) {
                    System.out.print("Enter the row letter (A-D): ");
                    letter = input.nextLine().toUpperCase(); // Asking the user to enter a row letter
                    if (letter.length() != 1) {
                        throw new InputMismatchException();  //If the user enter any character followed by whitespace
                    }
                    row_letter = letter.charAt(0); //Converting row_letter to char

                    // Switch case to convert the row letter to a number
                    switch (row_letter) {
                        case 'A':
                            row_number = 0;
                            break;
                        case 'B':
                            row_number = 1;
                            break;
                        case 'C':
                            row_number = 2;
                            break;
                        case 'D':
                            row_number = 3;
                            break;
                        default:
                            throw new InputMismatchException();  //If the user enter a not matching character
                    }
                    boolean_row_letter = true;  //exit from the loop
                }
                // Asking the user to enter a seat number
                System.out.print("Enter the seat number (A&D:1-14 , B&C:1-12): ");

                //Checking whether a character is left in the buffer
                if (input.hasNextInt()) {
                    seat_number = input.nextInt();

                    //Checking if the seat_number is in the range
                    if ((row_letter == ('A') || row_letter == ('D')) && (seat_number < 0 || seat_number > 14)) {
                        throw new ArrayIndexOutOfBoundsException();
                    } else if ((row_letter == ('B') || row_letter == ('C')) && (seat_number < 0 || seat_number > 12)) {
                        throw new ArrayIndexOutOfBoundsException();
                    } else {
                        boolean_seat_number = true; //exit from the loop
                    }

                } else {
                    input.nextLine();  //Clear the buffer
                    throw new InputMismatchException();}

            } catch (InputMismatchException e) {
                System.out.println("\n Invalid, please enter valid seat details...\n");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\n Invalid, seat number is not in the range...\n");}
        }
        System.out.println();
        prisingTickets();
    }

    // Method to purchase a seat
    public void buy_seat() {
        enteringSeatDetails();
        // Checking whether the seat is available or not
        if (seatArray[row_number][seat_number - 1] == 'O') {
            seatArray[row_number][seat_number - 1] = 'X';  // Booking the seat

            System.out.println("\nPlease enter your details to book the seat...\n");
            Scanner input = new Scanner(System.in);

            // Asking the user to enter details
            System.out.print("Enter your name: ");
            name = input.next();
            System.out.print("Enter your surname: ");
            surname = input.next();
            System.out.print("Enter your email address: ");
            email = input.next();

            System.out.println("\n\tThe " + row_letter + seat_number + " seat booked successfully...");

            Person person = new Person(name, surname, email); // Creating a new Person object
            ticket = new Ticket(row_letter, seat_number, ticket_price, person); // Creating a new Ticket object with the Person object
            ticket1 = new Ticket(ticket_price, row_letter, seat_number);
            ticketArray[i] = ticket;
            i++;
            personDetail = person; // Storing the person details in personDetail
            personArray[person_index] = person; // Adding the person to personArray
            person_index++;
            ticket.save(); // Calling the save method in Ticket class
        } else {
            System.out.println("\tThe " + row_letter + seat_number + " seat is already booked!");
        }
    }

    // Method to cancel a seat
    public void cancel_seat() {
        enteringSeatDetails();
        // Checking if the seat is booked or not
        if (seatArray[row_number][seat_number - 1] == 'X') {
            seatArray[row_number][seat_number - 1] = 'O'; // Cancelling the seat
            System.out.println("\tThe " + row_letter + seat_number + " seat cancelled...");

            // Creating a new array to store tickets without the cancelled one
            Ticket[] newTicketArray = new Ticket[52];
            int newIndex = 0;
            // Iterate through the existing ticket array
            for (int b = 0; b < ticketArray.length; b++) {
                // Check if the current ticket is not null and does not match the cancelled seat
                if (ticketArray[b] != null && (ticketArray[b].getRow() != row_letter || ticketArray[b].getSeat() != seat_number)) {
                    newTicketArray[newIndex] = ticketArray[b]; // Copy the ticket to the new array
                    newIndex++; // Move to the next index in the new array
                }
            }
            ticketArray = newTicketArray; // Update the ticket array to the new one without the cancelled ticket

        } else {
            System.out.println("\tThe " + row_letter + seat_number + " seat is already available");
        }
    }

    // Method to find the first available seat
    public void find_first_available() {
        for (int i = 0; i < seatArray.length; i++) {
            char[] row = seatArray[i];
            char row_letter = (char) ('A' + i); // Calculate row letter directly from index

            for (int j = 0; j < row.length; j++) {
                char seat = row[j];

                if (seat == 'O') { // Compare with character literal 'O'
                    int seat_no = j + 1; // Calculate seat number
                    System.out.println("\tThe first available seat is " + row_letter + seat_no + "...");
                    return; // Exit the method after finding the first available seat
                }
            }
        }
        System.out.println("\tNo available seats found...");
    }

    // Method to display the seating plan
    public void show_seating_plan() {
        System.out.println();
        // Iterate through each row in the seating array
        for (char[] row : seatArray) {
            // Iterate through each seat in the row
            for (char seat : row) {
                // Print the seat status (O for available, X for booked)
                System.out.print("\t" + seat + " ");
            }
            System.out.println(); // Move to the next row after printing all seats in a row
        }
    }

    // Method to print ticket information and total sales
    public void print_tickets_info() {
        int total_amount = 0;
        // Check if any tickets have been sold
        if (ticketArray[0] == null) {
            System.out.println("\tAny ticket has not been sold...");
        } else {
            System.out.println("Sold ticket details...\n");
            // Iterate through the ticket array
            for (int a = 0; ticketArray[a] != null; a++) {
                // Print ticket details (seat and price)
                System.out.print("\t\t" + ticketArray[a].getRow() + ticketArray[a].getSeat());
                System.out.println("\t\t £" + ticketArray[a].getPrice());
                total_amount += ticketArray[a].getPrice();  // Calculate total ticket price
            }
            System.out.println("\n\tTotal price of the tickets: £" + total_amount);
        }
    }

    // Method to search for a ticket
    public void search_ticket() {
        // Prompt user for seat details
        enteringSeatDetails();

        boolean hasTicket = false;

        // Iterate through each ticket in the tickets array
        for (Ticket ticket : ticketArray) {
            // Check if the ticket matches the entered seat details
            if (ticket != null && ticket.getRow() == row_letter && ticket.getSeat() == seat_number) {
                hasTicket = true;
                ticket.printTicketInformation();
                break;
            }
        }
        // If ticket not found, display message
        if (!hasTicket) {
            System.out.println("\tThis seat is available...");
        }
    }


    public static void main(String[] args) {
        System.out.println("\n\tWelcome to the Plane Management application");
        //Creating an object for the PlaneManagement class
        PlaneManagement obj1 = new PlaneManagement();
        obj1.initializeSeats(); //Calling the initializeSeats method
        // Displaying the user menu
        do {
            System.out.println("\n");
            System.out.println("""
                    **************************************************
                    *                 MENU OPTIONS                   *
                    **************************************************
                                    
                           1) Buy a seat
                           2) Cancel a seat
                           3) Find first available seat
                           4) Show seating plan
                           5) Print tickets information and total sales
                           6) Search ticket
                           0) Quit""");

            //Until the user enter a valid option
            System.out.println();
            while(boolean_value) {
                try {
                    //Asking the user to enter an option
                    System.out.print("Please select an option: ");
                    Scanner input = new Scanner(System.in);
                    String string_user_option = input.nextLine();
                    if (string_user_option.length() != 1) {
                        throw new InputMismatchException();
                    } else {
                        user_option = Integer.parseInt(string_user_option);
                        if (0 > user_option || user_option > 6) {
                            System.out.println("\n Invalid, option is out of range...");
                        } else{
                            System.out.println();
                            break;}
                    }
                }catch(NumberFormatException | InputMismatchException e){
                    System.out.println("\n Number from 1-6 is required...");
                }
                System.out.println();
            }

            //Switch case to invoke methods according to the option
            switch (user_option) {
                case 1:
                    obj1.buy_seat();
                    break;
                case 2:
                    obj1.cancel_seat();
                    break;
                case 3:
                    obj1.find_first_available();
                    break;
                case 4:
                    obj1.show_seating_plan();
                    break;
                case 5:
                    obj1.print_tickets_info();
                    break;
                case 6:
                    obj1.search_ticket();
                    break;
                case 0:
                    System.exit(0);
                    break;}

        }while (user_option != 0) ;//Until user exit by entering 0
    }
}