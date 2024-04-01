package us.mattgreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputGathering {
    private List<Talkable> talkables;
    private Scanner scanner = new Scanner(System.in);

    public InputGathering(ArrayList<Talkable> talkables){
        this.talkables = talkables;
    }

    private void addTalkable(){
        boolean added;
        do{
            try{
                String talkableType = getTalkableType();
                Talkable talkable = createTalkable(talkableType);
                talkables.add(talkable);
                added = true;
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                added = false;
            }
        }while (!added);
    }

    public void addTalkables(){
        addTalkable();

        boolean moreTalkable = false;
        do{
            try {
                System.out.println("Would you like to add another Talkable? y/n ");
                String userSelection = scanner.nextLine();

                switch (userSelection.toLowerCase()) {
                    case "y":
                        moreTalkable = true;
                        addTalkable();
                        break;
                    case "n":
                        moreTalkable = false;
                        System.out.println("-Final Results-");
                        break;
                    default:
                        throw new IllegalArgumentException(userSelection + " is not a valid selection.");
                }
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                moreTalkable = true;
            }
        }while (moreTalkable);
    }

    public List<Talkable> getTalkables(){
        return this.talkables;
    }

    //what type of talkable do you want to create?
    private String getTalkableType(){
        System.out.println("What type of talkable would you like to create?");
        String talkableType = scanner.nextLine();
        return talkableType.trim();
    }

    private String additionalTalkableTypeRequest(){
        System.out.println("What type of talkable would you like to create?");

        String additionalTalkableType = scanner.nextLine();
        return additionalTalkableType.trim();
    }

    private Talkable createTalkable(String talkableType) throws Exception {
        switch (talkableType.toLowerCase()){
            case "cat":
                return createCat();
            case "dog":
                return createDog();
            case "teacher":
                return createTeacher();

            default:
                throw new Exception(talkableType + " is not a valid input.");
        }
    }

    private Cat createCat(){
        System.out.println("What is the cat's name? ");
        String catName = scanner.nextLine().trim();
        int miceKilled = getUserInput("How many mice has " + catName + " killed?");
        return new Cat(miceKilled, catName);
    }

    private Dog createDog() throws Exception{
        System.out.println("What is the dog's name? ");
        String dogName = scanner.nextLine().trim();
        System.out.println("Is " + dogName + " friendly? y/n");
        String friendly = scanner.nextLine().trim();
        boolean isFriendly;
        if(friendly.toLowerCase().equals("y")){
            isFriendly = true;
        } else if (friendly.toLowerCase().equals("n")) {
            isFriendly = false;
        } else{
            throw new Exception(friendly + " is not a valid input.");
        }
        return new Dog(isFriendly, dogName);
    }

    private Teacher createTeacher(){
        System.out.println("What is the teacher's name? ");
        String teacherName = scanner.nextLine().trim();
        int teacherAge = getUserInput("What is the age of " + teacherName + " ?");
        return new Teacher(teacherAge, teacherName);
    }

    private int getUserInput(String message){
        int input = -1;
        do{
            try {
                System.out.println(message);
                input = Integer.parseInt(scanner.nextLine());
            }catch(Exception e){
                input = -1;
                System.out.println(e.getMessage() + " is not a valid input.");
            }
        }while(input < 0);

        return input;
    }
}
