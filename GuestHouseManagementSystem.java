# JavaAssignmentimport javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GuestHouseManagementSystem {
    private static final HashMap<String, Product> SingleRooms = new HashMap<>();
    private static final HashMap<String, Product> DoubleRooms = new HashMap<>();
    private static final HashMap<String, Product> VIPRooms = new HashMap<>();
    private static final HashMap<String, Product> VVIPRooms = new HashMap<>();
    private static final HashMap<String, Product> FamilyRooms = new HashMap<>();
    private static final LinkedHashMap<String, Integer> selectedProducts = new LinkedHashMap<>();
    private static final HashMap<String, String> userAccounts = new HashMap<>();
    private static JFrame mainFrame;
    private static JPanel cardPanel;
    private static String loggedInUser = null;

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,
                "Welcome to the hotel system!\nExplore our collection of rooms: Single, Double, VIP, VVIP, and Family rooms.\nWould you like to create an account or proceed as a guest?",
                "Welcome",
                JOptionPane.INFORMATION_MESSAGE);

        // Ask if the customer wants to create an account
        int choice = JOptionPane.showConfirmDialog(null, "Would you like to create an account?", "Create Account",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            // Start the account creation and login process
            createAccountAndLogin();
        } else {
            // Proceed directly to room selection
            initializeRoomSelection();
        }
    }

    private static void createAccountAndLogin() {
        // Account Registration Process
        String username = JOptionPane.showInputDialog("Enter a username:");
        String password = JOptionPane.showInputDialog("Enter a password:");

        // Store the account details (simple, no encryption)
        userAccounts.put(username, password);

        // Log in immediately after registration
        String loginUsername = JOptionPane.showInputDialog("Enter your username to login:");
        String loginPassword = JOptionPane.showInputDialog("Enter your password to login:");

        if (userAccounts.containsKey(loginUsername) && userAccounts.get(loginUsername).equals(loginPassword)) {
            loggedInUser = loginUsername;
            JOptionPane.showMessageDialog(null, "Login successful! Welcome " + loggedInUser);
            initializeRoomSelection(); // Proceed to room selection after successful login
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            createAccountAndLogin(); // If login fails, prompt again
        }
    }

    private static void initializeRoomSelection() {
        JOptionPane.showMessageDialog(null,
                "You are now in the room booking section. Choose the room category you want to explore.",
                "Room Selection", JOptionPane.INFORMATION_MESSAGE);

        // Define the path to the image folder
        String imageFolderPath = "C:\\Users\\hillarius\\Desktop\\images\\";

        // Initialize the product data
        SingleRooms.put("Room 101", new Product("Room 101", 9000, imageFolderPath + "single01.jpeg"));
        SingleRooms.put("Room 102", new Product("Room 102", 11000, imageFolderPath + "single02.jpeg"));
        SingleRooms.put("Room 103", new Product("Room 103", 10000, imageFolderPath + "single03.jpeg"));
        SingleRooms.put("Room 104", new Product("Room 104", 12000, imageFolderPath + "single04.jpeg"));

        DoubleRooms.put("Room 105", new Product("Room 105", 15000, imageFolderPath + "double01.jpeg"));
        DoubleRooms.put("Room 106", new Product("Room 106", 16000, imageFolderPath + "double02.jpeg"));
        DoubleRooms.put("Room 107", new Product("Room 107", 14000, imageFolderPath + "double03.jpeg"));
        DoubleRooms.put("Room 108", new Product("Room 108", 17000, imageFolderPath + "double04.jpeg"));

        VIPRooms.put("Room 109", new Product("Room 109", 20000, imageFolderPath + "vip01.jpeg"));
        VIPRooms.put("Room 110", new Product("Room 110", 21000, imageFolderPath + "vip02.jpeg"));
        VIPRooms.put("Room 111", new Product("Room 111", 22000, imageFolderPath + "vip03.jpeg"));
        VIPRooms.put("Room 112", new Product("Room 112", 23000, imageFolderPath + "vip04.jpeg"));

        VVIPRooms.put("Room 113", new Product("Room 113", 30000, imageFolderPath + "VVIP01.jpeg"));
        VVIPRooms.put("Room 114", new Product("Room 114", 31000, imageFolderPath + "VVIP02.jpeg"));
        VVIPRooms.put("Room 115", new Product("Room 115", 32000, imageFolderPath + "VVIP03.jpeg"));
        VVIPRooms.put("Room 116", new Product("Room 116", 33000, imageFolderPath + "VVIP04.jpeg"));

        FamilyRooms.put("Room 117", new Product("Room 117", 25000, imageFolderPath + "family01.jpeg"));
        FamilyRooms.put("Room 118", new Product("Room 118", 26000, imageFolderPath + "family02.jpeg"));
        FamilyRooms.put("Room 119", new Product("Room 119", 27000, imageFolderPath + "family03.jpeg"));
        FamilyRooms.put("Room 120", new Product("Room 120", 28000, imageFolderPath + "family04.jpeg"));

        // Create the main frame for category selection and product display
        mainFrame = new JFrame("Hotel Booking System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new CardLayout());

        cardPanel = new JPanel(new CardLayout());

        // Category Selection Panel
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(2, 2, 20, 20)); // GridLayout for category buttons

        // Add buttons for categories (SingleRooms, DoubleRooms, etc.)
        categoryPanel.add(createCategoryPanel("SingleRooms", imageFolderPath + "single05.jpeg", SingleRooms));
        categoryPanel.add(createCategoryPanel("DoubleRooms", imageFolderPath + "double05.jpeg", DoubleRooms));
        categoryPanel.add(createCategoryPanel("VIPRooms", imageFolderPath + "vip05.jpeg", VIPRooms));
        categoryPanel.add(createCategoryPanel("VVIPRooms", imageFolderPath + "VVIP04.jpeg", VVIPRooms));
        categoryPanel.add(createCategoryPanel("FamilyRooms", imageFolderPath + "family04.jpeg", FamilyRooms));

        // Add the category panel to the cardPanel
        cardPanel.add(categoryPanel, "categoryPanel");

        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);
    }

    private static JPanel createCategoryPanel(String category, String imagePath, HashMap<String, Product> products) {
        JPanel panel = new JPanel(new BorderLayout());
        JButton button = new JButton(new ImageIcon(imagePath));
        button.setToolTipText(category);
        button.addActionListener(e -> displayProducts(category, products));
        JLabel label = new JLabel(category, JLabel.CENTER);
        panel.add(button, BorderLayout.CENTER);
        panel.add(label, BorderLayout.SOUTH);
        return panel;
    }

    private static void displayProducts(String category, HashMap<String, Product> products) {
        JFrame productFrame = new JFrame(category);
        productFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        productFrame.setSize(800, 600);

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel costLabel = new JLabel("Total: Tsh 0.00", JLabel.CENTER);
        costLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));

        JButton payButton = new JButton("Make Payment");
        payButton.setEnabled(false); // Initially disabled

        for (String key : products.keySet()) {
            Product product = products.get(key);

            JPanel productContainer = new JPanel();
            productContainer.setLayout(new BorderLayout(5, 5));

            JButton productButton = new JButton(new ImageIcon(product.getImagePath()));
            productButton.setToolTipText(product.getName());
            productButton.addActionListener(e -> {
                // Allow room selection, enabling users to select multiple rooms
                int confirm = JOptionPane.showConfirmDialog(productFrame,
                        "Do you want to select " + product.getName() + "?",
                        "Select Room",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Add selected room to the list and update total cost
                    selectedProducts.put(product.getName(), 1);

                    double totalCost = selectedProducts.keySet().stream()
                            .mapToDouble(k -> {
                                Product p = products.get(k);
                                return (p != null) ? p.getPrice() : 0.0;
                            })
                            .sum();
                    costLabel.setText(String.format("Total: Tsh %.2f", totalCost));

                    // Enable the payment button
                    payButton.setEnabled(true);
                }
            });

            productContainer.add(productButton, BorderLayout.CENTER);
            productContainer.add(new JLabel(product.getName(), JLabel.CENTER), BorderLayout.SOUTH);

            productPanel.add(productContainer);
        }

        productPanel.add(costLabel);
        productPanel.add(payButton);

        // Payment process
        payButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(productFrame, "Enter the amount you're paying:");
            try {
                double paidAmount = Double.parseDouble(input);
                double totalCost = selectedProducts.keySet().stream()
                        .mapToDouble(k -> {
                            Product p = products.get(k);
                            return (p != null) ? p.getPrice() : 0.0;
                        })
                        .sum();

                if (paidAmount >= totalCost) {
                    double change = paidAmount - totalCost;

                    // Display the payment receipt
                    StringBuilder receipt = new StringBuilder();
                    receipt.append("Payment Receipt\n");
                    receipt.append("====================\n");

                    for (String roomName : selectedProducts.keySet()) {
                        Product room = products.get(roomName);
                        if (room != null) {
                            receipt.append(roomName + " - Tsh " + room.getPrice() + "\n");
                        }
                    }

                    receipt.append("====================\n");
                    receipt.append("Total: Tsh " + String.format("%.2f", totalCost) + "\n");
                    receipt.append("Amount Paid: Tsh " + String.format("%.2f", paidAmount) + "\n");
                    receipt.append("Change: Tsh " + String.format("%.2f", change) + "\n");

                    JOptionPane.showMessageDialog(productFrame, receipt.toString(), "Payment Successful", JOptionPane.INFORMATION_MESSAGE);

                    // Thank you message after successful payment
                    int nextAction = JOptionPane.showConfirmDialog(productFrame,
                            "Would you like to continue booking more rooms?",
                            "Continue Booking?", JOptionPane.YES_NO_OPTION);

                    if (nextAction == JOptionPane.YES_OPTION) {
                        // Reset the selected rooms and amount for the next booking
                        selectedProducts.clear(); // Clear selected rooms
                        costLabel.setText("Total: Tsh 0.00"); // Reset total cost
                        payButton.setEnabled(false); // Disable payment button until new selection
                        productFrame.dispose(); // Close current booking frame
                        mainFrame.setVisible(true); // Show main menu for further booking
                    } else {
                        // Exit the booking process
                        JOptionPane.showMessageDialog(productFrame, "Thank you for your booking. Have a great stay!", "Thank You", JOptionPane.INFORMATION_MESSAGE);
                        productFrame.dispose();
                    }

                } else {
                    JOptionPane.showMessageDialog(productFrame,
                            "Insufficient funds! Please enter a higher amount.",
                            "Payment Failed", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(productFrame,
                        "Invalid input! Please enter a valid amount.",
                        "Payment Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        productFrame.add(productPanel);
        productFrame.setVisible(true);
    }
}

// Product class
class Product {
    private final String name;
    private final double price;
    private final String imagePath;

    public Product(String name, double price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }
}
