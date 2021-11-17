package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] cinemaRoomSeatsArray = initCinemaRoomSeatsScheme(scanner);

        int actionNumber = 0;

        do {
            printMenu();
            actionNumber = scanner.nextInt();
            System.out.println();

            switch (actionNumber) {
                case 1:
                    printCinemaRoomScheme(cinemaRoomSeatsArray);
                    break;
                case 2:
                    cinemaRoomSeatsArray = buyTicket(scanner, cinemaRoomSeatsArray);
                    break;
                case 3:
                    printStatistics(cinemaRoomSeatsArray);
                    break;
                case 0:
                    break;
            }

        } while (actionNumber != 0);
    }

    public static String[][] initCinemaRoomSeatsScheme(Scanner scanner) {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.println();

        String[][] cinemaRoomSeatsArray = new String[rows][seats];

        for (int y = 0; y < rows; y++) {

            for (int x = 0; x < seats; x++) {
                cinemaRoomSeatsArray[y][x] = "S";
            }
        }

        return cinemaRoomSeatsArray;
    }

    public static void printCinemaRoomScheme(String[][] cinemaRoomSeatsArray) {
        System.out.println("Cinema:");

        for (int i = 0; i < cinemaRoomSeatsArray[0].length + 1; i++) {

            if (i == 0) {
                System.out.print(" " + " ");
            } else {
                System.out.print(i + " ");
            }
        }

        System.out.println();

        for (int y = 0; y < cinemaRoomSeatsArray.length; y++) {

            System.out.print((y + 1) + " ");

            for (int x = 0; x < cinemaRoomSeatsArray[y].length; x++) {
                System.out.print(cinemaRoomSeatsArray[y][x] + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public static int calculateCinemaIncome(String[][] cinemaRoomSeatsArray) {
        int income = 0;

        for (int y = 0; y < cinemaRoomSeatsArray.length; y++) {
            for (int x = 0; x < cinemaRoomSeatsArray[y].length; x++) {
                income = income + calculateTicketPrice(y, cinemaRoomSeatsArray);
            }
        }

        System.out.println("Total income: $" + income);

        return income;
    }

    public static String[][] buyTicket(Scanner scanner, String[][] cinemaRoomSeatsArray) {
        boolean success = false;

        do {
            System.out.println("Enter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();

            if (row > cinemaRoomSeatsArray.length || seat > cinemaRoomSeatsArray[0].length) {
                System.out.println("Wrong input!");
                System.out.println();
            } else {
                if ("S".equals(cinemaRoomSeatsArray[row - 1][seat - 1])) {
                    cinemaRoomSeatsArray[row - 1][seat - 1] = "B";
                    System.out.println("Ticket price: $" + calculateTicketPrice((row - 1), cinemaRoomSeatsArray));
                    System.out.println();
                    success = true;
                } else {
                    System.out.println("That ticket has already been purchased!");
                    System.out.println();
                }
            }

        } while (success != true);

        return cinemaRoomSeatsArray;
    }

    public static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void printStatistics(String[][] cinemaRoomSeatsArray) {
        int numberOfPurchasedTickets = 0;
        int totalSeats = cinemaRoomSeatsArray.length * cinemaRoomSeatsArray[0].length;
        int currentIncome = 0;

        for (int y = 0; y < cinemaRoomSeatsArray.length; y++) {
            for (int x = 0; x < cinemaRoomSeatsArray[y].length; x++) {
                if ("B".equals(cinemaRoomSeatsArray[y][x])) {
                    numberOfPurchasedTickets++;
                    currentIncome = currentIncome + calculateTicketPrice(y, cinemaRoomSeatsArray);
                }
            }
        }

        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
        System.out.println("Percentage: " + String.format("%.2f", Double.valueOf(numberOfPurchasedTickets * 100.0 / totalSeats)) + "%");
        System.out.println("Current income: $" + currentIncome);
        calculateCinemaIncome(cinemaRoomSeatsArray);
        System.out.println();
    }

    public static int calculateTicketPrice(int row, String[][] cinemaRoomSeatsArray) {
        int ticketCost = 10;
        int totalSeats = cinemaRoomSeatsArray.length * cinemaRoomSeatsArray[0].length;

        if (totalSeats > 60) {
            int firstHalf = cinemaRoomSeatsArray.length / 2;

            if ((row + 1) > firstHalf) {
                ticketCost = 8;
            }
        }

        return ticketCost;
    }
}