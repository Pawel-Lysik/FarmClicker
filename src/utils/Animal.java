package utils;
public class Animal extends Resource{
    public Animal(String n, int c, int v) {
        super(n, c, v);
    }
    public void buy(State state) {
        if(state.capacity > state.population && state.budget > cost)
            super.buy(state);
    }
}