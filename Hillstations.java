class Hillstations {
    public void famousfood() {
        System.out.println("Each hill station has its special cuisine");
    }
    
    public void famousfor() {
        System.out.println("Hill stations are famous for their scenic beauty");
    }
}

class Manali extends Hillstations {
    @Override
    public void famousfood() {
        System.out.println("Manali: Siddu, Thukpa, and Momos");
    }
    
    @Override
    public void famousfor() {
        System.out.println("Manali: Famous for snow-capped mountains and adventure sports");
    }
}

class Shimla extends Hillstations {
    @Override
    public void famousfood() {
        System.out.println("Shimla: Chana Madra, Babru, and Chha Gosht");
    }
    
    @Override
    public void famousfor() {
        System.out.println("Shimla: Famous for colonial architecture and Mall Road");
    }
}

class Darjeeling extends Hillstations {
    @Override
    public void famousfood() {
        System.out.println("Darjeeling: Tea, Momo, and Thukpa");
    }
    
    @Override
    public void famousfor() {
        System.out.println("Darjeeling: Famous for tea gardens and toy train");
    }
}

public class Hillstations {
    public static void main(String[] args) {
        System.out.println("=== Hill Stations of India ===\n");
        
        // Parent class reference, child class object - Runtime Polymorphism
        Hillstations station;
        
        station = new Manali();
        System.out.println("--- MANALI ---");
        station.famousfood();
        station.famousfor();
        
        System.out.println();
        
        station = new Shimla();
        System.out.println("--- SHIMLA ---");
        station.famousfood();
        station.famousfor();
        
        System.out.println();
        
        station = new Darjeeling();
        System.out.println("--- DARJEELING ---");
        station.famousfood();
        station.famousfor();
    }
}
