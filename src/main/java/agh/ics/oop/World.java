package agh.ics.oop;

public class World {
    public static void main(String[] args) {

        Animal pet = new Animal();

        AnimalMovement(args,pet);

        System.out.println(pet);
    }

    public static void AnimalMovement(String[] commands, Animal animal)
    {
        MoveDirection [] md = (commands!=null)? OptionsParser.parse(commands) : new MoveDirection[0];

        for(MoveDirection d : md)
            animal.move(d);
    }

}