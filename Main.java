import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int candidate1 = 0;
        int candidate2 = 0;

        HashSet<String> registeredVoters = new HashSet<>();
        HashSet<String> votedVoters = new HashSet<>();

        // Admin Login Credentials
        String adminUsername = "admin";
        String adminPassword = "1234";

        int choice;

        // Main Menu System
        do {

            // Welcome Menu Design
            System.out.println("\n================================");
            System.out.println("       ONLINE VOTING SYSTEM");
            System.out.println("================================");
            System.out.println("     Welcome to Our Project!");
            System.out.println("--------------------------------");

            System.out.println("1. Register Voter");
            System.out.println("2. Cast Vote");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");

            System.out.println("--------------------------------");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            // Option 1: Register Voter
            if (choice == 1) {

                System.out.print("Enter voter ID: ");
                String id = sc.nextLine();

                System.out.print("Enter name: ");
                String name = sc.nextLine();

                System.out.print("Enter age: ");
                int age = sc.nextInt();
                sc.nextLine();

                if (age >= 18) {

                    if (registeredVoters.add(id)) {

                        System.out.println("Registration successful!");

                    } else {

                        System.out.println("Already registered!");
                    }

                } else {

                    System.out.println(
                        "Not eligible! Age must be 18 or above."
                    );
                }

            }

            // Option 2: Cast Vote
            else if (choice == 2) {

                System.out.print("Enter voter ID: ");
                String id = sc.nextLine();

                if (!registeredVoters.contains(id)) {

                    System.out.println("Register first!");

                } else if (votedVoters.contains(id)) {

                    System.out.println("Already voted!");

                } else {

                    System.out.println("\n===== CANDIDATES =====");
                    System.out.println("1. Candidate A");
                    System.out.println("2. Candidate B");

                    System.out.print("Enter your vote: ");
                    int vote = sc.nextInt();
                    sc.nextLine();

                    if (vote == 1) {

                        candidate1++;
                        votedVoters.add(id);

                        System.out.println(
                            "Vote recorded successfully!"
                        );

                    } else if (vote == 2) {

                        candidate2++;
                        votedVoters.add(id);

                        System.out.println(
                            "Vote recorded successfully!"
                        );

                    } else {

                        System.out.println("Invalid vote!");
                    }
                }

            }

            // Option 3: Admin Login
            else if (choice == 3) {

                System.out.print("Enter Admin Username: ");
                String username = sc.nextLine();

                System.out.print("Enter Admin Password: ");
                String password = sc.nextLine();

                if (username.equals(adminUsername) &&
                    password.equals(adminPassword)) {

                    System.out.println("\n===== VOTING RESULTS =====");

                    System.out.println("Candidate A: " + candidate1);
                    System.out.println("Candidate B: " + candidate2);

                    System.out.println(
                        "Registered Voters: "
                        + registeredVoters.size()
                    );

                    System.out.println(
                        "Total Votes: "
                        + votedVoters.size()
                    );

                    if (candidate1 > candidate2) {

                        System.out.println(
                            "Winner: Candidate A"
                        );

                    } else if (candidate2 > candidate1) {

                        System.out.println(
                            "Winner: Candidate B"
                        );

                    } else {

                        System.out.println("Result: Tie!");
                    }

                    System.out.println(
                        "Admin Login Successful!"
                    );

                    saveResults(candidate1, candidate2);

                } else {

                    System.out.println(
                        "Invalid Admin Username or Password!"
                    );
                }

            }

            // Option 4: Exit
            else if (choice == 4) {

                System.out.println(
                    "Thank you for using Online Voting System!"
                );

                saveResults(candidate1, candidate2);

            }

            else {

                System.out.println(
                    "Invalid choice! Please try again."
                );
            }

        } while (choice != 4);

        sc.close();
    }

    // Save Voting Results Method
    public static void saveResults(
            int candidate1, int candidate2) {

        try {

            FileWriter writer = new FileWriter("results.txt");

            writer.write("===== VOTING RESULTS =====\n");
            writer.write("Candidate A: " + candidate1 + "\n");
            writer.write("Candidate B: " + candidate2 + "\n");

            writer.close();

            System.out.println(
                "Results saved successfully!"
            );

        } catch (IOException e) {

            System.out.println("Error saving results!");
        }
    }
}