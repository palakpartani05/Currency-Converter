import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverter extends JFrame{
    private JLabel amount,from,to,resultLabel;
    private JTextField amountField;
    private JComboBox<String> fromComboBox, toComboBox;
    private final String[] currencies = {"US Dollar","Indian Rupee","Euro","Japanese Yen","British Pound","Canadian Dollar","Australian Dollar","Swiss Franc)","Chinese Yuan"};
    private JButton convert;
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    private double[] exchangeRates = {1.00, 87.14,0.84, 109.65, 0.72, 1.27, 1.30, 0.92, 6.47};
    public CurrencyConverter(){
        setTitle("Currency Converter");
        
        from = new JLabel("From :");
        from.setBounds(100, 50, 60,60);
        add(from);

        fromComboBox = new JComboBox<>(currencies);
        fromComboBox.setBounds(200, 65, 220,30);
        add(fromComboBox);

        amount = new JLabel("Amount : ");
        amount.setBounds(100, 150, 60,60);
        add(amount);

        amountField = new JTextField();
        amountField.setBounds(200, 170, 220,30);
        add(amountField);

        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial",Font.PLAIN,20));
        resultLabel.setBounds(180,280,200,30);
        resultLabel.setForeground(Color.red);
        add(resultLabel);

        toComboBox = new JComboBox<>(currencies);
        toComboBox.setBounds(200, 115, 220,30);
        add(toComboBox);

        convert = new JButton("Convert");
        convert.setBounds(150, 220, 200,40);
        add(convert);

        to = new JLabel("To : ");
        to.setBounds(100, 100, 60,60);
        add(to);

        to = new JLabel("");
        //to.setBounds(100, 100, 60,60);
        add(to);
        
        
        
        convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String fromCurrency = (String) fromComboBox.getSelectedItem();
                    String toCurrency = (String) toComboBox.getSelectedItem();
                    double exchangeRate = exchangeRates[getIndex(toCurrency)] / exchangeRates[getIndex(fromCurrency)];
                    double result = amount * exchangeRate;
                    resultLabel.setText(decimalFormat.format(result) + " " + toCurrency);
                } 
                catch (Exception ex) {
                    resultLabel.setText("Invalid input");
                }
            }
        });

        setSize(600,400 );
        setLocation(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    protected int getIndex(String Currency) {
        for (int i = 0; i < currencies.length; i++) {
            if (Currency.equals(currencies[i])) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        new CurrencyConverter();
    }
}