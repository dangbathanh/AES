import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class TextFileDivider {
   /* public String divideAndConvertToHex(String filePath) {

        try (InputStream inputStream = new FileInputStream(filePath)) {
            byte[] buffer = new byte[16]; // Đọc 16 byte mỗi lần
            int bytesRead;
            int partNumber = 1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // Tính toán số lượng byte cần thêm vào để thực hiện Zero padding
                if(bytesRead<buffer.length){
                    buffer = Arrays.copyOf(buffer, bytesRead);
                    while (bytesRead < 16) {
                        buffer[bytesRead] = 0; // Thêm byte không để đủ 16 byte
                        bytesRead++;
                    }
                }
                // Chuyển dữ liệu đã đọc sang mã hex
                String hexString = bytesToHex(buffer);
                System.out.println("Part " + partNumber + ": " + hexString);
                partNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hexString;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    */
}
