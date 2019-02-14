package utils;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AccountState {
    public State conf;
    private static final State def = new State(100, 1, 10, 0);
    public ArrayList<Animal> animals;
    public ArrayList<Farm> farms;
    public ArrayList<Resource> resources;
    public Integer id;
    public long lastUsed;
    public AccountState(int idNumber) {
        id = idNumber;
        lastUsed = Date.from(Instant.now()).getTime();
        conf = new State(def);
        animals = new ArrayList<>(Arrays.asList(
                new Animal("Rabbit", 30, 20), new Animal("Sheep", 100, 60),
                new Animal("Pig", 300, 200), new Animal("Cow", 750, 500),
                new Animal("Horse", 1500, 1000), new Animal("Small dog", 100, 20),
                new Animal("Big dog", 700, 100)
        ));
        farms = new ArrayList<>(Arrays.asList(
                new Farm("Pen", 10000, 1000, 10), new Farm("Tiny farm", 20000, 2000, 25),
                new Farm("Small farm", 50000, 5000, 80), new Farm("Big farm", 150000, 15000, 200),
                new Farm("Small ranch", 600000, 100000, 1000), new Farm("Big ranch", 2000000, 400000, 5000),
                new Farm("Huge ranch", 10000000, 0, 100000)
        ));
        farms.get(0).count++;
        resources = new ArrayList<>();
        resources.addAll(animals);
        resources.addAll(farms);
    }
    public void restart() {
        for(Resource resource : resources)
            resource.count=0;
        farms.get(0).count++;
        conf=new State(def);
        lastUsed = Date.from(Instant.now()).getTime();
    }
    public void turn() {
        conf.day++;
        for(Animal animal : animals) {
            int oldcount=animal.count;
            animal.count = Math.min(animal.count / 2 + animal.count, animal.count + conf.capacity - conf.population);
            conf.population+=animal.count-oldcount;
        }
        lastUsed = Date.from(Instant.now()).getTime();
    }
    public void transactions(HttpServletRequest request) {
        for(Resource resource : resources) {
            if (request.getParameter("buy"+resource.name) != null){
                resource.buy(conf);
                lastUsed = Date.from(Instant.now()).getTime();
            }
            if (request.getParameter("sell"+resource.name) != null && resource.count>0) {
                resource.sell(conf);
                lastUsed = Date.from(Instant.now()).getTime();
            }
        }
        conf.population = 0;
        for(Animal animal : animals)
            conf.population+=animal.count;
    }
}
