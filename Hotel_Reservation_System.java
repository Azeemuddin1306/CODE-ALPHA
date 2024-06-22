import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private boolean isBooked;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookRoom() {
        this.isBooked = true;
    }

    public void releaseRoom() {
        this.isBooked = false;
    }
}

class Reservation {
    private String guestName;
    private Room room;

    public Reservation(String guestName, Room room) {
        this.guestName = guestName;
        this.room = room;
        room.bookRoom();
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }
}

class Hotel {
    private String name;
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    public Hotel(String name, int numberOfRooms) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        for (int i = 1; i <= numberOfRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Room> getAvailableRooms() {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isBooked()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public boolean makeReservation(String guestName, int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && !room.isBooked()) {
                reservations.add(new Reservation(guestName, room));
                return true;
            }
        }
        return false;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        System.out.println("\t\t\t☆ THE HOTEL NABIA ☆");
        System.out.println("\t\t     -----Paradise on Earth-----");
        Hotel hotel = new Hotel("our hotel, with its 10 luxurious rooms, it is a charming country hotel, located in Candeleda, Avila, 190 km Southwest of Madrid. It is the perfect place for rest and recreation.", 10);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to " + hotel.getName() + " Reservation System");
            System.out.println("\n1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("\nPlease choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    ArrayList<Room> availableRooms = hotel.getAvailableRooms();
                    if (availableRooms.isEmpty()) {
                        System.out.println("\nNo rooms available.");
                    } else {
                        System.out.println("\nAvailable rooms:");
                        for (Room room : availableRooms) {
                            System.out.println("Room Number: " + room.getRoomNumber());
                        }
                    }
                    break;
                case 2:
                    System.out.print("\nEnter guest name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("\nEnter room number to book: ");
                    int roomNumber = scanner.nextInt();
		    if (hotel.makeReservation(guestName, roomNumber)) {
                        System.out.println("\nReservation successful for " + guestName + " in room " + roomNumber);
                    } else {
                        System.out.println("\nRoom not available or invalid room number.");
                    }
                    break;
                case 3:
                    ArrayList<Reservation> reservations = hotel.getReservations();
                    if (reservations.isEmpty()) {
                        System.out.println("\nNo reservations found.");
                    } else {
                        System.out.println("\nBooking details:");
                        for (Reservation reservation : reservations) {
                            System.out.println("\nGuest Name: " + reservation.getGuestName() + ", Room Number: " + reservation.getRoom().getRoomNumber());
                        }
                    }
                    break;
                case 4:
                    System.out.println("\nThank you for using the reservation system. Have a nice stay enjoy yourself");
                    scanner.close();
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }
}