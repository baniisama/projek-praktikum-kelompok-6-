/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/*
KELOMPOK 6
ANGGOTA:
1. FATHIR AR RABBANI
2. MUHAMMAD ADITYA RAYYAN
3. KEMAS MUHAMMAD RAVA PUTRA WIJAYA
4. MUHAMAD RABEL
5. MUHAMAD FADLY FATHONY
6. JEHAN SYEIRA ADINNIA
7. CHALISA RANIAH ESTININGTYAS
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class Product {

    private final String name;
    private final int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    String getName() {
        return name;
    }

    int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - " + Currency.format(price);
    }
}

class CartItem {

    private final Product product;
    private final int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    Product getProduct() {
        return product;
    }

    int getQuantity() {
        return quantity;
    }

    int getSubtotal() {
        return product.getPrice() * quantity;
    }
}

class ShoppingCart {

    private final List<CartItem> items = new ArrayList<>();

    void addItem(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    void clear() {
        items.clear();
    }

    List<CartItem> getItems() {
        return items;
    }

    int getTotal() {

        int total = 0;

        for (CartItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }
}

class ProductCatalog {

    private final DefaultComboBoxModel<Product> products
            = new DefaultComboBoxModel<>();

    ProductCatalog() {

        addProduct(new Product("Thai tea", 14000));
        addProduct(new Product("Nasi Goreng", 15000));
        addProduct(new Product("Mie Ayam", 12000));
        addProduct(new Product("Es Teh", 5000));
        addProduct(new Product("Kopi", 7000));
        addProduct(new Product("Roti Bakar", 10000));
    }

    void addProduct(Product product) {
        products.addElement(product);
    }

    DefaultComboBoxModel<Product> getProducts() {
        return products;
    }
}

class PromoCode {

    private final String code;
    private final int discountPercent;

    PromoCode(String code, int discountPercent) {
        this.code = code;
        this.discountPercent = discountPercent;
    }

    String getCode() {
        return code;
    }

    int getDiscountPercent() {
        return discountPercent;
    }

    int calculateDiscount(int subtotal) {
        return subtotal * discountPercent / 100;
    }

    static PromoCode findByCode(String input) {

        if (input == null || input.trim().isEmpty()) {
            return new PromoCode("", 0);
        }

        String code = input.trim().toUpperCase(Locale.ROOT);

        if (code.equals("MEMBER50")) {
            return new PromoCode("MEMBER50", 50);
        }

        if (code.equals("HEMAT67")) {
            return new PromoCode("HEMAT67", 67);
        }

        return null;
    }
}

class Currency {

    private static final NumberFormat FORMATTER =
            NumberFormat.getCurrencyInstance(
                    Locale.of("id", "ID")
            );

    static String format(int amount) {
        return FORMATTER.format(amount);
    }
}

class Receipt {

    private final ShoppingCart cart;
    private final PromoCode promoCode;
    private final int paidAmount;

    Receipt(ShoppingCart cart,
            PromoCode promoCode,
            int paidAmount) {

        this.cart = cart;
        this.promoCode = promoCode;
        this.paidAmount = paidAmount;
    }

    String generateText() {

        int subtotal = cart.getTotal();
        int discount = promoCode.calculateDiscount(subtotal);
        int total = subtotal - discount;
        int change = paidAmount - total;

        StringBuilder text = new StringBuilder();

        text.append("====================================\n");
        text.append("           STRUK BELANJA\n");
        text.append("====================================\n");

        for (CartItem item : cart.getItems()) {

            text.append(item.getProduct().getName())
                    .append(" x")
                    .append(item.getQuantity())
                    .append(" = ")
                    .append(Currency.format(item.getSubtotal()))
                    .append("\n");
        }

        text.append("------------------------------------\n");
        text.append("Subtotal  : ")
                .append(Currency.format(subtotal))
                .append("\n");

        text.append("Diskon    : ")
                .append(Currency.format(discount))
                .append("\n");

        text.append("Hemat     : Kamu hemat ")
                .append(promoCode.getDiscountPercent())
                .append("%\n");

        text.append("Total     : ")
                .append(Currency.format(total))
                .append("\n");

        text.append("Bayar     : ")
                .append(Currency.format(paidAmount))
                .append("\n");

        text.append("Kembalian : ")
                .append(Currency.format(change))
                .append("\n");

        text.append("====================================\n");
        text.append("Terima kasih telah berbelanja\n");

        return text.toString();
    }
}

/**
 *
 * @author ASUS
 */
class CashierFrame extends JFrame {

    private final ProductCatalog catalog = new ProductCatalog();
    private final ShoppingCart cart = new ShoppingCart();

    // Variables declaration - do not modify
    private JComboBox<Product> jComboBox1;
    private JSpinner jSpinner1;

    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;

    private JTable jTable1;
    private JScrollPane jScrollPane1;

    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JLabel jLabel10;
    private JLabel jLabel17;

    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;

    private DefaultTableModel tableModel;
    // End of variables declaration

    public CashierFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor
     * to initialize the form.
     * WARNING: Do NOT modify this code.
     * The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        JPanel mainPanel = new JPanel();

        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel jLabel6 = new JLabel();

        JLabel jLabel11 = new JLabel();
        JLabel jLabel12 = new JLabel();
        JLabel jLabel13 = new JLabel();
        JLabel jLabel14 = new JLabel();
        JLabel jLabel15 = new JLabel();
        JLabel jLabel16 = new JLabel();

        jComboBox1 = new JComboBox<>(catalog.getProducts());

        jSpinner1 = new JSpinner(
                new SpinnerNumberModel(1, 1, 100, 1)
        );

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();

        jLabel7 = new JLabel(Currency.format(0));
        jLabel8 = new JLabel(Currency.format(0));
        jLabel9 = new JLabel(Currency.format(0));
        jLabel10 = new JLabel(Currency.format(0));
        jLabel17 = new JLabel("Kamu hemat 0%");

        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jButton6 = new JButton();

        tableModel = new DefaultTableModel(
                new Object[]{
                        "Produk",
                        "Harga",
                        "Jumlah",
                        "Subtotal"
                },
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jTable1 = new JTable(tableModel);
        jScrollPane1 = new JScrollPane(jTable1);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Program Kasir Kelompok 6");

        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setText("PROGRAM KASIR KELOMPOK 6");

        jLabel2.setText("Pilih Produk");
        jLabel3.setText("Jumlah");
        jLabel4.setText("Item Baru");
        jLabel5.setText("Harga");
        jLabel6.setText("Kode Promo");

        jLabel11.setText("Subtotal");
        jLabel12.setText("Diskon");
        jLabel13.setText("Total Akhir");
        jLabel14.setText("Uang Bayar");
        jLabel15.setText("Kembalian");

        jButton1.setText("Tambah");
        jButton2.setText("Tambah Item Baru");
        jButton3.setText("Terapkan Promo");
        jButton4.setText("Hitung Kembalian");
        jButton5.setText("Reset");
        jButton6.setText("Export Struk TXT");

        jTable1.setRowHeight(25);
        jTable1.setEnabled(false);

        jLabel7.setFont(new Font("Segoe UI", Font.BOLD, 16));
        jLabel8.setFont(new Font("Segoe UI", Font.BOLD, 16));
        jLabel9.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel10.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel17.setFont(new Font("Segoe UI", Font.BOLD, 16));
        jLabel17.setForeground(new Color(0, 128, 0));

        jButton1.addActionListener(evt -> addSelectedProduct());

        jButton2.addActionListener(evt -> addCustomProduct());

        jButton3.addActionListener(evt -> updateTotals());

        jButton4.addActionListener(evt -> calculateChange());

        jButton5.addActionListener(evt -> resetTransaction());

        jButton6.addActionListener(evt -> exportReceipt());

        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        .addComponent(jLabel1)

                        .addGroup(layout.createSequentialGroup()

                                .addGroup(layout.createParallelGroup()
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel4))

                                .addGap(10)

                                .addGroup(layout.createParallelGroup()
                                        .addComponent(jComboBox1)
                                        .addComponent(jTextField1))

                                .addGap(10)

                                .addGroup(layout.createParallelGroup()
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel5))

                                .addGap(10)

                                .addGroup(layout.createParallelGroup()
                                        .addComponent(jSpinner1)
                                        .addComponent(jTextField2))

                                .addGap(10)

                                .addGroup(layout.createParallelGroup()
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                        )

                        .addComponent(jScrollPane1)

                        .addGroup(layout.createSequentialGroup()

                                .addComponent(jLabel6)

                                .addGap(15)

                                .addComponent(jTextField3)

                                .addGap(10)

                                .addComponent(jButton3)
                        )

                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(20)
                                .addComponent(jLabel7))

                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(20)
                                .addComponent(jLabel8))

                        .addComponent(jLabel17)

                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(20)
                                .addComponent(jLabel9))

                        .addGroup(layout.createSequentialGroup()

                                .addComponent(jLabel14)

                                .addGap(20)

                                .addComponent(jTextField4)

                                .addGap(10)

                                .addComponent(jButton4)

                                .addGap(10)

                                .addComponent(jButton5)
                        )

                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(20)
                                .addComponent(jLabel10))

                        .addComponent(jButton6,
                                GroupLayout.Alignment.TRAILING)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()

                        .addComponent(jLabel1)

                        .addGap(20)

                        .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)

                                .addComponent(jLabel2)
                                .addComponent(jComboBox1)
                                .addComponent(jLabel3)
                                .addComponent(jSpinner1)
                                .addComponent(jButton1)
                        )

                        .addGap(10)

                        .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)

                                .addComponent(jLabel4)
                                .addComponent(jTextField1)
                                .addComponent(jLabel5)
                                .addComponent(jTextField2)
                                .addComponent(jButton2)
                        )

                        .addGap(20)

                        .addComponent(jScrollPane1)

                        .addGap(20)

                        .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)

                                .addComponent(jLabel6)
                                .addComponent(jTextField3)
                                .addComponent(jButton3)
                        )

                        .addGap(15)

                        .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)

                                .addComponent(jLabel11)
                                .addComponent(jLabel7)
                        )

                        .addGap(10)

                        .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)

                                .addComponent(jLabel12)
                                .addComponent(jLabel8)
                        )

                        .addGap(10)

                        .addComponent(jLabel17)

                        .addGap(10)

                        .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)

                                .addComponent(jLabel13)
                                .addComponent(jLabel9)
                        )

                        .addGap(20)

                        .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)

                                .addComponent(jLabel14)
                                .addComponent(jTextField4)
                                .addComponent(jButton4)
                                .addComponent(jButton5)
                        )

                        .addGap(20)

                        .addGroup(layout.createParallelGroup(
                                        GroupLayout.Alignment.BASELINE)

                                .addComponent(jLabel15)
                                .addComponent(jLabel10)
                        )

                        .addGap(20)

                        .addComponent(jButton6)
        );

        setContentPane(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }
    // </editor-fold>

    private void addSelectedProduct() {

        Product product =
                (Product) jComboBox1.getSelectedItem();

        int quantity =
                (Integer) jSpinner1.getValue();

        if (product == null) {
            return;
        }

        cart.addItem(product, quantity);

        refreshTable();
    }

    private void addCustomProduct() {

        String name = jTextField1.getText().trim();
        String priceText = jTextField2.getText().trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nama item tidak boleh kosong"
            );

            return;
        }

        try {

            int price = Integer.parseInt(priceText);

            Product product =
                    new Product(name, price);

            catalog.addProduct(product);

            jComboBox1.setSelectedItem(product);

            jTextField1.setText("");
            jTextField2.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    "Item berhasil ditambahkan"
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Masukkan harga valid"
            );
        }
    }

    private void refreshTable() {

        tableModel.setRowCount(0);

        for (CartItem item : cart.getItems()) {

            tableModel.addRow(new Object[]{
                    item.getProduct().getName(),
                    Currency.format(
                            item.getProduct().getPrice()
                    ),
                    item.getQuantity(),
                    Currency.format(
                            item.getSubtotal()
                    )
            });
        }

        updateTotals();
    }

    private void updateTotals() {

        int subtotal = cart.getTotal();

        PromoCode promoCode =
                PromoCode.findByCode(
                        jTextField3.getText()
                );

        int discount = promoCode == null
                ? 0
                : promoCode.calculateDiscount(subtotal);

        int total = subtotal - discount;

        jLabel7.setText(
                Currency.format(subtotal)
        );

        jLabel8.setText(
                Currency.format(discount)
        );

        jLabel17.setText(
                "Kamu hemat " + (promoCode == null ? 0 : promoCode.getDiscountPercent()) + "%"
        );

        jLabel9.setText(
                Currency.format(total)
        );
    }

    private void calculateChange() {

        try {

            int paid = Integer.parseInt(
                    jTextField4.getText().trim()
            );

            int subtotal = cart.getTotal();

            PromoCode promoCode =
                    PromoCode.findByCode(
                            jTextField3.getText()
                    );

            int discount = promoCode == null
                    ? 0
                    : promoCode.calculateDiscount(subtotal);

            int total = subtotal - discount;

            int change = paid - total;

            jLabel10.setText(
                    Currency.format(change)
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Masukkan uang bayar"
            );
        }
    }

    private void exportReceipt() {

        try {

            int paid = Integer.parseInt(
                    jTextField4.getText().trim()
            );

            PromoCode promoCode =
                    PromoCode.findByCode(
                            jTextField3.getText()
                    );

            if (promoCode == null) {
                promoCode = new PromoCode("", 0);
            }

            Receipt receipt =
                    new Receipt(
                            cart,
                            promoCode,
                            paid
                    );

            JFileChooser chooser =
                    new JFileChooser();

            chooser.setSelectedFile(
                    new File("struk.txt")
            );

            int result =
                    chooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                File file =
                        chooser.getSelectedFile();

                try (FileWriter writer =
                             new FileWriter(file)) {

                    writer.write(
                            receipt.generateText()
                    );

                    JOptionPane.showMessageDialog(
                            this,
                            "Struk berhasil disimpan"
                    );
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Gagal export struk"
            );
        }
    }

    private void resetTransaction() {

        cart.clear();

        tableModel.setRowCount(0);

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");

        jLabel7.setText(Currency.format(0));
        jLabel8.setText(Currency.format(0));
        jLabel9.setText(Currency.format(0));
        jLabel10.setText(Currency.format(0));
        jLabel17.setText("Kamu hemat 0%");

        jSpinner1.setValue(1);
    }
}

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new CashierFrame().setVisible(true);
        });
    }
}
