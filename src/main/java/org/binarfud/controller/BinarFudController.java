package org.binarfud.controller;

import lombok.RequiredArgsConstructor;
import org.binarfud.model.Product;
import org.binarfud.service.OrderService;
import org.binarfud.service.ProductService;
import org.binarfud.util.common.DisplayUtil;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class BinarFudController {
    private final ProductService productService;
    private final OrderService orderService;
    private final DisplayUtil displayUtil;

    public void init() {
        productService.initProduct();
    }

    public void index() {
        while (true) {
            displayUtil.printWelcomeMessage("Selamat Datang di BinarFud", "=");
            System.out.println("Silahkan Pilih Menu: ");
            productService.getAllProducts().forEach(
                    (key, value) ->
                            System.out.println(value))
            ;
            System.out.println("99. Pesan Dan Bayar");
            System.out.println("0. Keluar Aplikasi");

            int choice = displayUtil.inputChoice("=>");

            switch (choice) {
                case 0:
                    char confirm = displayUtil.inputConfirm("=>");
                    if (Character.toLowerCase(confirm) == 'y') {
                        System.out.println("Terima kasih. Selamat tinggal!");
                        return;
                    }
                    break;
                case 99:
                    if (orderService.getAllOrders().isEmpty()) {
                        System.out.println("Anda belum memesan apapun.");
                    } else {
                        displayTotalPay();
                    }
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    orderProduct(choice);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public void orderProduct(int idProduct) {
        displayUtil.printWelcomeMessage("Berapa Pesanan Anda", "=");
        Product product = productService.getProductById(idProduct);
        if (product == null) {
            System.out.println("Id produk tidak valid.");
            return;
        }

        System.out.println(product.getName() + "\t | \t" + product.getPrice());
        System.out.println("Input 0 untuk kembali ke menu utama.");
        int qty = displayUtil.inputChoice("Qty =>");
        if (qty != 0) {
            char confirm = displayUtil.inputConfirm("=>");
            if (Character.toLowerCase(confirm) == 'y')
                orderService.addOrder(product, qty);
        }
    }

    public void displayTotalPay() {
        boolean backToIndex = false;

        while (!backToIndex) {
            displayUtil.printWelcomeMessage("Konfirmasi & Pembayaran", "=");
            orderService.getAllOrders().forEach(
                    (key, value) ->
                            System.out.println(value))
            ;

            System.out.println(displayUtil.printLine("-") + "+");
            System.out.println("Total\t\t\t " + orderService.getTotalQtyOrders() + "\t\t " + orderService.getTotalPriceOrders());
            System.out.println("1. Konfirmasi Dan Bayar");
            System.out.println("2. Kembali Ke Menu Utama");
            System.out.println("3. Keluar Aplikasi");

            int choice = displayUtil.inputChoice("=>");

            switch (choice) {
                case 1:
                    printStruk();
                    System.exit(0);
                    break;
                case 2:
                    backToIndex = true;
                    break;
                case 3:
                    char confirm = displayUtil.inputConfirm("=>");
                    if (Character.toLowerCase(confirm) == 'y') {
                        System.out.println("Terima kasih. Selamat tinggal!");
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public void printStruk() {
        try (PrintWriter writer = new PrintWriter("struk.txt")) {
            writer.println(displayUtil.printLine("="));
            writer.println("\t\t\tBinarFud");
            writer.println(displayUtil.printLine("="));
            writer.println("Terima Kasih Sudah Memesan Di BinarFud");
            writer.println("Dibawah Ini Adalah Pesanan Anda");
            orderService.getAllOrders().forEach(
                    (key, value) ->
                            writer.println(value))
            ;
            writer.println(displayUtil.printLine("-") + "+");
            writer.println("Total\t\t\t " + orderService.getTotalQtyOrders() + "\t\t " + orderService.getTotalPriceOrders());
            writer.println("Pembayaran : BinarCash");
            writer.println(displayUtil.printLine("="));
            writer.println("Simpan Struk Ini Sebagai Bukti Pembayaran Anda");
            writer.println(displayUtil.printLine("="));
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis file: " + e.getMessage());
        }
        System.out.println("Struk telah disimpan dalam file 'struk.txt'");
    }
}
