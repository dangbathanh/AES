import java.util.Scanner;
import java.io.*;
public class Main {
    public static void main(String[] args) {
    //    AES aes = new AES();
        int[][] data = {
                {0xD4, 0xE0, 0xB8, 0x1E},
                {0xBF, 0xB4, 0x41, 0x27},
                {0x5D, 0x52, 0x11, 0x98},
                {0x30, 0xAE, 0xF1, 0xE5}
        };
    /*    int[][] key = {
                {0x2B, 0x7E, 0x15, 0x16},
                {0x28, 0xAE, 0xD2, 0xA6},
                {0xAB, 0xF7, 0x97, 0x6E},
                {0x5D, 0x77, 0x81, 0x17}
        }; */
        String key = "2b7e151628aed2a6abf7158809cf4f3c";
           //     "2b7e151628aed2a6abf7158809cf4f3c";
        //    "8e73b0f7da0e6452c810f32b809079e562f8ead2522c6b7b";

       // 603deb1015ca71be2b73aef0857d77811f352c073b6108d72d9810a30914dff4


        long startTime = System.nanoTime();

 //      int[][] result =aes.keyExpansion(key);
        long endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
   //     aes.printState(data);
       /* int chon=0;
        Scanner scanner = new Scanner(System.in);
while(chon!=3){
    System.out.println("Chương trình mã hóa file sử dụng AES.");
    System.out.println("1.Mã hóa file");
    System.out.println("2.Giải mã file");
    System.out.println("3.Thoát chương trình");
    System.out.print("Chọn: ");
    chon = scanner.nextInt();
    scanner.nextLine();
    switch (chon){
        case 1:
            String key;
            while (true) {
                System.out.print("Nhập key (16 ký tự): ");
                key = scanner.nextLine();
                if (key.length() == 16) {
                    break;
                } else {
                    System.out.println("Key phải có đúng 16 ký tự.");
                }
            }

            String inputFile;
            while (true) {
                System.out.print("Nhập tên file cần mã hóa: ");
                File file = new File(System.getProperty("user.dir") + File.separator + scanner.nextLine());
                if (file.exists()) {
                    break;
                } else {
                    System.out.println("File không tồn tại trong thư mục hiện tại.");
                }
            }

            try {
                // encrypt(inputFile, outputFile, key);
                //  System.out.println("File đã được mã hóa thành công!");
            } catch (Exception e) {
                // System.out.println("Đã xảy ra lỗi: " + e.getMessage());
            } finally {
                //  scanner.close();
            }
            break;
        case 2:
            break;
        case 3:
            chon=3;
            break;
    }


} */

         //   for (int i = 0; i < result[0].length; i++) {
         //       System.out.printf("Round key %2d: ", i);
          //      for (int j = 0; j < 4; j++) {
         //           System.out.printf("%02X ", result[j][i]);
          //      }
         //       System.out.println();
          //  }
        }


}