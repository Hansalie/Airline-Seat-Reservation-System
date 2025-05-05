import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private char row;
    private int seat;
    private int price;
    private Person person;

    //Creating the constructor
    public Ticket(char row, int seat, int price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public Ticket(int price, char row, int seat){
        this.price=price;
        this.row=row;
        this.seat=seat;
    }

    //Getter for row
    public char getRow(){
        return row;
    }
    //Getter for seat
    public int getSeat(){
        return seat;
    }
    //Getter for price
    public int getPrice() {
        return price;
    }
    public Person getPerson(){
        return  person;}


    //Setter for row
    public void setRow(char row){
        this.row=row;
    }
    //Setter for seat
    public void setSeat(int seat){
        this.seat=seat;
    }
    //Setter for price
    public void setPrice(int price){
        this.price=price;
    }
    public void setPerson(Person person){
        this.person=person;}

    public void printTicketInformation() {
        System.out.println("Booked person and ticket details...\n");
        System.out.println("\tSeat row - " + row);
        System.out.println("\tSeat number - " + seat);
        System.out.println("\tTicket price - " + "£"+price);
        System.out.println();
        person.printPersonInformation();
    }

    public void save(){
        try {
            FileWriter file=new FileWriter(row+""+seat+".txt");
            file.write("Seat row - " + row);
            file.write("\nSeat number - " + seat);
            file.write("\nTicket price - " + "£"+price);
            file.write("\n");
            file.write("\nName - " + person.getName());
            file.write("\nSurname - " + person.getSurname());
            file.write("\nEmail Address - " + person.getEmail());
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
