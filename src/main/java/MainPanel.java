import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainPanel extends JPanel {
    private JComboBox<String> pClassComboBox;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> embarkedComboBox;
    private JTextField minPassengerIdTextFiled;
    private JTextField maxPassengerIdTextFiled;
    private JTextField nameTextField;
    private JTextField sibSpTextField;
    private JTextField parChTextField;
    private JTextField ticketNumberTextField;
    private JTextField minFareTextField;
    private JTextField maxFareTextField;
    private JTextField cabinNumberTextField;
    private List<Passenger> passengers= new LinkedList<>();
    private JButton filterButton;
    private JButton statisticsButton;
    private int timesFiltered=0;

    public MainPanel(int x, int y, int width, int height) throws FileNotFoundException {
        File file = new File(Constants.PATH_TO_DATA_FILE);//this is the path to the data file
        Scanner scanner = new Scanner(file);
        Scanner scannerForChecking = new Scanner(file);
        scanner.nextLine();
        scannerForChecking.nextLine();
        while (scanner.hasNextLine()) {
            String[] temporary=new String[13];
            if(scannerForChecking.nextLine().split(",").length==13){
                temporary= scanner.nextLine().split(",");
            }
            else {
                temporary= (scanner.nextLine()+" ").split(",");
                temporary[12]="";
            }
            String[] values=temporary;
            for (int i=3;i<5;i++){
                String temp=values[i];
                values[i]="";
                int finalI = i;
                temp.chars().mapToObj(e ->(char)e).filter(character -> !character.equals('"')).forEach(character -> {
                   values[finalI]=values[finalI]+character;
               });
               // Stream.of(charArray).filter(char::isItQuotationMark())
            }
            Passenger passenger = new Passenger(values[0], values[1], values[2], values[3] + "," + values[4], (String) values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12]);
            passengers.add(passenger);
        }
        this.setLayout(null);
        this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);
        FilterParameters filterParameters = new FilterParameters();
        final int All = -1;
        final int FIRST_CLASS = 1;
        final int SECOND_CLASS = 2;
        final int THIRD_CLASS = 3;
        final int TWO = 2;
        final int THREE = 3;
        final int FOUR = 4;
        final int FIVE = 5;
        final int SIX = 6;
        final int SEVEN = 7;
        final int EIGHT = 8;
        JLabel pClassLabel = new JLabel("Passenger Class: ");
        pClassLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(pClassLabel);
        this.pClassComboBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
        this.pClassComboBox.setBounds(pClassLabel.getX() + pClassLabel.getWidth() + 1, pClassLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.add(this.pClassComboBox);
        this.pClassComboBox.addActionListener((e) -> {
            int pClassParameter;
            String optionSelected = (String) pClassComboBox.getSelectedItem();
            switch (optionSelected) {
                case "All": {
                    pClassParameter = All;
                    break;
                }
                case "1st": {
                    pClassParameter = FIRST_CLASS;
                    break;
                }
                case "2nd": {
                    pClassParameter = SECOND_CLASS;
                    break;
                }
                case "3rd": {
                    pClassParameter = THIRD_CLASS;
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + optionSelected);
            }
            filterParameters.setPClass(pClassParameter);
        });
        JLabel genderLabel = new JLabel("Sex: ");
        genderLabel.setBounds(pClassComboBox.getX() + pClassComboBox.getWidth() + Constants.SPACE_BETWEEN_BOXES, y, Constants.LABEL_WIDTH / THREE, Constants.LABEL_HEIGHT);
        this.add(genderLabel);
        this.genderComboBox = new JComboBox<>(Constants.PASSENGER_GENDER_OPTIONS);
        this.genderComboBox.setBounds(genderLabel.getX() + genderLabel.getWidth() + 1, genderLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.add(this.genderComboBox);
        this.genderComboBox.addActionListener((e) -> {
            filterParameters.setGender((String) genderComboBox.getSelectedItem());
        });
        JLabel embarkedLabel = new JLabel("Embarked: ");
        embarkedLabel.setBounds(genderComboBox.getX() + genderComboBox.getWidth() + Constants.SPACE_BETWEEN_BOXES, y, TWO * Constants.LABEL_WIDTH / THREE, Constants.LABEL_HEIGHT);
        this.add(embarkedLabel);
        this.embarkedComboBox = new JComboBox<>(Constants.PASSENGER_EMBARKING_OPTIONS);
        this.embarkedComboBox.setBounds(embarkedLabel.getX() + embarkedLabel.getWidth() + 1, embarkedLabel.getY(), THREE * Constants.COMBO_BOX_WIDTH / TWO, Constants.COMBO_BOX_HEIGHT);
        this.add(this.embarkedComboBox);
        this.embarkedComboBox.addActionListener((e) -> {
            char embarkedFilter;
            String optionSelected = (String) embarkedComboBox.getSelectedItem();
            switch (optionSelected) {
                case "All": {
                    embarkedFilter = 'A';
                    break;
                }
                case "Cherbourg": {
                    embarkedFilter = 'C';
                    break;
                }
                case "Queenstown": {
                    embarkedFilter = 'Q';
                    break;
                }
                case "Southampton": {
                    embarkedFilter = 'S';
                    break;
                }

                default:
                    throw new IllegalStateException("Unexpected value: " + optionSelected);
            }
            filterParameters.setEmbarked(embarkedFilter);
        });
        JLabel minPassengerIdLabel = new JLabel("Minimal passenger id: ");
        minPassengerIdLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y + Constants.LABEL_HEIGHT + Constants.SPACE_BETWEEN_LINES, FIVE * Constants.LABEL_WIDTH / FOUR, Constants.LABEL_HEIGHT);
        this.add(minPassengerIdLabel);
        this.minPassengerIdTextFiled = new JTextField();
        minPassengerIdTextFiled.setBounds(minPassengerIdLabel.getX() + minPassengerIdLabel.getWidth() + 1, minPassengerIdLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.minPassengerIdTextFiled);

        JLabel maxPassengerIdLabel = new JLabel("Maximal passenger id: ");
        maxPassengerIdLabel.setBounds(minPassengerIdTextFiled.getX() + minPassengerIdTextFiled.getWidth() + Constants.SPACE_BETWEEN_BOXES, y + Constants.LABEL_HEIGHT + Constants.SPACE_BETWEEN_LINES, FIVE * Constants.LABEL_WIDTH / FOUR, Constants.LABEL_HEIGHT);
        this.add(maxPassengerIdLabel);
        this.maxPassengerIdTextFiled = new JTextField();
        maxPassengerIdTextFiled.setBounds(maxPassengerIdLabel.getX() + maxPassengerIdLabel.getWidth() + 1, minPassengerIdLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.maxPassengerIdTextFiled);

        JLabel nameLabel = new JLabel("Passenger name: ");
        nameLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y + TWO * Constants.LABEL_HEIGHT + TWO * Constants.SPACE_BETWEEN_LINES, FIVE * Constants.LABEL_WIDTH / FOUR, Constants.LABEL_HEIGHT);
        this.add(nameLabel);
        this.nameTextField = new JTextField();
        nameTextField.setBounds(nameLabel.getX() + nameLabel.getWidth() + 1, nameLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.nameTextField);

        JLabel sibSpLabel = new JLabel("Sibling&Spouse count: ");
        sibSpLabel.setBounds(minPassengerIdTextFiled.getX() + minPassengerIdTextFiled.getWidth() + Constants.SPACE_BETWEEN_BOXES, y + TWO * Constants.LABEL_HEIGHT + TWO * Constants.SPACE_BETWEEN_LINES, FIVE * Constants.LABEL_WIDTH / FOUR, Constants.LABEL_HEIGHT);
        this.add(sibSpLabel);
        this.sibSpTextField = new JTextField();
        sibSpTextField.setBounds(sibSpLabel.getX() + sibSpLabel.getWidth() + 1, sibSpLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.sibSpTextField);

        JLabel parChLabel = new JLabel("Parents&Child count: ");
        parChLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y + THREE * Constants.LABEL_HEIGHT + THREE * Constants.SPACE_BETWEEN_LINES, FIVE * Constants.LABEL_WIDTH / FOUR, Constants.LABEL_HEIGHT);
        this.add(parChLabel);
        this.parChTextField = new JTextField();
        parChTextField.setBounds(parChLabel.getX() + parChLabel.getWidth() + 1, parChLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.parChTextField);

        JLabel ticketNumberLabel = new JLabel("Ticket number (full): ");
        ticketNumberLabel.setBounds(parChTextField.getX() + parChTextField.getWidth() + Constants.SPACE_BETWEEN_BOXES, y + THREE * Constants.LABEL_HEIGHT + THREE * Constants.SPACE_BETWEEN_LINES, FIVE * Constants.LABEL_WIDTH / FOUR, Constants.LABEL_HEIGHT);
        this.add(ticketNumberLabel);
        this.ticketNumberTextField = new JTextField();
        ticketNumberTextField.setBounds(ticketNumberLabel.getX() + ticketNumberLabel.getWidth() + 1, ticketNumberLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.ticketNumberTextField);

        JLabel minFareLabel = new JLabel("Minimal fare: ");
        minFareLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y + FOUR * Constants.LABEL_HEIGHT + FOUR * Constants.SPACE_BETWEEN_LINES, FIVE * Constants.LABEL_WIDTH / SEVEN, Constants.LABEL_HEIGHT);
        this.add(minFareLabel);
        this.minFareTextField = new JTextField();
        minFareTextField.setBounds(minFareLabel.getX() + minFareLabel.getWidth() + 1, minFareLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.minFareTextField);
        JLabel maxFareLabel = new JLabel("Maximal fare: ");
        maxFareLabel.setBounds(minFareTextField.getX() + minFareTextField.getWidth() + Constants.SPACE_BETWEEN_BOXES / THREE, y + FOUR * Constants.LABEL_HEIGHT + FOUR * Constants.SPACE_BETWEEN_LINES, THREE * Constants.LABEL_WIDTH / FOUR, Constants.LABEL_HEIGHT);
        this.add(maxFareLabel);
        this.maxFareTextField = new JTextField();
        maxFareTextField.setBounds(maxFareLabel.getX() + maxFareLabel.getWidth() + 1, maxFareLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.maxFareTextField);
        JLabel cabinNumberLabel = new JLabel("Cabin number: ");
        cabinNumberLabel.setBounds(maxFareTextField.getX() + maxFareTextField.getWidth() + Constants.SPACE_BETWEEN_BOXES / THREE, y + FOUR * Constants.LABEL_HEIGHT + FOUR * Constants.SPACE_BETWEEN_LINES, FOUR * Constants.LABEL_WIDTH / FIVE, Constants.LABEL_HEIGHT);
        this.add(cabinNumberLabel);
        this.cabinNumberTextField = new JTextField();
        cabinNumberTextField.setBounds(cabinNumberLabel.getX() + cabinNumberLabel.getWidth() + 1, cabinNumberLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.cabinNumberTextField);
        filterButton = new JButton("Filter");
        filterButton.setBounds(Constants.WINDOW_WIDTH / TWO - Constants.BUTTON_WIDTH / TWO, y + SIX * Constants.LABEL_HEIGHT + SIX * Constants.SPACE_BETWEEN_LINES, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        this.add(filterButton);
        JLabel filterResultLabel=new JLabel();
        filterResultLabel.setBounds(Constants.WINDOW_WIDTH / TWO - FIVE*Constants.LABEL_WIDTH/FOUR, y + Constants.BUTTON_HEIGHT+SIX * Constants.LABEL_HEIGHT + SEVEN * Constants.SPACE_BETWEEN_LINES,FIVE*Constants.LABEL_WIDTH/TWO, Constants.LABEL_HEIGHT);
        this.add(filterResultLabel);
        this.filterButton.addActionListener((e) -> {
            timesFiltered++;
            if (!maxPassengerIdTextFiled.getText().equals("") && maxPassengerIdTextFiled.getText() != null) {
                try {
                    filterParameters.setMaxPassengerId(Integer.parseInt(maxPassengerIdTextFiled.getText()));
                } catch (NumberFormatException E) {
                    System.out.println("Input String cannot be parsed to Integer.");
                }
            }
            else {
                filterParameters.setMaxPassengerId(-1);
            }
            if (!nameTextField.getText().equals("") && nameTextField.getText() != null) {
                filterParameters.setNameContains(nameTextField.getText());
            }
            else {
                filterParameters.setNameContains("");
            }
            if (!sibSpTextField.getText().equals("") && sibSpTextField.getText() != null) {
                try {
                    filterParameters.setSibSp(Integer.parseInt(sibSpTextField.getText()));
                } catch (NumberFormatException E) {
                    System.out.println("Input String cannot be parsed to Integer.");
                }
            }else{
                filterParameters.setSibSp(-1);
            }
            if (!parChTextField.getText().equals("") && parChTextField.getText() != null) {
                try {
                    filterParameters.setParCh(Integer.parseInt(parChTextField.getText()));
                } catch (NumberFormatException E) {
                    System.out.println("Input String cannot be parsed to Integer.");
                }
            }else {
                filterParameters.setParCh(-1);
            }
            if (!ticketNumberTextField.getText().equals("") && ticketNumberTextField.getText() != null) {
                filterParameters.setTicketNumber(ticketNumberTextField.getText());
            } else{
                filterParameters.setTicketNumber("");
            }
            if (!minFareTextField.getText().equals("")&&minFareTextField.getText()!=null) {
            try {
                filterParameters.setMinFare(Integer.parseInt(minFareTextField.getText()));
            } catch (NumberFormatException E) {
                System.out.println("Input String cannot be parsed to Integer.");
            }
        }
         else{
                filterParameters.setMinFare(-1);
        }
            if (!maxFareTextField.getText().equals("")&&maxFareTextField.getText()!=null) {
            try {
                filterParameters.setMaxFare(Integer.parseInt(maxFareTextField.getText()));
            } catch (NumberFormatException E) {
                System.out.println("Input String cannot be parsed to Integer.");
            }
        }else {
                filterParameters.setMaxFare(-1);
            }
            if (!minPassengerIdTextFiled.getText().equals("")&&minPassengerIdTextFiled.getText()!=null) {
            try {
                filterParameters.setMinPassengerId(Integer.parseInt(minPassengerIdTextFiled.getText()));
            } catch (NumberFormatException E) {
                System.out.println("Input String cannot be parsed to Integer.");
            }
        }else{
                filterParameters.setMinPassengerId(-1);
            }
            if (!cabinNumberTextField.getText().equals("")&&cabinNumberTextField.getText()!=null) {
                    filterParameters.setCabin(cabinNumberTextField.getText());
                }else {
                filterParameters.setCabin("");
            }

            List<Passenger> filteredList = passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).sorted(Comparator.comparing(passenger -> passenger.getFormattedName())).collect(Collectors.toList());
            int rowCount=filteredList.size();
            filterParameters.setSurvivalStatus(1);
            int survivedMatchingPassengersCount= (int) filteredList.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
            filterParameters.setSurvivalStatus(-1);
            filterResultLabel.setText("Total Rows: "+rowCount+" ("+survivedMatchingPassengersCount+" survived, "+(rowCount-survivedMatchingPassengersCount)+" did not)");
            File filteredFile = new File("src/main/resources/titanic"+timesFiltered+".csv");
            if(!filteredFile.exists()){
                try {
                    filteredFile.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                FileWriter fileWriter= new FileWriter(filteredFile.getPath());
                fileWriter.write("PassengerId,Survived,Pclass,Name,Sex,Age,SibSp,Parch,Ticket,Fare,Cabin,Embarked");
                filteredList.stream().forEach(passenger -> {
                    try {
                        fileWriter.write("\n");
                        fileWriter.write(passenger.toString());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        statisticsButton=new JButton("Create statistics file");
        statisticsButton.setBounds(Constants.WINDOW_WIDTH / TWO - Constants.BUTTON_WIDTH, y + EIGHT * Constants.LABEL_HEIGHT +  EIGHT* Constants.SPACE_BETWEEN_LINES, TWO*Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        this.add(statisticsButton);
        Statistics statistics=new Statistics(passengers);
        statisticsButton.addActionListener(((e)->{
            File statisticsFile = new File("src/main/resources/Statistics.txt");
            if(!statisticsFile.exists()){
                try {
                    statisticsFile.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                FileWriter fileWriter= new FileWriter(statisticsFile.getPath());
                fileWriter.write(statistics.getStatistics());
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }));
    }

}

