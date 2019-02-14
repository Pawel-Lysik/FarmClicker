import utils.*;

import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import javax.servlet.*;
import javax.servlet.http.*;
public class Main extends HttpServlet {
    Random rand = new Random();
    private static int id=1;
    private HashMap<Integer, AccountState> accounts = new HashMap<>();
    private HashSet<Integer> keys = new HashSet<>(), delKeys = new HashSet<>();
    private AccountState current;
    private void newAccount() {
        accounts.put(id, new AccountState(id));
        keys.add(id);
        current=accounts.get(id);
        id++;
    }
    private void checkOld() {
        long now = Date.from(Instant.now()).getTime();
        for(Integer key : keys)
            if (now - accounts.get(key).lastUsed > 120000)
                delKeys.add(key);
        for(Integer key : delKeys) {
            keys.remove(key);
            accounts.remove(key);
        }
    }
    private void checkNull(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        if(current==null)
            request.getRequestDispatcher("sessionExpired.jsp").forward(request, response);
    }
    private void attack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        int wolves = Math.abs(rand.nextInt())%(current.conf.day/20+2);
        int foxes =  Math.abs(rand.nextInt())%(current.conf.day/10+2)+1;
        request.setAttribute("wolves", wolves);
        request.setAttribute("foxes", foxes);
        ArrayList<Integer> animals = new ArrayList<>();
        for(Animal animal : current.animals)
            animals.add(animal.count);
        if(animals.get(5)>=foxes) {
            animals.set(0, 0);
            animals.set(5, 0);
        }
        else {
            current.animals.get(0).count=0;
            current.animals.get(5).count=0;
        }
        if(animals.get(6)>=wolves) {
            for(int i=1; i<5; i++)
                animals.set(i, 0);
            animals.set(6, 0);
        }
        else {
            for(int i=1; i<5; i++)
                current.animals.get(i).count=0;
            current.animals.get(6).count=0;
        }
        animals.add(foxes);
        animals.add(wolves);
        request.setAttribute("attack", animals);
        request.getRequestDispatcher("fight.jsp").forward(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkOld();
        if(request.getParameter("new") != null) newAccount();
        else current=accounts.get(Integer.parseInt(request.getParameter("id")));
        checkNull(request, response);
        current.transactions(request);

        if (request.getParameter("restart") != null) current.restart();

        request.setAttribute("state", current.conf);
        request.setAttribute("animals", current.animals);
        request.setAttribute("farms", current.farms);
        request.setAttribute("id", current.id);

        if (request.getParameter("end turn") != null) {
            if(rand.nextInt()%20==0)attack(request, response);
            current.turn();
        }
        request.setAttribute("userNumber", accounts.size());
        if (request.getParameter("guide") != null)
            request.getRequestDispatcher("guide.jsp").forward(request, response);

        request.getRequestDispatcher("farm.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}