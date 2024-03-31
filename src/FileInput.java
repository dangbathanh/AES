import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;
import java.util.Objects;
import java.lang.Object;

public class FileInput extends JFrame implements ActionListener{
    private JTextField selectedFileNameFieldEncrypt;
    private JTextField selectedFileNameFieldDecrypt;
    private JLabel outputFileNameLabelEncrypt;
    private JLabel outputFileNameLabelDecrypt;
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









    /*    JLabel encryptionMethodLabel = new JLabel("Encryption Method:");
        encryptionMethodLabel.setBounds(10, 10, 120, 20);
        add(encryptionMethodLabel);
        encryptionMethodComboBox = new JComboBox<>(new String[]{"AES-128", "AES-192", "AES-256"});
        encryptionMethodComboBox.setBounds(130, 10, 120, 20);
        add(encryptionMethodComboBox);

        // Thêm label và combo box cho chế độ mã hóa
        JLabel encryptionModeLabel = new JLabel("Encryption Mode:");
        encryptionModeLabel.setBounds(10, 40, 120, 20);
        add(encryptionModeLabel);
        encryptionModeComboBox = new JComboBox<>(new String[]{"CBC"});
        encryptionModeComboBox.setBounds(130, 40, 120, 20);
        encryptionModeComboBox.addActionListener(this);
        add(encryptionModeComboBox);

        JLabel keyLabel = new JLabel("Key:");
        keyLabel.setBounds(10, 80, 30, 20);
        add(keyLabel);

        JTextField keyTextField = new JTextField(20);
        keyTextField.setBounds(40, 80, 300, 20);
        add(keyTextField);

        // Thêm label và textfield cho IV
        ivLabel = new JLabel("Initialization Vector (IV):");
        ivLabel.setBounds(10, 120, 150, 20);
       // ivLabel.setVisible(false);
        add(ivLabel);

        ivTextField = new JTextField(20);
        ivTextField.setBounds(160, 120, 300, 20);
      //  ivTextField.setVisible(false);
        add(ivTextField);



        selectedFileNameField = new JTextField(20);
        selectedFileNameField.setEditable(false);
        selectedFileNameField.setBounds(10, 150, 200, 20);
        add(selectedFileNameField);

        JButton chooseButton = new JButton("Choose File");
        chooseButton.addActionListener(this);
        chooseButton.setBounds(220, 150, 120, 20);
        add(chooseButton);


        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setBounds(50, 180, 120, 30);
        add(submitButton);

        outputFileNameLabel = new JLabel();
        outputFileNameLabel.setBounds(10, 220, 150, 20);
        outputFileNameLabel.setVisible(false);
        add(outputFileNameLabel);



        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButton.setBounds(180, 220, 120, 20);
        saveButton.setEnabled(false);
        saveButton.setVisible(false);
        add(saveButton);

     */
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


        selectedFileNameFieldEncrypt = new JTextField(20);
        selectedFileNameFieldEncrypt.setEditable(false);
        selectedFileNameFieldEncrypt.setBounds(10, 150, 200, 20);
        panel.add(selectedFileNameFieldEncrypt);

        JButton chooseButtonEncrypt = new JButton("Choose Encrypt File");
        chooseButtonEncrypt.addActionListener(this);
        chooseButtonEncrypt.setBounds(220, 150, 120, 20);
        panel.add(chooseButtonEncrypt);


        JButton submitButtonEncrypt = new JButton("Submit Encrypt");
        submitButtonEncrypt.addActionListener(this);
        submitButtonEncrypt.setBounds(50, 180, 120, 30);
        panel.add(submitButtonEncrypt);

        outputFileNameLabelEncrypt = new JLabel();
        outputFileNameLabelEncrypt.setBounds(10, 220, 150, 20);
        outputFileNameLabelEncrypt.setVisible(false);
        panel.add(outputFileNameLabelEncrypt);


        saveButtonEncrypt = new JButton("Save Encrypt File");
        saveButtonEncrypt.addActionListener(this);
        saveButtonEncrypt.setBounds(180, 220, 120, 20);
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



        selectedFileNameFieldDecrypt = new JTextField(20);
        selectedFileNameFieldDecrypt.setEditable(false);
        selectedFileNameFieldDecrypt.setBounds(10, 150, 200, 20);
        panel.add(selectedFileNameFieldDecrypt);

        JButton chooseButtonDecrypt = new JButton("Choose Decrypt File");
        chooseButtonDecrypt.addActionListener(this);
        chooseButtonDecrypt.setBounds(220, 150, 120, 20);
        panel.add(chooseButtonDecrypt);


        JButton submitButtonDecrypt = new JButton("Submit Decrypt");
        submitButtonDecrypt.addActionListener(this);
        submitButtonDecrypt.setBounds(50, 180, 120, 30);
        panel.add(submitButtonDecrypt);

        outputFileNameLabelDecrypt = new JLabel();
        outputFileNameLabelDecrypt.setBounds(10, 220, 150, 20);
        outputFileNameLabelDecrypt.setVisible(false);
        panel.add(outputFileNameLabelDecrypt);



        saveButtonDecrypt = new JButton("Save Decrypt File");
        saveButtonDecrypt.addActionListener(this);
        saveButtonDecrypt.setBounds(180, 220, 120, 20);
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
                    String test = "";
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
                    //    System.out.print(test);
                        bos.close();
                        fos.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                    // Hiển thị biểu tượng và tên file output
                    outputFileNameLabelEncrypt.setText(outputFileEncrypt.getName());

                    outputFileNameLabelEncrypt.setVisible(true);
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
                    //int blockSize = 16;
                    String data = dataToHexString(iv);
                    String previousHexString = "";
                    boolean bFirstBlock = true;
                    String test = "";

                    try (FileInputStream fis = new FileInputStream(filePathDecrypt);
                         BufferedInputStream bis = new BufferedInputStream(fis);
                         FileOutputStream fos = new FileOutputStream(outputFileDecrypt);
                         BufferedOutputStream bos = new BufferedOutputStream(fos))
                    {
                        int bytesRead;
                        byte[] block = new byte[16];
                        byte[] previousBlock = new byte[16];
                        while ((bytesRead = bis.read(block)) != -1) {
                            // Padding with zeros if the last block is not full


                            if (!bFirstBlock) {
                                // Nếu không phải là khối đầu tiên, chuyển đổi khối trước đó thành chuỗi hexa và in ra
                                previousHexString = bytesToHex(previousBlock);
                            } else {
                                previousHexString = dataToHexString(iv);
                                bFirstBlock = false; // Đánh dấu khối hiện tại không phải là khối đầu tiên
                            }

                            // Convert block to hex string
                            String hexString = bytesToHex(block);
                            System.out.println("----------------------");
                            System.out.println("hexString"+hexString);
                            System.out.println("----------------------");

                            // Convert hex string back to byte array
                            test=cbc.decrypt(hexString, previousHexString, key);
                            System.out.println("----------------------");
                            System.out.println("result: "+test);
                            System.out.println("----------------------");
                            output = hexToBytes(test);

                            System.arraycopy(block, 0, previousBlock, 0, block.length);
                            // Kiểm tra xem chuỗi có chứa PKCS7 padding không

                            bos.write(removePadding(output));
                        }
                        //    System.out.print(test);
                        bos.close();
                        fos.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                    // Hiển thị biểu tượng và tên file output
                    outputFileNameLabelDecrypt.setText(outputFileDecrypt.getName());

                    outputFileNameLabelDecrypt.setVisible(true);
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
