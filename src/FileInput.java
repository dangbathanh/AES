import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.Objects;
import java.lang.Object;

public class FileInput extends JFrame implements ActionListener{
    private JTextField selectedFileNameFieldEncrypt;
    private JTextField selectedFileNameFieldDecrypt;
    private JLabel outputFileNameLabelEncrypt;
    private JTextField outputFileNameFieldEncrypt;
    private JLabel outputFileNameLabelDecrypt;
    private JTextField outputFileNameFieldDecrypt;
    private JTextField keyTextFieldEncrypt;
    private JTextField keyTextFieldDecrypt;
    private File selectedFileEncrypt;
    private File selectedFileDecrypt;
    private File outputFileEncrypt;
    private File outputFileDecrypt;
    private String filePathEncrypt;
    private String filePathDecrypt;
    private JButton saveButtonEncrypt;
    private JButton saveButtonDecrypt;
    private JComboBox<String> encryptionMethodComboBox;
    private JComboBox<String> decryptionMethodComboBox;
    private JComboBox<String> encryptionModeComboBox;
    private JComboBox<String> decryptionModeComboBox;
    private JLabel ivLabel;
    private JTextField ivTextFieldEncrypt;
    private JTextField ivTextFieldDecrypt;
    AES cbc = new CBC();
    public FileInput() {
        setTitle("Mã hóa AES");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null); // Sử dụng Null Layout

        JTabbedPane tabbedPane = new JTabbedPane();
       tabbedPane.setBounds(0, 0, 900, 600);
        JPanel encryptionPanel = createEncryptionPanel();
        tabbedPane.addTab("AES Encryption", null, encryptionPanel, "AES Encryption");
        JPanel decryptionPanel = createDecryptionPanel();
        tabbedPane.addTab("AES Decryption", null, decryptionPanel, "AES Decryption");


        add(tabbedPane);

    }


    private JPanel createEncryptionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel encryptionMethodLabel = new JLabel("Encryption Method:");
        encryptionMethodLabel.setBounds(10, 10, 120, 20);
        panel.add(encryptionMethodLabel);
        encryptionMethodComboBox = new JComboBox<>(new String[]{"AES-128", "AES-192", "AES-256"});
        encryptionMethodComboBox.setBounds(130, 10, 120, 20);
        panel.add(encryptionMethodComboBox);

        // Thêm label và combo box cho chế độ mã hóa
        JLabel encryptionModeLabel = new JLabel("Encryption Mode:");
        encryptionModeLabel.setBounds(10, 40, 120, 20);
        panel.add(encryptionModeLabel);
        encryptionModeComboBox = new JComboBox<>(new String[]{"CBC"});
        encryptionModeComboBox.setBounds(130, 40, 120, 20);
        encryptionModeComboBox.addActionListener(this);
        panel.add(encryptionModeComboBox);

        JLabel keyLabel = new JLabel("Key:");
        keyLabel.setBounds(10, 80, 30, 20);
        panel.add(keyLabel);

        keyTextFieldEncrypt = new JTextField(20);
        keyTextFieldEncrypt.setBounds(40, 80, 300, 20);
        panel.add(keyTextFieldEncrypt);

        // Thêm label và textfield cho IV
        ivLabel = new JLabel("Initialization Vector (IV):");
        ivLabel.setBounds(10, 120, 150, 20);
        // ivLabel.setVisible(false);
        panel.add(ivLabel);

        ivTextFieldEncrypt = new JTextField(20);
        ivTextFieldEncrypt.setBounds(160, 120, 300, 20);
        //  ivTextField.setVisible(false);
        panel.add(ivTextFieldEncrypt);

        JLabel selectedFileNameFieldEncryptLabel = new JLabel("Choose Encrypt File:");
        selectedFileNameFieldEncryptLabel.setBounds(10, 150, 120, 20);
        panel.add(selectedFileNameFieldEncryptLabel);

        selectedFileNameFieldEncrypt = new JTextField(20);
        selectedFileNameFieldEncrypt.setEditable(false);
        selectedFileNameFieldEncrypt.setBounds(140, 150, 200, 20);
        panel.add(selectedFileNameFieldEncrypt);

        JButton chooseButtonEncrypt = new JButton("Choose Encrypt File");
        chooseButtonEncrypt.addActionListener(this);
        chooseButtonEncrypt.setBounds(360, 150, 170, 20);
        panel.add(chooseButtonEncrypt);


        JButton submitButtonEncrypt = new JButton("Submit Encrypt");
        submitButtonEncrypt.addActionListener(this);
        submitButtonEncrypt.setBounds(50, 180, 120, 30);
        panel.add(submitButtonEncrypt);

        outputFileNameLabelEncrypt = new JLabel("File Encrypted:");
        outputFileNameLabelEncrypt.setBounds(10, 220, 90, 20);
        outputFileNameLabelEncrypt.setVisible(false);
        panel.add(outputFileNameLabelEncrypt);

        outputFileNameFieldEncrypt = new JTextField();
        outputFileNameFieldEncrypt.setEditable(false);
        outputFileNameFieldEncrypt.setBounds(110, 220, 150, 20);
        outputFileNameFieldEncrypt.setVisible(false);
        panel.add(outputFileNameFieldEncrypt);


        saveButtonEncrypt = new JButton("Save Encrypt File");
        saveButtonEncrypt.addActionListener(this);
        saveButtonEncrypt.setBounds(270, 220, 120, 20);
        saveButtonEncrypt.setEnabled(false);
        saveButtonEncrypt.setVisible(false);
        panel.add(saveButtonEncrypt);

        // Thêm các thành phần khác cho tab mã hóa AES ở đây

        return panel;
    }

    private JPanel createDecryptionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel decryptionMethodLabel = new JLabel("Decryption Method:");
        decryptionMethodLabel.setBounds(10, 10, 120, 20);
        panel.add(decryptionMethodLabel);
        decryptionMethodComboBox = new JComboBox<>(new String[]{"AES-128", "AES-192", "AES-256"});
        decryptionMethodComboBox.setBounds(130, 10, 120, 20);
        panel.add(decryptionMethodComboBox);

        // Thêm label và combo box cho chế độ mã hóa
        JLabel decryptionModeLabel = new JLabel("Decryption Mode:");
        decryptionModeLabel.setBounds(10, 40, 120, 20);
        panel.add(decryptionModeLabel);
        decryptionModeComboBox = new JComboBox<>(new String[]{"CBC"});
        decryptionModeComboBox.setBounds(130, 40, 120, 20);
        decryptionModeComboBox.addActionListener(this);
        panel.add(decryptionModeComboBox);

        JLabel keyLabel = new JLabel("Key:");
        keyLabel.setBounds(10, 80, 30, 20);
        panel.add(keyLabel);

        keyTextFieldDecrypt = new JTextField(20);
        keyTextFieldDecrypt.setBounds(40, 80, 300, 20);
        panel.add(keyTextFieldDecrypt);

        // Thêm label và textfield cho IV
        ivLabel = new JLabel("Initialization Vector (IV):");
        ivLabel.setBounds(10, 120, 150, 20);
        // ivLabel.setVisible(false);
        panel.add(ivLabel);

        ivTextFieldDecrypt = new JTextField(20);
        ivTextFieldDecrypt.setBounds(160, 120, 300, 20);
        //  ivTextField.setVisible(false);
        panel.add(ivTextFieldDecrypt);

        JLabel selectedFileNameFieldDecryptLabel = new JLabel("Choose Decrypt File:");
        selectedFileNameFieldDecryptLabel.setBounds(10, 150, 120, 20);
        panel.add(selectedFileNameFieldDecryptLabel);

        selectedFileNameFieldDecrypt = new JTextField(20);
        selectedFileNameFieldDecrypt.setEditable(false);
        selectedFileNameFieldDecrypt.setBounds(140, 150, 200, 20);
        panel.add(selectedFileNameFieldDecrypt);

        JButton chooseButtonDecrypt = new JButton("Choose Decrypt File");
        chooseButtonDecrypt.addActionListener(this);
        chooseButtonDecrypt.setBounds(360, 150, 120, 20);
        panel.add(chooseButtonDecrypt);


        JButton submitButtonDecrypt = new JButton("Submit Decrypt");
        submitButtonDecrypt.addActionListener(this);
        submitButtonDecrypt.setBounds(50, 180, 120, 30);
        panel.add(submitButtonDecrypt);

        outputFileNameLabelDecrypt = new JLabel("File Decrypted:");
        outputFileNameLabelDecrypt.setBounds(10, 220, 90, 20);
        outputFileNameLabelDecrypt.setVisible(false);
        panel.add(outputFileNameLabelDecrypt);


        outputFileNameFieldDecrypt = new JTextField();
        outputFileNameFieldDecrypt.setEditable(false);
        outputFileNameFieldDecrypt.setBounds(110, 220, 150, 20);
        outputFileNameFieldDecrypt.setVisible(false);
        panel.add(outputFileNameFieldDecrypt);



        saveButtonDecrypt = new JButton("Save Decrypt File");
        saveButtonDecrypt.addActionListener(this);
        saveButtonDecrypt.setBounds(270, 220, 120, 20);
        saveButtonDecrypt.setEnabled(false);
        saveButtonDecrypt.setVisible(false);
        panel.add(saveButtonDecrypt);

        // Thêm các thành phần khác cho tab mã hóa AES ở đây

        return panel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Choose Encrypt File")) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFileEncrypt = fileChooser.getSelectedFile();
                selectedFileNameFieldEncrypt.setText(selectedFileEncrypt.getName());
                filePathEncrypt = selectedFileEncrypt.getAbsolutePath();
                // Hiển thị biểu tượng của file

            }
        } else if (e.getActionCommand().equals("Submit Encrypt")) {
            if (selectedFileEncrypt != null) {
                try {
                    String selectedEncryptionMethod = (String) encryptionMethodComboBox.getSelectedItem();
                    String iv = ivTextFieldEncrypt.getText();
                    String key = keyTextFieldEncrypt.getText();
                    byte[] output;
                    if (iv.length() != 16) {
                        JOptionPane.showMessageDialog(this, "IV must be 16 characters long!", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Ngăn không cho tiếp tục thực hiện
                    }

                    switch (selectedEncryptionMethod) {
                        case "AES-128" -> {
                            if (key.length() != 16) {
                                JOptionPane.showMessageDialog(this, "Key must be 16 characters long for AES-128 encryption!", "Error", JOptionPane.ERROR_MESSAGE);
                                return; // Ngăn không cho tiếp tục thực hiện
                            }
                        }
                        case "AES-192" -> {
                            if (key.length() != 24) {
                                JOptionPane.showMessageDialog(this, "Key must be 24 characters long for AES-192 encryption!", "Error", JOptionPane.ERROR_MESSAGE);
                                return; // Ngăn không cho tiếp tục thực hiện
                            }
                        }
                        case "AES-256" -> {
                            if (key.length() != 32) {
                                JOptionPane.showMessageDialog(this, "Key must be 32 characters long for AES-256 encryption!", "Error", JOptionPane.ERROR_MESSAGE);
                                return; // Ngăn không cho tiếp tục thực hiện
                            }
                        }
                        case null -> {
                            JOptionPane.showMessageDialog(this, "Encrypt method invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Ngăn không cho tiếp tục thực hiện
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + selectedEncryptionMethod);
                    }
                    // Tạo file tạm và ghi dữ liệu vào file
                    outputFileEncrypt = File.createTempFile("temp_", ".txt");
                    //int blockSize = 16;
                    String data = dataToHexString(iv);
                 //   String test = "";
                    try (FileInputStream fis = new FileInputStream(filePathEncrypt);
                         BufferedInputStream bis = new BufferedInputStream(fis);
                        FileOutputStream fos = new FileOutputStream(outputFileEncrypt);
                        BufferedOutputStream bos = new BufferedOutputStream(fos))
                    {
                        int bytesRead;
                        byte[] block = new byte[16];
                        while ((bytesRead = bis.read(block)) != -1) {
                            // Padding with zeros if the last block is not full
                            if (bytesRead < 16) {
                               // for (int i = bytesRead; i < 16; i++) {
                              //      block[i] = 0;
                            //    }
                                for (int i = bytesRead; i < 16; i++) {
                                    block[i] = (byte) (16 - bytesRead);
                                }
                            }
                            // Convert block to hex string
                            String hexString = bytesToHex(block);


                            data = cbc.encrypt(hexString, data, key);
                            // Convert hex string back to byte array
                            output = hexToBytes(data);

                            bos.write(output);
                        }
                        // Thêm 16 byte đệm cuối file
                        bos.write(hexToBytes(cbc.encrypt("10101010101010101010101010101010", data, key)));
                        bos.close();
                        fos.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                    // Hiển thị biểu tượng và tên file output
                    outputFileNameFieldEncrypt.setText(outputFileEncrypt.getName());

                    outputFileNameLabelEncrypt.setVisible(true);
                    outputFileNameFieldEncrypt.setVisible(true);
                    // Kích hoạt nút lưu
                    saveButtonEncrypt.setEnabled(true);
                    saveButtonEncrypt.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tạo file tạm và ghi dữ liệu.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một file trước khi submit.");
            }
        } else if (e.getActionCommand().equals("Save Encrypt File")) {
            if (outputFileEncrypt != null) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedSaveFile = fileChooser.getSelectedFile();
                    try {
                        FileInputStream inputStream = new FileInputStream(outputFileEncrypt);
                        FileOutputStream outputStream = new FileOutputStream(selectedSaveFile);
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = inputStream.read(buffer)) > 0) {
                            outputStream.write(buffer, 0, length);
                        }
                        inputStream.close();
                        outputStream.close();
                        JOptionPane.showMessageDialog(this, "File đã được lưu thành công.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi lưu file.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng tạo file trước khi lưu.");
            }
        }


       else if (e.getActionCommand().equals("Choose Decrypt File")) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFileDecrypt = fileChooser.getSelectedFile();
                selectedFileNameFieldDecrypt.setText(selectedFileDecrypt.getName());
                filePathDecrypt = selectedFileDecrypt.getAbsolutePath();
                // Hiển thị biểu tượng của file

            }
        } else if (e.getActionCommand().equals("Submit Decrypt")) {
            if (selectedFileDecrypt != null) {
                try {
                    String selectedDecryptionMethod = (String) decryptionMethodComboBox.getSelectedItem();
                    String iv = ivTextFieldDecrypt.getText();
                    String key = keyTextFieldDecrypt.getText();
                    byte[] output;
                    if (iv.length() != 16) {
                        JOptionPane.showMessageDialog(this, "IV must be 16 characters long!", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Ngăn không cho tiếp tục thực hiện
                    }

                    switch (selectedDecryptionMethod) {
                        case "AES-128" -> {
                            if (key.length() != 16) {
                                JOptionPane.showMessageDialog(this, "Key must be 16 characters long for AES-128 encryption!", "Error", JOptionPane.ERROR_MESSAGE);
                                return; // Ngăn không cho tiếp tục thực hiện
                            }
                        }
                        case "AES-192" -> {
                            if (key.length() != 24) {
                                JOptionPane.showMessageDialog(this, "Key must be 24 characters long for AES-192 encryption!", "Error", JOptionPane.ERROR_MESSAGE);
                                return; // Ngăn không cho tiếp tục thực hiện
                            }
                        }
                        case "AES-256" -> {
                            if (key.length() != 32) {
                                JOptionPane.showMessageDialog(this, "Key must be 32 characters long for AES-256 encryption!", "Error", JOptionPane.ERROR_MESSAGE);
                                return; // Ngăn không cho tiếp tục thực hiện
                            }
                        }
                        case null -> {
                            JOptionPane.showMessageDialog(this, "Decrypt method invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Ngăn không cho tiếp tục thực hiện
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + selectedDecryptionMethod);
                    }
                    // Tạo file tạm và ghi dữ liệu vào file
                    outputFileDecrypt = File.createTempFile("temp_", ".txt");

                    String data = dataToHexString(iv);
                    String previousHexString = "";

                    String result = "";
                    String hexString = "";

                    try (FileInputStream fis = new FileInputStream(filePathDecrypt);
                         BufferedInputStream bis = new BufferedInputStream(fis);
                         FileOutputStream fos = new FileOutputStream(outputFileDecrypt);
                         BufferedOutputStream bos = new BufferedOutputStream(fos))
                    {
                        int bytesRead;
                        byte[] block = new byte[16];
                        byte[] previousBlock = new byte[16];
                        byte[] previousDecrypt = new byte[16];
                        bytesRead = bis.read(block);
                        if (bytesRead == -1) {
                            // File không có đủ dữ liệu để đọc
                            return;
                        }
                            previousBlock = block;
                            previousHexString = dataToHexString(iv);

                            hexString = bytesToHex(block);

                            result=cbc.decrypt(hexString, previousHexString, key);

                            previousDecrypt = hexToBytes(result);
                            previousHexString = bytesToHex(previousBlock);




                        while ((bytesRead = bis.read(block)) != -1) {



                                // Nếu không phải là khối đầu tiên, chuyển đổi khối trước đó thành chuỗi hexa và in ra



                            // Convert block to hex string
                            hexString = bytesToHex(block);


                            // Convert hex string back to byte array
                            result=cbc.decrypt(hexString, previousHexString, key);

                            output = hexToBytes(result);


                            // Kiểm tra xem chuỗi có chứa PKCS7 padding không
                            if(Arrays.equals(output, new byte[]{0x10, 0x10, 0x10, 0x10, 0x10,0x10,0x10,0x10,0x10,0x10,0x10,0x10,0x10,0x10,0x10,0x10})){
                                    previousDecrypt = removePadding(previousDecrypt);
                                    bos.write(previousDecrypt);
                            }else{
                                bos.write(previousDecrypt);
                            }
                            System.arraycopy(block, 0, previousBlock, 0, block.length);
                            previousHexString = bytesToHex(previousBlock);
                            previousDecrypt = output;
                        }
                        bos.close();
                        fos.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    // Hiển thị biểu tượng và tên file output
                    outputFileNameFieldDecrypt.setText(outputFileDecrypt.getName());
                    outputFileNameLabelDecrypt.setVisible(true);
                    outputFileNameFieldDecrypt.setVisible(true);
                    // Kích hoạt nút lưu
                    saveButtonDecrypt.setEnabled(true);
                    saveButtonDecrypt.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tạo file tạm và ghi dữ liệu.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một file trước khi submit.");
            }
        } else if (e.getActionCommand().equals("Save Decrypt File")) {
            if (outputFileDecrypt != null) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedSaveFileDecrypt = fileChooser.getSelectedFile();
                    try {
                        FileInputStream inputStream = new FileInputStream(outputFileDecrypt);
                        FileOutputStream outputStream = new FileOutputStream(selectedSaveFileDecrypt);
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = inputStream.read(buffer)) > 0) {
                            outputStream.write(buffer, 0, length);
                        }
                        inputStream.close();
                        outputStream.close();
                        JOptionPane.showMessageDialog(this, "File đã được lưu thành công.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi lưu file.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng tạo file trước khi lưu.");
            }
        }


    }
    protected String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(bytes.length*2);
        for (byte b : bytes) {
        //    String hex = Integer.toHexString(0xff & b);
         //   if (hex.length() == 1) {
      //          hexString.append('0');
        //    }
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

    protected byte[] hexToBytes(String hexString){
        int len = hexString.length();
        byte[] output = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            output[i / 2] = (byte) Integer.parseInt(hexString.substring(i, i + 2), 16);
        }
        return output;
    }

    protected String dataToHexString(String data) {
        StringBuilder hexString = new StringBuilder();
        for (char c : data.toCharArray()) {
            hexString.append(String.format("%02X", (int) c));
        }
        return hexString.toString();
    }

    public byte[] removePadding(byte[] output) {
        int paddingLength = output[output.length - 1];
        if (paddingLength > 0 && paddingLength <= 16) {
            // Loại bỏ byte padding
            for (int i = 16 - paddingLength; i < 15; i++) {
                if (output[i] != paddingLength) {
                    return output;
                }
            }
            byte[] result = new byte[output.length - paddingLength];
            System.arraycopy(output, 0, result, 0, result.length);
            return result;
        } else {
            // Nếu không có padding, trả về chuỗi ban đầu
            return output;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileInput example = new FileInput();
            example.setVisible(true);
        });
    }
}
