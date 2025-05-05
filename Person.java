public class Person {
    private String name,surname,email;

    //Creating the constructor
    public Person(String name,String surname,String email){
        this.name=name;
        this.surname=surname;
        this.email=email;
    }
    //Getter for name
    public String getName(){
        return name;
    }
    //Getter for surname
    public String getSurname(){
        return surname;
    }
    //Getter for email
    public String getEmail(){
        return email;
    }

    //Setter for name
    public void setName(String name){
        this.name=name;
    }
    //Setter for surname
    public void setSurname(String surname){
        this.surname=surname;
    }
    //Setter for email
    public void setEmail(String email){
        this.email=email;
    }

    public void printPersonInformation() {
        int i=0;
        System.out.println("\tName - " + PlaneManagement.personArray[i].getName());
        System.out.println("\tSurname - " + PlaneManagement.personArray[i].getSurname());
        System.out.println("\tEmail Address - " +   PlaneManagement.personArray[i].getEmail());

    }
}

