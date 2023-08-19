import java.util.*;

interface RoomManagement {
    boolean isAvailable();
    void addRoom();
    void checkoutRoom();
}

class Room {
    protected int roomNumber;
    protected double price;
    protected boolean available;

    public Room(int roomNumber, double price) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void bookRoom() {
        if (available) {
            available = false;
            System.out.println("Room " + roomNumber + " has been booked.");
        } else {
            System.out.println("Room " + roomNumber + " is not available.");
        }
    }

    public void checkoutRoom() {
        if (!available) {
            available = true;
            System.out.println("Room " + roomNumber + " has been checked out.");
            System.out.println("Bill:-"+price);
        } else {
            System.out.println("Room " + roomNumber + " is already available.");
        }
    }
}

class SingleRoom extends Room {
    public SingleRoom(int roomNumber, double price) {
        super(roomNumber, price);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom(int roomNumber, double price) {
        super(roomNumber, price);
    }
}

class InvalidRoomNumberException extends Exception {
    public InvalidRoomNumberException(String message) {
        super(message);
    }
}

class InvalidRoomPriceException extends Exception {
    public InvalidRoomPriceException(String message) {
        super(message);
    }
}

class RoomNotAvailableException extends Exception {
    public RoomNotAvailableException(String message) {
        super(message);
    }
}

class Hotell {
    private ArrayList<Room> rooms;

    Hotell() {
        rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Room findRoom(int roomNumber) throws InvalidRoomNumberException {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        throw new InvalidRoomNumberException("Room " + roomNumber + " not found.");
    }

    public void bookRoom(int roomNumber) throws InvalidRoomNumberException, RoomNotAvailableException {
        Room room = findRoom(roomNumber);
        if (room.isAvailable()) {
            room.bookRoom();
        } else {
            throw new RoomNotAvailableException("Room " + roomNumber + " is not available for booking.");
        }
    }

    public void checkoutRoom(int roomNumber) throws InvalidRoomNumberException {
        Room room = findRoom(roomNumber);
        room.checkoutRoom();
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room.getRoomNumber());
            }
        }
    }

    public void displayAllRooms() {
        System.out.println("All Rooms:");
        for (Room room : rooms) {
            System.out.println(room.getRoomNumber());
        }
    }

    public void displayOccupiedRooms() {
        System.out.println("Occupied Rooms:");
        for (Room room : rooms) {
            if (!room.isAvailable()) {
                System.out.println(room.getRoomNumber());
            }
        }
    }
}

public class HotelManagementSystem {
    public static void main(String[]args) {
        Hotell hotel = new Hotell();
        hotel.addRoom(new SingleRoom(101, 100.0));
        hotel.addRoom(new SingleRoom(102, 120.0));
        hotel.addRoom(new DoubleRoom(201, 150.0));
        hotel.addRoom(new DoubleRoom(202, 180.0));
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nHotel Management System Menu:");
            System.out.println("1. Book a room");
            System.out.println("2. Checkout from a room");
            System.out.println("3. Display available rooms");
            System.out.println("4. Display occupied rooms");
            System.out.println("5. Display all rooms");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    try {
                        hotel.bookRoom(roomNumber);
                    } catch (InvalidRoomNumberException | RoomNotAvailableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    roomNumber = scanner.nextInt();
                    try {
                        hotel.checkoutRoom(roomNumber);
                    } catch (InvalidRoomNumberException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    hotel.displayAvailableRooms();
                    break;
                case 4:
                    hotel.displayOccupiedRooms();
                    break;
                case 5:
                    hotel.displayAllRooms();
                    break;
                case 6:
                    System.out.println("Thank you for using the Hotel Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);
        scanner.close();
    }
}