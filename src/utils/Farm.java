package utils;
public class Farm extends Resource{
    public int capacity;
    public Farm(String n, int c, int v, int cap) {
        super(n, c, v);
        capacity=cap;
    }
    public void buy(State state) {
        if(state.budget > cost) {
            super.buy(state);
            state.capacity += capacity;
        }
    }
    public void sell(State state) {
        if(state.capacity-capacity>=state.population) {
            super.sell(state);
            state.capacity -= capacity;
        }
    }
}
