import java.util.LinkedList;
import java.util.List;

public class Statistics {
    private List<Passenger> passengers = new LinkedList<>();
    private String statistics = new String();

    public Statistics(List<Passenger> passengers) {
        this.passengers = passengers;
        makeStatistics();
    }

    public String getStatistics() {
        return statistics;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    private void makeStatistics() {
        FilterParameters filterParameters = new FilterParameters();
        filterParameters.setGender("male");
        double maleCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setSurvivalStatus(1);
        double maleSurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setGender("female");
        double femaleSurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setSurvivalStatus(-1);
        double femaleCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setGender("All");
        filterParameters.setPClass(1);
        double firstClassCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setSurvivalStatus(1);
        double firstClassSurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setSurvivalStatus(-1);
        filterParameters.setPClass(2);
        double secondClassCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setSurvivalStatus(1);
        double secondClassSurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setSurvivalStatus(-1);
        filterParameters.setPClass(3);
        double thirdClassCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setSurvivalStatus(1);
        double thirdClassSurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setPClass(-1);
        double tenAndYoungerCount = (double) passengers.stream().filter(passenger -> {
            boolean ans = false;
            if (passenger.getAge() >= 0 && passenger.getAge() <= 10) {
                ans = true;
            }
            return ans;
        }).count();
        double tenAndYoungerSurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).filter(passenger -> {
            boolean ans = false;
            if (passenger.getAge() >= 0 && passenger.getAge() <= 10) {
                ans = true;
            }
            return ans;
        }).count();
        double elevenToTwentyCount = (double) passengers.stream().filter(passenger -> {
            boolean ans = false;
            if (passenger.getAge() >= 11 && passenger.getAge() <= 20) {
                ans = true;
            }
            return ans;
        }).count();
        double elevenToTwentySurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).filter(passenger -> {
            boolean ans = false;
            if (passenger.getAge() >= 11 && passenger.getAge() <= 20) {
                ans = true;
            }
            return ans;
        }).count();
        double twentyOneToThirtyCount = (double) passengers.stream().filter(passenger -> {
            boolean ans = false;
            if (passenger.getAge() >= 21 && passenger.getAge() <= 30) {
                ans = true;
            }
            return ans;
        }).count();
        double twentyOneToThirtySurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).filter(passenger -> {
            boolean ans = false;
            if (passenger.getAge() >= 21 && passenger.getAge() <= 30) {
                ans = true;
            }
            return ans;
        }).count();
        double thirtyOneToFortyCount = (double) passengers.stream().filter(passenger -> {
            boolean ans = false;
            if (passenger.getAge() >= 31 && passenger.getAge() <= 40) {
                ans = true;
            }
            return ans;
        }).count();
        double thirtyOneToFortySurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).filter(passenger -> {
            boolean ans = false;
            if (passenger.getAge() >= 31 && passenger.getAge() <= 40) {
                ans = true;
            }
            return ans;
        }).count();
        double fortyOneToFiftyCount = (double) passengers.stream().filter(passenger -> {
            boolean ans = false;
            if (passenger.getAge() >= 41 && passenger.getAge() <= 50) {
                ans = true;
            }
            return ans;
        }).count();
        double fortyOneToFiftySurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).filter(passenger -> {
            boolean ans = false;
            if (passenger.getAge() >= 41 && passenger.getAge() <= 50) {
                ans = true;
            }
            return ans;
        }).count();
        double aboveFiftyCount = (double) passengers.stream().filter(passenger -> {
            boolean ans = false;
            if (passenger.getAge() >= 51) {
                ans = true;
            }
            return ans;
        }).count();
        double aboveFiftySurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).filter(passenger -> {
            boolean ans = false;
            if (passenger.getAge() >= 51) {
                ans = true;
            }
            return ans;
        }).count();
        double withFamilyCount = (double) passengers.stream().filter(passenger -> {
            boolean ans = false;
            if (passenger.getSibSp() >= 1 || passenger.getParCh() >= 1) {
                ans = true;
            }
            return ans;
        }).count();
        double withFamilySurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).filter(passenger -> {
            boolean ans = false;
            if (passenger.getSibSp() >= 1 || passenger.getParCh() >= 1) {
                ans = true;
            }
            return ans;
        }).count();
        double withoutFamilyCount = (double) passengers.stream().filter(passenger -> {
            boolean ans = false;
            if (passenger.getSibSp() == 0 || passenger.getParCh() == 0) {
                ans = true;
            }
            return ans;
        }).count();
        double withoutFamilySurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).filter(passenger -> {
            boolean ans = false;
            if (passenger.getSibSp() == 0 || passenger.getParCh() == 0) {
                ans = true;
            }
            return ans;
        }).count();
        double tenPoundFareAndCheaperCount = (double) passengers.stream().filter(passenger -> {
            boolean ans = false;
            if (passenger.getFare()>=0&&passenger.getFare() <= 10) {
                ans = true;
            }
            return ans;
        }).count();
        double tenPoundFareAndCheaperSurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).filter(passenger -> {
            boolean ans = false;
            if (passenger.getFare()>=0&&passenger.getFare() <= 10) {
                ans = true;
            }
            return ans;
        }).count();
        double elevenToThirtyPoundFareCount = (double) passengers.stream().filter(passenger -> {
            boolean ans = false;
            if (passenger.getFare() >= 11 && passenger.getFare() <= 30) {
                ans = true;
            }
            return ans;
        }).count();
        double elevenToThirtyPoundFareSurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).filter(passenger -> {
            boolean ans = false;
            if (passenger.getFare() >= 11 && passenger.getFare() <= 30) {
                ans = true;
            }
            return ans;
        }).count();
        double aboveThirtyPoundFareCount = (double) passengers.stream().filter(passenger -> {
            boolean ans = false;
            if (passenger.getFare() >= 31) {
                ans = true;
            }
            return ans;
        }).count();
        double aboveThirtyPoundFareSurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).filter(passenger -> {
            boolean ans = false;
            if (passenger.getFare() >= 31) {
                ans = true;
            }
            return ans;
        }).count();
        filterParameters.setEmbarked('C');
        double cherbourgSurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setSurvivalStatus(-1);
        double cherbourgCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setEmbarked('Q');
        double queenstownCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setSurvivalStatus(1);
        double queenstownSurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setEmbarked('S');
        double southamptonSurvivorsCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        filterParameters.setSurvivalStatus(-1);
        double southamptonCount = (double) passengers.stream().filter(passenger -> passenger.doesPassengerMatchParameters(filterParameters)).count();
        statistics = "Percentage of survival by class: \n" +
                "First class: " + 100 * (firstClassSurvivorsCount / firstClassCount) + "% \n" +
                "Second class: " + 100 * (secondClassSurvivorsCount / secondClassCount) + "% \n" +
                "Third class: " + 100 * (thirdClassSurvivorsCount / thirdClassCount) + "% \n \n" +
                "Percentage of survival by gender: \n" +
                "Male: " + 100 * (maleSurvivorsCount / maleCount) + "% \n" +
                "Female: " + 100 * (femaleSurvivorsCount / femaleCount) + "% \n \n" +
                "Percentage of survival by age: \n" +
                "0-10: " + 100 * (tenAndYoungerSurvivorsCount / tenAndYoungerCount) + "% \n" +
                "11-20: " + 100 * (elevenToTwentySurvivorsCount / elevenToTwentyCount) + "% \n" +
                "21-30: " + 100 * (twentyOneToThirtySurvivorsCount / twentyOneToThirtyCount) + "% \n" +
                "31-40: " + 100 * (thirtyOneToFortySurvivorsCount / thirtyOneToFortyCount) + "% \n" +
                "41-50: " + 100 * (fortyOneToFiftySurvivorsCount / fortyOneToFiftyCount) + "% \n" +
                "51+: " + 100 * (aboveFiftySurvivorsCount / aboveFiftyCount) + "% \n \n" +
                "Percentage of survival by fare: \n" +
                "0-10: " + 100 * (tenPoundFareAndCheaperSurvivorsCount / tenPoundFareAndCheaperCount) + "% \n" +
                "11-30: " + 100 * (elevenToTwentySurvivorsCount / elevenToThirtyPoundFareCount) + "% \n" +
                "31+: " + 100 * (aboveThirtyPoundFareSurvivorsCount / aboveThirtyPoundFareCount) + "% \n \n" +
                "Percentage of survival by embarking port: \n" +
                "Cherbourg: " + 100 * (cherbourgSurvivorsCount / cherbourgCount) + "% \n" +
                "QueensTown: " + 100 * (queenstownSurvivorsCount / queenstownCount) + "% \n" +
                "Southampton: " + 100 * (southamptonSurvivorsCount / southamptonCount) + "%";
    }

}
