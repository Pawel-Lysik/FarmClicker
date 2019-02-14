package utils;

public class State {
    public int budget, day, capacity, population;
    public State(int bgt, int d, int cap, int pop) {
        budget = bgt;
        day = d;
        capacity = cap;
        population = pop;
    }
    public State(State s) {
        budget = s.budget;
        day = s.day;
        capacity = s.capacity;
        population = s.population;
    }
}
