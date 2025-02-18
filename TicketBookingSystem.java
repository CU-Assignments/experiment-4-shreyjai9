import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private final boolean[] seats;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketBookingSystem(int totalSeats) {
        this.seats = new boolean[totalSeats];
    }

    public void bookSeat(int seatNumber, String user) {
        lock.lock();
        try {
            if (seatNumber < 0 || seatNumber >= seats.length) {
                System.out.println(user + " - Invalid seat number!");
                return;
            }
            if (!seats[seatNumber]) {
                seats[seatNumber] = true;
                System.out.println(user + " successfully booked Seat " + seatNumber);
            } else {
                System.out.println(user + " - Seat " + seatNumber + " is already booked.");
            }
        } finally {
            lock.unlock();
        }
    }
}

class VIPBooking extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;

    VIPBooking(TicketBookingSystem system, int seatNumber, String name) {
        super(name);
        this.system = system;
        this.seatNumber = seatNumber;
        setPriority(Thread.MAX_PRIORITY);
    }

    public void run() {
        system.bookSeat(seatNumber, getName());
    }
}

class RegularBooking extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;

    RegularBooking(TicketBookingSystem system, int seatNumber, String name) {
        super(name);
        this.system = system;
        this.seatNumber = seatNumber;
        setPriority(Thread.MIN_PRIORITY);
    }

    public void run() {
        system.bookSeat(seatNumber, getName());
    }
}

public class TicketBookingMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);

        Thread vip1 = new VIPBooking(system, 2, "VIP User 1");
        Thread vip2 = new VIPBooking(system, 3, "VIP User 2");
        Thread reg1 = new RegularBooking(system, 2, "Regular User 1");
        Thread reg2 = new RegularBooking(system, 3, "Regular User 2");
        Thread reg3 = new RegularBooking(system, 4, "Regular User 3");

        vip1.start();
        vip2.start();
        reg1.start();
        reg2.start();
        reg3.start();
    }
}